FROM adoptopenjdk/openjdk11-openj9
COPY target/email-service*.jar /opt/app/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/app/app.jar"]
