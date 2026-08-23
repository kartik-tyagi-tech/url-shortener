# URL Shortener

A simple REST API built with Spring Boot that shortens long URLs and redirects users to the original link when they visit the short one.

## Features

- Create a short URL from a long URL
- Optional custom alias (choose your own short code instead of an auto-generated one)
- Optional expiration date for a URL
- Redirects short URLs to their original destination
- Tracks click count on every redirect
- List all URLs with pagination and search
- Update or delete an existing URL
- Input validation and centralized error handling
- Interactive API docs via Swagger

## Tech Stack

- Java 21
- Spring Boot 4
- Spring Data JPA (Hibernate)
- MySQL
- Maven
- Lombok
- Springdoc OpenAPI (Swagger)

## How Short Codes Are Generated

src/main/java/com/learning/urlshortener/
├── controller/ → REST endpoints
├── service/ → business logic
├── repository/ → database access (Spring Data JPA)
├── entity/ → database table mapping
├── dto/ → request/response objects
├── exception/ → custom exceptions + global error handler
├── util/ → Base62 encoder
└── config/ → Swagger configuration


## Getting Started

### Prerequisites

- Java 21
- Maven
- MySQL running locally

### Setup

1. Clone the repository

2. Update `src/main/resources/application.yml` with your MySQL username and password:

```yaml
spring:
  datasource:
    username: root
    password: your_mysql_password
```

3. Run the application

4. The app will start on `http://localhost:8080`. The database `url_shortener_db` will be created automatically on first run.

### Testing the API

Open `http://localhost:8080/swagger-ui.html` to explore and test all endpoints interactively.

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/urls` | Create a short URL |
| GET | `/api/urls` | List URLs (supports `search`, `page`, `size` query params) |
| PUT | `/api/urls/{id}` | Update a URL's destination |
| DELETE | `/api/urls/{id}` | Delete a URL |
| GET | `/r/{shortCode}` | Redirect to the original URL |

### Example: Create a short URL


Response:

```json
{
  "id": 1,
  "originalUrl": "https://github.com",
  "shortCode": "1",
  "shortUrl": "http://localhost:8080/r/1",
  "expirationDate": null,
  "clickCount": 0,
  "createdAt": "2026-08-21T12:00:00"
}
```

## Notes

This project does not currently include user authentication — all URLs are public. Adding user accounts with Spring Security and JWT would be a natural next step.

## Possible Improvements

- User authentication (JWT) so URLs belong to specific users
- Redis caching for frequently accessed redirects
- Rate limiting to prevent abuse
- Asynchronous click tracking
- Database migrations with Flyway instead of `ddl-auto: update`
Each URL's database ID is encoded using **Base62** (digits 0-9, lowercase a-z, uppercase A-Z) to produce a short, unique code. Since IDs from the database are never repeated, this guarantees every generated short code is unique without needing random generation or collision checks.

## Project Structure
