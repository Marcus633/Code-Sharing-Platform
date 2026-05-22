# Code Sharing Platform

A multi-user web service built using Java and the Spring Boot framework that allows users to store, retrieve, and manage code snippets. The application provides both a RESTful API returning JSON responses and a Server-Side Rendered (SSR) web interface driven by Apache FreeMarker template configurations. It features self-destructing snippet options enforced by persistent Time-To-Live (TTL) expiration intervals and view-count access boundaries.

This project was developed as a structured backend engineering exercise to implement dynamic model injection, asynchronous JavaScript execution pipelines, and integrated file-system database engines.

## Tech Stack

* **Language & Runtime:** Java 11
* **Framework:** Spring Boot (Spring Web, Apache FreeMarker MVC)
* **Data Access:** Spring Data JPA, Hibernate, H2 Relational Database Engine
* **Data Validation:** Jakarta Bean Validation API
* **Utilities:** Project Lombok, Highlight.js (Client-side syntax rendering)
* **Build System:** Gradle

## Repository Structure

The core source code, styles, and template engines are organized into the following structure:
* **Source Implementation:** `Code Sharing Platform/task/src/platform/`
* **Static Assets (CSS):** `Code Sharing Platform/task/src/resources/css/theme.css`
* **UI Templates (FreeMarker):** `Code Sharing Platform/task/src/resources/templates/*.ftlh`

## API Specifications

### REST API Interface
| Method | Endpoint | Description | Response Format |
| :--- | :--- | :--- | :--- |
| POST | `/api/code/new` | Submits a new code snippet with custom time/view constraints. Returns a structural UUID. | JSON |
| GET | `/api/code/{id}` | Retrieves the data for an individual snippet by its UUID if restrictions are valid. | JSON |
| GET | `/api/code/latest` | Queries and compiles a list of the 10 most recent unrestricted snippets. | JSON |

### Web Browser Interface
| Method | Endpoint | Description | Interface Format |
| :--- | :--- | :--- | :--- |
| GET | `/code/new` | Renders a submission interface allowing manual entry of restriction variables. | HTML / FreeMarker |
| GET | `/code/{id}` | Displays an individual snippet alongside its live remaining visibility counters. | HTML / FreeMarker |
| GET | `/code/latest` | Renders an historical index showing the 10 most recent permanent snippets. | HTML / FreeMarker |

## Local Installation and Execution

### Prerequisites
* Java 11 Development Kit (JDK) or higher installed.
* An environment configured to run Gradle wrapper scripts.

### Build Instructions

1. Clone the repository to your local machine:
   ```bash
   https://github.com/Marcus633/Code-Sharing-Platform.git
   ```
2. Navigate to the repo:
 ```bash
   cd Code-Sharing-Platform
 ```
3. Execute the Spring Boot run task using the Gradle wrapper:
 ```bash
   gradlew.bat bootRun
 ```
The application initializes by default on port `8889`. You can navigate to `http://localhost:8889/code/new` in any standard web browser to submit code blocks interactively, or monitor structural data allocations via the local H2 Database console interface.
