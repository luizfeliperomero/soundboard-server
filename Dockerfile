FROM openjdk
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw package -DskipTests
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/soundboard-0.0.1-SNAPSHOT.jar"]
