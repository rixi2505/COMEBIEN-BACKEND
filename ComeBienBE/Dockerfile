FROM openjdk:20
VOLUME [ "/tmp" ]
EXPOSE 8080
ADD ./target/backend.jar backend-aws.jar
ENTRYPOINT ["java", "-jar", "/backend-aws.jar"]