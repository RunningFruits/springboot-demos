FROM cantara/alpine-openjdk-jdk8
COPY boot-api.jar /boot-api.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/boot-api.jar"]
