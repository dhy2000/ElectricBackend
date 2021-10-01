FROM maven:3.8.2-openjdk-8-slim

# Set source directory
WORKDIR /app

# Copy dependency first
COPY pom.xml .

# Download dependencies
RUN mvn dependency:copy-dependencies

# Copy project source
COPY . .

# Build maven project, Clear useless built files, Load artifact jar to working directory
RUN mvn compile && mvn package && cd target && rm -r `ls | grep -v ".jar$"` && cd .. && cp target/*.jar ./SpringApplication.jar

# Write start script
RUN echo "#!/bin/sh" > start.sh && echo 'echo $DB_USER && java -jar SpringApplication.jar --spring.datasource.username=$DB_USER --spring.datasource.password=$DB_PASSWORD' >> start.sh && chmod +x start.sh

# Open port
EXPOSE 8000

# Set Entrypoint
#ENTRYPOINT ["java", "-jar", "SpringApplication.jar", "--spring.datasource.username=$DB_USER", "--spring.datasource.password=$DB_PASSWORD"]
ENTRYPOINT ['start.sh']