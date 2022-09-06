FROM amazoncorretto:8-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} student-app-1.0.jar
ENTRYPOINT ["java","-jar","student-app-1.0.jar"]