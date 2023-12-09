FROM openjdk:20
ARG dbPath=database/database.db
ENV dbPath="${dbPath}"
ARG JAR_FILE=target/api-0.0.1-SNAPSHOT.jar 
WORKDIR /opt/api
COPY ${JAR_FILE} api.jar
COPY ./db ./database
ENTRYPOINT ["java", "-jar", "api.jar"]