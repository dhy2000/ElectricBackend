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
RUN mvn compile && mvn package && cd target && rm -r `ls | grep -v ".jar$"` && cd .. && cp target/*.jar ./Work.jar

# Open port
EXPOSE 8000

# Set Entrypoint
ENTRYPOINT ["java", "-jar", "Work.jar"]