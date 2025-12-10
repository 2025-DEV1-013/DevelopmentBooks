
# Development Books

There is a series of books about software development that have been read by a lot of developers who want to improve their development skills. Let’s say an editor, in a gesture of immense generosity to mankind (and to increase sales as well), is willing to set up a pricing model where you can get discounts when you buy these books. The available books are :

1. Clean Code (Robert Martin, 2008)
2. The Clean Coder (Robert Martin, 2011)
3. Clean Architecture (Robert Martin, 2017)
4. Test Driven Development by Example (Kent Beck, 2003)
5. Working Effectively With Legacy Code (Michael C. Feathers, 2004)


# Rules

One copy of the five books costs 50 EUR.

- If, however, you buy two different books from the series, you get a 5% discount on those two books.
- If you buy 3 different books, you get a 10% discount.
- If you buy 4 different books, you get a 20% discount.
- If you go for the whole hog, and buy all 5, you get a huge 25% discount.
- Note that if you buy, say, 4 books, of which 3 are different titles, you get a 10% discount on the 3 that form part of a set, but the 4th book still costs 50 EUR.


# Purpose

Develop a application to **calculate the best price** of any conceivable shopping basket by applicable the above discounts using **Test Driven Development** (TDD).

- **Backend** : Java & Springboot
- **Frontend**: ReactJS

# Requirement Criteria

If the shopping basket contains the below books.

- 2 copies of the “Clean Code” book
- 2 copies of the “Clean Coder” book
- 2 copies of the “Clean Architecture” book
- 1 copy of the “Test Driven Development by Example” book
- 1 copy of the “Working effectively with Legacy Code” book

We can avail the discounts for above shopping basket containing 8 books by grouping [5,3] or [4,4] or [3,3,2], etc. Output should be **320 EUR** as the best price by applying **[4,4]** as below.

- (4 * 50 EUR) - 20% [first book, second book, third book, fourth book]
- (4 * 50 EUR) - 20% [first book, second book, third book, fifth book]

= (160 EUR + 160 EUR) => **320 EUR**

# Tools & Tech Stack
### Backend – Tools & Libraries

- **Language:** Java 21
- **Framework:** Spring Boot 4.x (Spring MVC)
- **Build Tool:** Maven
- **Mapping:** MapStruct
- **Validation & Exceptions:** Spring Validation, custom exception handlers
- **API Documentation:** springdoc-openapi (Swagger UI)
- **Code Coverage:** JaCoCo
- **Testing:** JUnit 5, Mockito, Spring WebMVC Test (`@WebMvcTest`)

### Frontend – Tools & Libraries

- **Language:** JavaScript / JSX
- **Framework:** React 18
- **Bundler / Dev Server:** Vite
- **UI Library:** Material UI (MUI)
- **Routing:** React Router
- **Testing:** Vitest + React Testing Library + Jest-DOM
- **Styling:** CSS modules / scoped CSS under `src/styles`

# Prerequisites

Install the following on your machine:

- Java 21+

- Maven 3.9+

- Node.js 18+ and npm 9+

- Git (optional, for version control)


## BackEnd Project Setup

The initial setup includes:

- **Spring Boot 4.0.0** project structure
- **Java 21** compatibility

## Running the Application

First clone the git repository or download the source code form this link :
```bash
https://github.com/2025-DEV1-013/DevelopmentBooks.git
```
To run the application locally:
### Backend
```bash
cd book-discount-price-backend
mvn spring-boot:run            # run app
mvn clean package              # build jar
```
Verify:

http://localhost:8090/api/books/getbooks

http://localhost:8090/api/books/price/calculate (POST with JSON)

### Frontend

```bash
cd development-books-frontend
npm install                    # install dependencies, only required first time
npm run dev                    # start Vite dev server (URL : http://localhost:5173)

```

    Remember first run backend application, once backend up & running 
    then run frontend app (`npm run dev`) to access the full application.

# Build & Run Tests 

To Execute Test locally:
### Backend
```bash
cd book-discount-price-backend
mvn clean test                 # run tests + Jacoco
```
This will:

Compile the project

Run all JUnit tests

Generate JaCoCo code coverage report at:
```bash
book-discount-price-backend/target/site/jacoco/index.html 
```
### Frontend

```bash
cd development-books-frontend
npm test                       # run unit tests (Vitest)
npm run test:ui                # web-based dashboard (URL : http://localhost:51204/__vitest__)
```
The frontend coverage html report will be at:
```bash
development-books-frontend/coverage/index.html OR access http://localhost:51204/__vitest__
```
Open coverage/index.html in your browser.

# Swagger & OpenAPI Usage

Backend uses Swagger/OpenAPI 3.0 for API documentation.

### swagger.yaml defines:

/api/books → Fetch all development books

/api/books/price → Calculate discounted price

Models:

- Book

- BookResponse

- BookBasketRequest

- BookPriceResponse

### Tools used:

swagger.yaml (located in src/main/resources)

**openapi-generator-maven-plugin**
→ Automatically generates API models (DTOs) from YAML

**springdoc-openapi-starter**
→ Auto-generates live Swagger UI at runtime

The backend exposes interactive Swagger UI using springdoc-openapi.

Once the backend is running:  http://localhost:8090/swagger-ui/index.html