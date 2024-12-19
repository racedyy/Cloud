# API de Gestion des Comptes Utilisateurs

Cette API permet de gérer les comptes utilisateurs avec les fonctionnalités suivantes :
- Création de compte
- Consultation des informations utilisateur
- Modification des informations utilisateur (sauf email)

## Prérequis

1. Java JDK 17 ou supérieur
2. PostgreSQL 12 ou supérieur
3. Maven
4. Postman (pour tester l'API)

## Installation

1. **Configuration de la base de données PostgreSQL**
   ```sql
   CREATE DATABASE apicompte;
   ```
   Les identifiants par défaut dans `application.properties` sont :
   - Username: postgres
   - Password: PostgresCedy
   - Database: apicompte

2. **Cloner le projet**
   ```bash
   git clone [url-du-projet]
   cd [nom-du-projet]
   ```

3. **Compiler le projet**
   ```bash
   mvn clean install
   ```

## Lancement de l'application

1. **Démarrer l'application**
   ```bash
   mvn spring-boot:run
   ```
   L'application sera accessible sur `http://localhost:8080`

## Tests avec Postman

### 1. Création d'un compte utilisateur
- **Endpoint**: POST `/api/users/register`
- **Content-Type**: application/json
- **Body**:
  ```json
  {
    "username": "testuser",
    "email": "test@example.com",
    "password": "password123"
  }
  ```
- **Réponse attendue** (200 OK):
  ```json
  {
    "id": 1,
    "username": "testuser",
    "email": "test@example.com",
    "role": "USER",
    "enabled": true
  }
  ```

### 2. Consultation d'un compte utilisateur
- **Endpoint**: GET `/api/users/{id}`
- **Example**: GET `/api/users/1`
- **Réponse attendue** (200 OK):
  ```json
  {
    "id": 1,
    "username": "testuser",
    "email": "test@example.com",
    "role": "USER",
    "enabled": true
  }
  ```

### 3. Modification d'un compte utilisateur
- **Endpoint**: PUT `/api/users/{id}`
- **Content-Type**: application/json
- **Example**: PUT `/api/users/1`
- **Body** (tous les champs sont optionnels):
  ```json
  {
    "username": "nouveau_username",
    "password": "nouveau_password",
    "enabled": true
  }
  ```
- **Réponse attendue** après avoir modifié le mot de passe et refaire la consultation du compte (200 OK):
  ```json
  {
    "id": 1,
    "username": "nouveau_username",
    "email": "test@example.com",
    "role": "USER",
    "enabled": true
  }
  ```

  # On ne peut pas modifier l'email :
  ```json
  {
    "username": "nouveau_username",
    "password": "nouveau_password",
    "enabled": true,
    "email": "nouveau_email@example.com"  
  }
  ```
  **Réponse attendue** (400 BAD_REQUEST) avec comme erreur : Email cannot be modified 

## Notes importantes

1. **Validation des données**:
   - Le username doit être unique
   - L'email doit être unique et valide
   - Le mot de passe est obligatoire à la création

2. **Sécurité**:
   - Les mots de passe sont automatiquement hashés avant stockage
   - L'email ne peut pas être modifié après la création du compte

3. **Gestion des erreurs**:
   - Username déjà existant : "Username already exists"
   - Email déjà existant : "Email already exists"
   - Utilisateur non trouvé : "User not found"
   - Email ne peut pas être modifié : "Email cannot be modified"

## Structure du projet

```
src/
├── main/
│   ├── java/
│   │   └── com/identity/provider/
│   │       ├── config/
│   │       │   └── SecurityConfig.java
│   │       ├── controller/
│   │       │   └── UserController.java
│   │       ├── dto/
│   │       │   └── UserDto.java
│   │       ├── enums/
│   │       │   └── Role.java
│   │       ├── exception/
│   │       │   └── GlobalExceptionHandler.java
│   │       ├── model/
│   │       │   └── User.java
│   │       ├── repository/
│   │       │   └── UserRepository.java
│   │       ├── service/
│   │       │   └── UserService.java
│   │       ├── IdentityProviderApplication.java
│   │       └── UserManagementApplication.java
│   └── resources/
│       └── application.properties
``` 