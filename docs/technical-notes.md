# Technical Notes

## Backend Framework

Spring Boot is used as the primary backend framework for the application.

## Layered Design

The application separates controllers, services, repositories and models
into dedicated packages.

This makes responsibilities easier to identify and supports maintainability.

## Persistence

Spring Data JPA and Hibernate are used for persistence and object-relational
mapping, with MySQL serving as the relational database.

## Validation

Spring Boot Validation is included in the project's dependency stack to
support validation of application input.

## Server-Side Rendering

Thymeleaf is included for server-side web page rendering.

## PDF Generation

The application includes iTextPDF and a dedicated `CourierPDF` component,
supporting courier-related PDF functionality.

## Build and Dependency Management

Maven manages project dependencies and build configuration.

The Maven Wrapper is included so that the project can be built without
requiring a globally installed Maven version.

## Development Configuration

Spring Boot DevTools is included in the project dependency configuration
for development-time support.

## Testing

Spring Boot Test is included in the project dependency configuration.

## Version Control

The project is maintained in Git and published through GitHub as a technical
portfolio project.
