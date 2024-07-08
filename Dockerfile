FROM openjdk:21

WORKDIR /app

COPY target/my-albums-0.0.1-SNAPSHOT.jar java-app.jar

ENTRYPOINT [ "java","-jar","java-app.jar" ]
