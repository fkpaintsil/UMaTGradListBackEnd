#
# Build stage
#
FROM maven:3.9.0-eclipse-temurin-17 AS build
WORKDIR /app/
COPY . /app/
RUN mvn clean package -DskipTests


#
# Package stage
#
FROM eclipse-temurin:17-jdk
COPY --from=build /app/target/gradlist-0.0.1-SNAPSHOT.jar gradlist.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","gradlist.jar"]

