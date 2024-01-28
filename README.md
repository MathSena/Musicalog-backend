# Musicalog - Music Store Catalog Web App

## Introduction

Musicalog is a dynamic web application designed for managing a music album catalog. It features a RESTful API that enables users to list, create, update, and delete albums. This application is built using Java 17, Spring Boot, and MongoDB, and it's tested using unit tests.

## Tecnologies

- Java 17
- Spring Boot
- MongoDB
- Postman (for API testing)
- -Swagger (for API documentation)
- Lombok (for code generation)

## Installation

1. **Clone the Repository**
   ```sh
   git clone [repository URL]

   ```
2. **Run**
   - Run the application using your IDE of choice.
   - Run the application using the command line:
     ```sh
     mvn spring-boot:run

     ```

The Musicalog API provides the following endpoints:

## Albums

- **GET /v1/api/albums:** List all albums.
- **GET /v1/api/albums/search:** Search albums by title or artist name.
- **POST /v1/api/albums:** Create a new album.
- **PUT /v1/api/albums/{id}:** Update an existing album.
- **DELETE /v1/api/albums/{id}:** Delete an album.

## Testing

- Use Postman to test the API endpoints.
- Run the unit tests included in the project to ensure functionality.

## Contributing

Contributions to the Musicalog project are welcome. Follow these steps:

1. Fork the repository.
2. Create a new branch (git checkout -b feature-branch).
3. Make changes and commit (git commit -am 'Add some feature').
4. Push to the branch (git push origin feature-branch).
5. Create a new Pull Request.

## License

Musicalog is distributed under the MIT License. See LICENSE for more information.
