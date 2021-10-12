FROM maven:3.8.2-openjdk-8-slim

# Set source directory
WORKDIR /app

# Copy dependency first
COPY pom.xml .

# Download dependencies and plugins
RUN mvn dependency:resolve && mvn dependency:resolve-plugins

# Copy project source
COPY . .

# Build maven project, Clear useless built files, Load artifact jar to working directory
RUN mvn compile && mvn package && cd target && rm -r `ls | grep -v ".jar$"` && cd .. && cp target/*.jar ./SpringApplication.jar

# Write start script
RUN echo "java -jar SpringApplication.jar --spring.datasource.username=\$DB_USER --spring.datasource.password=\$DB_PASSWORD" >> start.sh

# Open port
EXPOSE 8000

# Set Entrypoint
ENTRYPOINT ["/bin/bash", "./start.sh"]