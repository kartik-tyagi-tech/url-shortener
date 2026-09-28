<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0:0f2027,50:203a43,100:2c5364&height=180&section=header&text=URL%20Shortener&fontSize=50&fontColor=ffffff&animation=fadeIn&fontAlignY=36&desc=Spring%20Boot%20REST%20API%20%E2%80%A2%20Base62%20Short%20Codes&descAlignY=58&descSize=17" width="100%"/>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring%20Boot-4.1-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring%20Data%20JPA-Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white"/>
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/>
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"/>
  <img src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black"/>
</p>

<p align="center">
  A REST API that turns long URLs into short, shareable links — with custom aliases, expiry dates and click tracking.
</p>

---

## ✨ Features

| | Feature | Details |
|---|---|---|
| 🔗 | **Shorten URLs** | Auto-generated Base62 short codes |
| 🏷️ | **Custom alias** | Pick your own code instead of an auto-generated one |
| ⏳ | **Expiration dates** | Optional; must be in the future — no date means the link never expires |
| ↪️ | **Redirects** | `GET /r/{shortCode}` sends users to the original link (HTTP 302) |
| 📈 | **Click tracking** | Click count goes up on every redirect |
| 🔍 | **List & search** | Pagination and search across all URLs |
| ✏️ | **Update / delete** | Change a link's destination or remove it |
| 🛡️ | **Validation & errors** | Bean Validation + centralized exception handling with clean JSON errors |
| 📘 | **API docs** | Interactive Swagger UI |

---

## 🏗️ Architecture

```
          ┌──────────────┐
 Client ─▶│  Controller  │  UrlController · RedirectController
          └──────┬───────┘
                 ▼
          ┌──────────────┐
          │   Service    │  UrlService — business logic, Base62, expiry, clicks
          └──────┬───────┘
                 ▼
          ┌──────────────┐
          │  Repository  │  UrlMappingRepository (Spring Data JPA)
          └──────┬───────┘
                 ▼
          ┌──────────────┐
          │    MySQL     │  url_shortener_db
          └──────────────┘
```

### 🔢 How short codes are generated

Each URL's database ID is encoded in **Base62** (`0-9`, `a-z`, `A-Z`). Database IDs never repeat, so every short code is unique — no random generation or collision checks needed.

```
ID 1        →  "1"
ID 125      →  "21"
ID 1000000  →  "4c92"
```

---

## 📁 Project Structure

```
src/main/java/com/learning/urlshortener/
├── controller/   → REST endpoints (UrlController, RedirectController)
├── service/      → business logic (UrlService)
├── repository/   → database access (UrlMappingRepository)
├── entity/       → table mapping (UrlMapping)
├── dto/          → CreateUrlRequest, UpdateUrlRequest, UrlResponse
├── exception/    → custom exceptions + GlobalExceptionHandler
├── util/         → Base62Encoder
└── config/       → Swagger / OpenAPI config
```

---

## 🔌 API Endpoints

| Method | Endpoint | Description |
|:---:|---|---|
| ![POST](https://img.shields.io/badge/POST-49CC90?style=flat-square) | `/api/urls` | Create a short URL |
| ![GET](https://img.shields.io/badge/GET-61AFFE?style=flat-square) | `/api/urls` | List URLs (`search`, `page`, `size` params) |
| ![PUT](https://img.shields.io/badge/PUT-FCA130?style=flat-square) | `/api/urls/{id}` | Update a URL's destination |
| ![DELETE](https://img.shields.io/badge/DELETE-F93E3E?style=flat-square) | `/api/urls/{id}` | Delete a URL |
| ![GET](https://img.shields.io/badge/GET-61AFFE?style=flat-square) | `/r/{shortCode}` | Redirect to the original URL |

### Example — create a short URL

**Request**

```bash
curl -X POST http://localhost:8080/api/urls \
  -H "Content-Type: application/json" \
  -d '{
        "originalUrl": "https://github.com",
        "customAlias": "gh",
        "expirationDate": "2027-12-31T23:59:00"
      }'
```

`customAlias` and `expirationDate` are optional.

**Response** — `201 Created`

```json
{
  "id": 1,
  "originalUrl": "https://github.com",
  "shortCode": "gh",
  "shortUrl": "http://localhost:8080/r/gh",
  "expirationDate": "2027-12-31T23:59:00",
  "clickCount": 0,
  "createdAt": "2026-08-21T12:00:00"
}
```

### Error responses

| Status | When |
|:---:|---|
| `400 Bad Request` | Missing URL, or expiration date in the past |
| `404 Not Found` | Short code or ID doesn't exist, or the link has expired |
| `409 Conflict` | Custom alias already taken |

---

## 🚀 Getting Started

### Prerequisites

- Java 21
- Maven
- MySQL running locally

### Run it

```bash
# 1. Clone
git clone https://github.com/kartik-tyagi-tech/url-shortener.git
cd url-shortener

# 2. Set your MySQL username/password in
#    src/main/resources/application.yml

# 3. Start the app
mvn spring-boot:run
```

The app runs on **http://localhost:8080**. The `url_shortener_db` database is created automatically on first run.

### Try the API

Open **http://localhost:8080/swagger-ui.html** to explore and test every endpoint in the browser.

---

## 🛣️ Roadmap

- [ ] User accounts with Spring Security + JWT
- [ ] Redis caching for hot redirects
- [ ] Rate limiting
- [ ] Asynchronous click tracking
- [ ] Flyway migrations instead of `ddl-auto: update`

---

## 👨‍💻 Author

**Kartik Tyagi** — MCA @ VIT Vellore

<p>
  <a href="https://github.com/kartik-tyagi-tech"><img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white"/></a>
  <a href="https://linkedin.com/in/tyagikartik"><img src="https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white"/></a>
  <a href="https://leetcode.com/u/Kartiktyagi16"><img src="https://img.shields.io/badge/LeetCode-FFA116?style=for-the-badge&logo=leetcode&logoColor=black"/></a>
</p>

<p align="center">⭐ If you found this useful, consider giving it a star!</p>
