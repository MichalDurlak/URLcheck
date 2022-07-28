FROM openjdk:19-jdk
EXPOSE 8080
COPY target/URLcheck-0.0.1-SNAPSHOT.jar /URLcheck.jar
CMD ["java","-jar","/URLcheck.jar"]