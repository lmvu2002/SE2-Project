# Fetching latest version of Java
FROM openjdk:19

VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]