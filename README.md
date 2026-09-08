# Java Spring Boot Courier Management System

A Java Spring Boot web application for managing courier-related operations,
users, employees, locations, and associated administrative workflows.

The project demonstrates practical implementation of a layered Spring Boot
application, relational database persistence, server-side web rendering,
validation, document generation, and structured separation between
controllers, services, repositories, and data models.

## Project Overview

The system was developed as a practical software engineering project to
demonstrate backend and full-stack web application development using the
Java/Spring ecosystem.

The application organizes functionality around courier management,
registration, employee management, and location-related information such as
addresses, countries, districts, and states.

## Key Functional Areas

- Courier management
- Customer/registration workflows
- Employee management
- Address management
- Country management
- District management
- State management
- Web-based application navigation and forms
- PDF-related courier functionality

## Technology Stack

| Technology | Purpose |
|---|---|
| Java 17 | Core application development |
| Spring Boot 3.2.4 | Application framework |
| Spring MVC / Web | Web request handling |
| Spring Data JPA | Data persistence |
| Hibernate | ORM and database mapping |
| MySQL | Relational database |
| Thymeleaf | Server-side web templates |
| HTML / CSS / JavaScript | User interface |
| Maven | Build and dependency management |
| Lombok | Java boilerplate reduction |
| iTextPDF | PDF generation |
| Spring Boot Test | Application testing |
| Git / GitHub | Version control and project portfolio |

The technologies above are based on the project's Maven configuration.

## Architecture

The application follows a layered architecture:

```text
Browser / User
      |
      v
Presentation Layer
(HTML / CSS / JavaScript / Templates)
      |
      v
Controller Layer
      |
      v
Service Layer
      |
      v
Repository Layer
      |
      v
MySQL Database
