# 1. Nom du projet

**Nom du projet :** AssetPilot -- Système de Gestion du Parc Informatique (Backend)

------------------------------------------------------------------------

# 2. Présentation du projet

AssetPilot est une application de gestion du parc informatique composée d'une API REST développée avec Spring Boot. Elle permet de gérer le catalogue du matériel (équipements, catégories), les utilisateurs (employés et techniciens), les affectations du matériel ainsi que les incidents techniques, le tout de manière sécurisée avec des accès basés sur les rôles. L'application s'adresse aux entreprises souhaitant centraliser la gestion de leur matériel informatique. Son objectif principal est de simplifier le suivi du matériel, les affectations et la maintenance grâce à une plateforme moderne, sécurisée et performante.

------------------------------------------------------------------------

# 3. Problématique

Le problème identifié est que la gestion manuelle du parc informatique (matériel, affectations, tickets de maintenance) est souvent lente, dispersée et source d'erreurs.

La solution proposée permet de centraliser ces informations dans une application sécurisée offrant une gestion complète des utilisateurs, du matériel et des accès selon les rôles (Administrateur, Employé, Technicien).

------------------------------------------------------------------------

# 4. Fonctionnalités principales

- Se connecter avec authentification JWT
- Gérer le catalogue du matériel : équipements et catégories (CRUD)
- Gérer les employés et les techniciens (CRUD)
- Effectuer les affectations du matériel et les restitutions
- Déclarer des incidents techniques et les assigner aux techniciens
- Suivre l'état des réparations par les techniciens
- Consulter les statistiques du parc
- Exporter la liste des incidents au format Excel
- Générer des QR codes pour les équipements
- Contrôle d'accès par rôle via @PreAuthorize
- Documentation API avec Swagger

------------------------------------------------------------------------

# 5. Technologies utilisées

Technologie                   Utilisation dans le projet
  ----------------------------- ----------------------------
Java 17                       Développement du backend
Spring Boot 4.1               API REST
Spring Security + JWT         Authentification & autorisation
Spring Data JPA / Hibernate   Persistance
Flyway                        Migrations
MySQL                         Base de données
Redis                         Cache
MapStruct                      Mapping DTO/Entité
Lombok                        Réduction de code
ZXing                         Génération de QR codes
Apache POI                    Export Excel
Docker & Docker Compose       Conteneurisation
GitHub Actions                CI/CD
Swagger (springdoc-openapi)   Documentation
Git/GitHub                    Versionnement

------------------------------------------------------------------------

# 6. Installation et lancement

## 6.1 Prérequis

- Java 17
- Maven
- Docker Desktop
- Git

## 6.2 Cloner le dépôt

``` bash
git clone https://github.com/votre-compte/AssetPilot-backend.git
```

## 6.3 Ouvrir le dossier

``` bash
cd AssetPilot-backend
```

## 6.4 Installer les dépendances

``` bash
mvn clean install
```

## 6.5 Variables d'environnement

``` env
SPRING_DATASOURCE_URL=
SPRING_DATASOURCE_USERNAME=
SPRING_DATASOURCE_PASSWORD=
SPRING_DATA_REDIS_HOST=
APP_JWT_SECRET=
APP_JWT_EXPIRATION=
```

## 6.6 Lancer le projet (Docker)

``` bash
docker compose up --build
```

L'API démarre sur le port 8080, MySQL sur le port 3307 et Redis sur le port 6379.

## 6.7 Ouvrir le projet

API : http://localhost:8080
Swagger UI : http://localhost:8080/swagger-ui.html

**Compte administrateur initial :**
-  Email : `admin@assetpilot.com`

------------------------------------------------------------------------

# 7. Captures d'écran

## Capture 1

**Titre :** Swagger UI

![alt text](captures/swagger-ui.png)

------------------------------------------------------------------------

# 8. Contribution personnelle

Projet réalisé individuellement.

J'ai conçu l'architecture complète du backend.

J'ai développé les API REST, la sécurité JWT, la gestion des rôles avec @PreAuthorize, la pagination, Redis, Docker, GitHub Actions ainsi que la documentation Swagger.

------------------------------------------------------------------------

# 9. Difficultés rencontrées

## Difficulté 1

J'ai rencontré des difficultés lors de l'implémentation de Spring Security avec JWT.

Après plusieurs tests et la consultation de la documentation officielle, j'ai mis en place AuthenticationManager, UserDetailsService, JwtFilter et SecurityFilterChain.

Cette étape m'a permis de mieux comprendre l'authentification JWT.

## Difficulté 2

L'implémentation du contrôle d'accès par rôle nécessitait une configuration précise des autorités et des annotations.

J'ai défini le modèle de rôles (ROLE_ADMIN, ROLE_EMPLOYEE, ROLE_TECHNICIAN) et sécurisé chaque contrôleur avec @PreAuthorize, ce qui m'a permis de mieux maîtriser la sécurité au niveau des méthodes.

## Difficulté 3

Le mapping entre les entités et les DTO ainsi que l'héritage JOINED entre Utilisateur, Employé et Technicien étaient complexes à mettre en place avec JPA.

L'utilisation de MapStruct et la validation du schéma via les migrations Flyway m'ont permis de résoudre ces points.

------------------------------------------------------------------------

# 10. Améliorations possibles

- Ajouter une interface frontend (React.js) pour les trois rôles
- Ajouter des notifications par e-mail
- Ajouter des statistiques avec des graphiques
- Déployer le backend sur Kubernetes

Ces améliorations permettraient d'améliorer l'expérience utilisateur, les performances et la scalabilité de l'application.

------------------------------------------------------------------------

# ✅ Checklist finale

-   [x] Présentation du projet
-   [x] Fonctionnalités
-   [x] Technologies
-   [x] Installation
-   [x] Captures d'écran
-   [x] Contribution
-   [x] Difficultés
-   [x] Améliorations