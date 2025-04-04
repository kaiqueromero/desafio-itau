FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/desafio-itau-0.0.1-SNAPSHOT.jar /app/desafio-itau.jar

EXPOSE 8080

CMD ["java", "-jar", "desafio-itau.jar"]