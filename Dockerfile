FROM adoptopenjdk/openjdk11
ARG JAR_FILE=project3restConsumer/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dserver.port=9000","-jar","/app.jar"]