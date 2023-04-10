FROM eclipse-temurin:17-jre-alpine
COPY /target/person-jpa-exmaple-0.0.1-SNAPSHOT.jar /person-jpa-exmaple-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/person-jpa-exmaple-0.0.1-SNAPSHOT.jar"]