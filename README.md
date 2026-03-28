# Application de Gestion de Commande Restaurant
## Description

Cette application permet de gérer le cycle complet des commandes dans un restaurant, depuis la prise de commande par le client jusqu’au traitement en cuisine.
Elle inclut :
- la gestion des commandes
- la validation via code
- le paiement
- la validation côté restaurant
- le suivi en cuisine
- les statistiques administratives

---

##  Technologies utilisées

- Java 17
- Spring Boot
- Spring Security (JWT)
- Spring Data JPA
- Base de données H2 (in-memory)
- Swagger (OpenAPI)
- Postman

---

## Fonctionnalités principales

###  Côté Client
- Passer une commande (liste de plats)
- Choisir une table du restaurant
- Recevoir un code de validation
- Confirmer la commande via code
- Soumettre un paiement (Cash / Mvola / Orange Money)
- Annuler une commande non payée
- Consulter le détail d’une commande via numéro

---

###  Côté Restaurant (Admin)
- Voir les commandes à valider
- Valider les commandes payées
- Consulter les statistiques :
  - montant total par mode de paiement
  - commandes par jour

---

###  Côté Cuisinier
- Voir les commandes en cours
- Modifier le statut :
  - CONFIRMEE → EN_PREPARATION
  - EN_PREPARATION → FINIE
- Consulter les statistiques journalières

---

##  Authentification
Authentification basée sur JWT :
- Access Token
- Refresh Token (renouvellement automatique)

### Rôles :
- ADMIN
- CUISINIER

---

##  Gestion du numéro de commande
Chaque commande possède un numéro unique composé de :
ddMMyy + compteur du jour


##  Gestion des commandes expirées

Les commandes non confirmées ou non payées sont automatiquement supprimées après 30 minutes.
Conditions :
- Statut : EN_VALIDATION_EMAIL ou EN_ATTENTE
- Si délai dépassé → suppression automatique

---

##  Documentation API (Swagger)

Swagger UI :
http://localhost:8080/swagger-ui/index.html

Documentation JSON :
http://localhost:8080/v3/api-docs

---

##  Tests API

Collection Postman incluse :
restaurant_api_postman_collection.json

---

##  Lancer le projet

mvn spring-boot:run

---

##  Base de données (H2)

Console :
http://localhost:8080/h2-console

Configuration :
- JDBC URL : jdbc:h2:mem:testdb
- User : sa
- Password : (vide)

---

##  Comptes de test

Admin :
Numero : 0320000000  
Mot de passe : 123456  

Cuisinier :
Numero : 0330000000  
Mot de passe : 123456  

---

##  Scénario de test complet

1. Login utilisateur  
2. Passer une commande  
3. Validation via code  
4. Soumission du paiement  
5. Validation côté restaurant  
6. Traitement en cuisine :
   - EN_PREPARATION  
   - FINIE  
7. Consultation des statistiques  

---

##  Structure du projet

src/main/java/mg/votretp/restapi/  
- controller  
- service  
- repository  
- model  
- dto  

---
