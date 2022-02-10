FROM openjdk:8
ADD target/corona-app.jar corona-app
EXPOSE 8089
ENTRYPOINT ["java","-jar","corona-app.jar"]
