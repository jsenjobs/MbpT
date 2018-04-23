FROM java:latest

ADD target/*.jar app.jar
Add target/bootstrap.properties bootstrap.properties

RUN bash -c 'touch /app.jar'

ENTRYPOINT ["java","-jar","/app.jar"]