# World of Gamecraft

This is a game project developed using Java, SQL, Spring Boot, and Maven.

## Project Structure

The project is structured into several packages, each containing related classes and functionalities.

- `rs.maxbet.worldofgamecraft.data`: Contains the data classes for the project.
- `rs.maxbet.worldofgamecraft.controler`: Contains the controllers for handling HTTP requests.
- `rs.maxbet.worldofgamecraft.service`: Contains the service classes that handle the business logic.
- `rs.maxbet.worldofgamecraft.exception`: Contains custom exceptions for the project.

## Key Features

- Dueling system: Users can challenge each other to duels. The duel system supports different modes such as attack, cast, and heal.
- Role-based access control: Some endpoints are only accessible to users with specific roles.

## Setup

Prerequisites: Make sure that Accounts and Character services are running with all containers (RabbitMQ, SQL Server).
1. Clone the repository.
2. Install the dependencies using Maven.
3. Set up your SQL database and update the application properties with your database credentials.
4. Run the application.

## Usage

The application exposes several endpoints under the `/api` path. For example:

- `/api/challenge`: POST request to create a new challenge.
- `/api/challenge`: GET request to retrieve all challenges.
- `/api/{characterId}/attack`: POST request to set a character's mode to attack.
- `/api/{characterId}/cast`: POST request to set a character's mode to cast.
- `/api/{characterId}/heal`: POST request to set a character's mode to heal.

## Contributing

Please read CONTRIBUTING.md for details on our code of conduct, and the process for submitting pull requests to us.

## License

This project is licensed under the MIT License - see the LICENSE.md file for details