FROM openjdk:8
ADD target/weather-app.jar weather-app
EXPOSE 8081
ENTRYPOINT ["java","-jar","corona-app.jar"]
