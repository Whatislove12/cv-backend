FROM eclipse-temurin:24-jdk AS build

WORKDIR /app

COPY . .

RUN ./gradlew build

FROM eclipse-temurin:24-jdk

WORKDIR /app

COPY --from=build /app/build/libs/contact-backend-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
