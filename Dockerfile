FROM openjdk:17-jdk
COPY target/webapp-0.0.1-SNAPSHOT.jar webapp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/webapp-0.0.1-SNAPSHOT.jar"]