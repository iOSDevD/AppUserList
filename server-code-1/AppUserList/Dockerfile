FROM maven:3.6.0-jdk-11-slim AS build

# Copy pom.xml to the image
COPY pom.xml /home/app/pom.xml
RUN mvn -f /home/app/pom.xml verify clean --fail-never

# Copy the source code 
COPY src /home/app/src
RUN mvn -f /home/app/pom.xml package

# Install Tomcat    & openjdk 8 (openjdk has java and javac)
#FROM tomcat:jdk8-openjdk
FROM tomcat:8.5.69-jdk8-openjdk
# Copy source files to tomcat folder structure
COPY --from=build /home/app/target/appUserList-1.0.0-BUILD-SNAPSHOT.war /usr/local/tomcat/webapps/

EXPOSE 8090