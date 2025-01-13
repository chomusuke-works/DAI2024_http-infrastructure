FROM alpine:latest
RUN apk add --no-cache openjdk21
COPY target/*.jar /app/zoo.jar
CMD ["java", "-jar", "/app/zoo.jar"]