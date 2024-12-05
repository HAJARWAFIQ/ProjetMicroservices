# Étape 1: Utiliser Maven pour compiler le JAR
FROM maven:3.8.4-openjdk-17 AS build

# Définir le répertoire de travail
WORKDIR /app

# Copier les fichiers du projet dans le conteneur
COPY . .

# Compiler le projet et générer le fichier JAR (skip tests si nécessaire)
RUN mvn clean package -DskipTests

# Étape 2: Utiliser OpenJDK pour exécuter le JAR
FROM openjdk:17-slim

# Copier le fichier JAR généré dans l'image finale
COPY --from=build /app/target/Security_Microservice-Service-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port sur lequel l'application Spring Boot écoute
EXPOSE 8080

# Lancer l'application Java
ENTRYPOINT ["java", "-jar", "app.jar"]
