# Spring FitZone 💪

## 📖 Description
Spring FitZone is a gym management application built with Java 17 and Spring Boot. It provides CRUD operations for clients and follows a clean architecture with modular layers to ensure scalability and maintainability.

## 🏗️ Architecture
The project is structured in the following layers:
- **Main Package**: Contains the Spring Boot entry point (`SpringFitZoneApplication.java`)
- **Config**: Manages Spring and database configuration settings
- **Service**: Implements business logic
- **Repository**: Interfaces for data access and Spring Data repositories
- **Entity**: Domain models and JPA entities

## 🚀 Technologies Used
- ☕ Java 17
- 🌱 Spring Boot
- 📦 Maven
- 🗄️ MySQL (Database)
- 🧪 JUnit 5 (Unit and integration tests)

## 📋 Prerequisites
- ☕ JDK 17
- 📦 Maven 3.6.x or higher
- 🗄️ MySQL Server

## 💾 Installation
1. Clone the repository:
```bash
git clone https://github.com/your-username/spring-fitzone.git
```

2. Navigate to the project directory:
```bash
cd spring-fitzone
```

3. Build the project with Maven:
```bash
mvn clean install
```

## ⚙️ Database Configuration
1. Create a MySQL database.

2. You can find an example configuration file at `src/main/resources/config/.env.example`:
```env
# Database Configuration
DB_URL=jdbc:mysql://localhost:3306/your_database
DB_USERNAME=your_username
DB_PASSWORD=your_password

# Server Configuration
SERVER_PORT=8080
```

3. Create a `.env` file in the `src/main/resources/config/` directory and copy the variables from `.env.example`, replacing the values with your actual configuration.

4. Ensure your Spring Boot application is configured to read the `.env` file using the dotenv-java library.
   You'll need to add this dependency to your `pom.xml`:
```xml
<dependency>
    <groupId>io.github.cdimascio</groupId>
    <artifactId>dotenv-java</artifactId>
    <version>3.0.0</version>
</dependency>
```

5. Update `src/main/resources/application.properties` to reference these environment variables:
```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

## ▶️ Execution
To run the application:
```bash
mvn spring-boot:run
```

Or execute the main class directly:
```bash
java -jar target/spring-fitzone-0.0.1-SNAPSHOT.jar
```

## 📁 Project Structure
```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── spring_fitzone/
│   │           ├── SpringFitZoneApplication.java  # Main application class
│   │           ├── config/        # Spring and database configuration
│   │           ├── service/       # Business logic services
│   │           ├── repository/    # Spring Data repositories
│   │           └── entity/        # Domain models and JPA entities
│   └── resources/         
│       ├── application.properties # Spring Boot configuration
│       └── config/
│           └── .env.example       # Environment variables example
└── test/
    └── java/                      # Unit and integration tests
```

## 🤝 Contributing
To contribute to the project:
1. Fork the repository
2. Create a feature branch (`git checkout -b feat/new-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feat/new-feature`)
5. Create a Pull Request

## 📄 License
This project is licensed under the MIT License. See the LICENSE file for details.