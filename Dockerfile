# Pobranie oficjalnego obrazu JDK 21
FROM openjdk:21-jdk-slim

# Ustawienie katalogu roboczego
WORKDIR /app

# Kopiowanie pliku JAR aplikacji do kontenera
COPY build/libs/finwise.jar app.jar

# Ustawienie portu aplikacji
EXPOSE 8080

# Uruchomienie aplikacji Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]