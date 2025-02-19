# Cabinet Medical - Application de Gestion

## Description
Application de gestion de cabinet médical basée sur une architecture microservices, permettant la gestion des patients et des praticiens.

Ordre de lancement :
Eureka Server
Patient Service
Practitionner Service
Gateway Service

## Architecture
L'application est composée de plusieurs microservices :

- **Eureka Server** (Port: 8761)
- **Patient Service** (Port: 8081)
- **Practitioner Service** (Port: 8080)
- **Gateway Service** (Port: 8082)

## Technologies Utilisées

- Java 8
- Spring Boot 1.5.10
- Spring Cloud (Eureka)
- Maven
- Docker
- Swagger 2.6.1
- Lombok

## Documentation API

## Installation

### Prérequis
- Java 8
- Maven
- Docker
- Docker Compose

### Étapes d'installation

1. Cloner le repository
```bash
git clone repourl
cd TP_Cabinet_medical

2.Construire les images Docker
```bash
docker-compose build
docker-compose up -d
```
3 Accéder aux services
Eureka Server : http://localhost:8761
Gateway Service : http://localhost:808
Patient Service : http://localhost:8081
Practitioner Service : http://localhost:8082
Swagger
Un Swagger est disponible pour chaque microservice (excepté Eureka) et est accessible via l'URL respective du service.

### Swagger UI
- Patient Service: http://localhost:8081/swagger-ui.html
- Practitioner Service: http://localhost:8080/swagger-ui.html
- Gateway Service: http://localhost:8082/swagger-ui.html
- Eureka Server: http://localhost:8761
- 
### Eureka Server
- Dashboard: http://localhost:8761

Un Swagger est disponible pour chaque microservice (excepté Eureka) et est accessible via l'URL respective du service.
