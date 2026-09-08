# System Architecture

## 1. Overview

The Courier Management System is a Java-based web application developed
using Spring Boot and organized using a layered architecture.

The architecture separates presentation, request handling, application
logic, persistence, and database responsibilities.

## 2. Architectural Flow

```text
+-----------------------+
|     User / Browser    |
+-----------+-----------+
            |
            v
+-----------------------+
|  Presentation Layer   |
| HTML / CSS / JS /     |
| Thymeleaf Templates   |
+-----------+-----------+
            |
            v
+-----------------------+
|   Controller Layer    |
| Handles Web Requests  |
+-----------+-----------+
            |
            v
+-----------------------+
|      Service Layer    |
| Application /         |
| Business Logic        |
+-----------+-----------+
            |
            v
+-----------------------+
|    Repository Layer   |
| Data Access / JPA     |
+-----------+-----------+
            |
            v
+-----------------------+
|     MySQL Database    |
+-----------------------+
