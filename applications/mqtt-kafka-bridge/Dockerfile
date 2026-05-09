FROM maven:3.6.0-jdk-11-slim AS build
COPY ./src /home/api/src
COPY ./pom.xml /home/api
WORKDIR /home/api/
RUN mvn clean package -Dmaven.test.skip

FROM amazoncorretto:11.0.31
COPY --from=build /home/api/target/*.jar /usr/local/lib/app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=local", "/usr/local/lib/app.jar"]
EXPOSE 8080