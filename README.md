# Astranova

<div align="center">

[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.0-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![Gradle](https://img.shields.io/badge/Gradle-8.x-02303A?style=for-the-badge&logo=gradle&logoColor=white)](https://gradle.org/)

</div>

Astranova is a Java and Spring Boot application for modeling a space mission domain. The project focuses on core entities, role/state enums, and validation rules for a futuristic mission management system.

## Features

- Domain-driven project structure
- Entities for astronauts, missions, planets, spaceships, users, and stakes
- Enums for roles and states used across the app
- Automated validation tests with JUnit 5
- Spring Boot application bootstrap

## Tech Stack

- Java
- Spring Boot
- Gradle Kotlin DSL
- JUnit 5
- H2 Database

## Project Structure

```text
astranova/
├── src/
│   ├── main/
│   │   └── java/dev/team/astranova/
│   │       ├── Application.java
│   │       ├── core/
│   │       │   ├── constant/
│   │       │   └── entity/
│   └── test/
│       └── java/dev/team/astranova/core/entity/
├── build.gradle.kts
├── gradlew
├── gradlew.bat
├── settings.gradle.kts
├── gradle/
├── .gitignore
├── README.md
└── .idea/
```

## Core Domain

The project includes the following domain entities:

- `Astronaut`
- `Mission`
- `Planet`
- `Spaceship`
- `Stake`
- `User`

And the main constants/enums:

- `AstronautState`
- `MissionState`
- `MissionRole`
- `SpaceshipState`
- `ExplorationState`
- `EnvironmentalConditions`
- `UserRole`

## Getting Started

### Prerequisites

- Java JDK 21 or later
- Gradle wrapper included in the repo

### Clone

```bash
git clone <repository-url>
cd astranova
```

### Run tests

```bash
./gradlew test
```

### Start the application

```bash
./gradlew bootRun
```

## Useful Commands

```bash
./gradlew clean
./gradlew test
./gradlew bootRun
./gradlew classes
```

## Development Notes

This repository follows a clean separation between:

- domain entities
- business constants
- validation logic
- test coverage

The app is structured to be extended with adapters, services, ports, and controllers as the project grows.

## Contributing

Contributions are welcome. Please keep commits descriptive and follow conventional commit style for repository consistency.

## License

This project is currently under active development and does not yet declare a public license.
