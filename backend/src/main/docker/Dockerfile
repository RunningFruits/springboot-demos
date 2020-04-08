FROM cantara/alpine-openjdk-jdk8
COPY boot-web.jar /boot-web.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/boot-web.jar"]
