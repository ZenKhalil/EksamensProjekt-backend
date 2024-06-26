# Athletics Management System - Backend

## Overview
The backend of the Athletics Management System is built using Spring Boot. It provides RESTful APIs to manage participants, disciplines, results, and user authentication.

## Table of Contents
- [Overview](#overview)
- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Database Schema](#database-schema)
- [Security](#security)
- [Dependencies](#dependencies)
- [Contributing](#contributing)
- [License](#license)

## Getting Started
Follow these instructions to set up and run the backend locally.

## Prerequisites
- Java 17
- Maven
- MySQL or any other relational database
- Postman (for API testing)

## Installation

1. **Clone the repository:**
   ```sh
   git clone https://github.com/yourusername/athletics-management-system.git
   cd athletics-management-system/backend

2. **Set up the database:**
- Create a database named athletics_management in your MySQL server.
- Update the application.properties file with your database configuration.

3. **Install dependencies:**
   mvn clean install

### Environment Configuration

To run this application, you need to configure your environment variables.

```env
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/your_database
SPRING_DATASOURCE_USERNAME=your_db_username
SPRING_DATASOURCE_PASSWORD=your_db_password
SPRING_JPA_HIBERNATE_DDL_AUTO=update
JWT_SECRET=your_jwt_secret

##Running the Application

To run the application, use the following command:

**mvn spring-boot:run**

The application will start on **http://localhost:8080**.

## API Endpoints

### Authentication
- **POST /api/login** - User login
- **POST /api/signup** - User registration

### Participants
- **GET /participants** - Get all participants
- **GET /participants/{id}** - Get a participant by ID
- **POST /participants** - Create a new participant
- **PUT /participants/{id}** - Update a participant
- **DELETE /participants/{id}** - Delete a participant

### Disciplines
- **GET /disciplines** - Get all disciplines
- **GET /disciplines/{id}** - Get a discipline by ID
- **POST /disciplines** - Create a new discipline
- **PUT /disciplines/{id}** - Update a discipline
- **DELETE /disciplines/{id}** - Delete a discipline

### Results
- **GET /results** - Get all results
- **GET /results/{id}** - Get a result by ID
- **POST /results** - Create a new result
- **PUT /results/{id}** - Update a result
- **DELETE /results/{id}** - Delete a result

## Database Schema

The database schema consists of the following tables:
- **users** - Stores user information.
- **roles** - Stores role information.
- **user_roles** - Maps users to their roles.
- **participants** - Stores participant information.
- **disciplines** - Stores discipline information.
- **results** - Stores result information.

## Security

The application uses JWT (JSON Web Tokens) for securing the APIs. The security configuration is defined in `SecurityConfig.java`.

- **Endpoints accessible without authentication:**
  - `/api/login`
  - `/api/signup`

- **Endpoints that require authentication:**
  - All other endpoints

## Dependencies

Key dependencies used in this project include:
- **Spring Boot**
- **Spring Security**
- **Spring Data JPA**
- **MySQL Connector**
- **JWT (JSON Web Token)**
- **Lombok**

## Contributing

1. **Fork the repository**
2. **Create your feature branch** (`git checkout -b feature/your-feature`)
3. **Commit your changes** (`git commit -am 'Add some feature'`)
4. **Push to the branch** (`git push origin feature/your-feature`)
5. **Create a new Pull Request**

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.



