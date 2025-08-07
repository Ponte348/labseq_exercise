# labseq_exercise
REST service built with the Quarkus framework that implements a simple sequence and returns the requested values.

Made by: Pedro Ponte  
Contacts: [LinkedIn](https://www.linkedin.com/in/ponte348/), [Email](mailto:ponte@ua.pt)

## Architecture
The application is structured as follows:
- **Backend**: service built with Quarkus that handles the sequence logic
- **Frontend**: Angular application that provides a user interface for interacting with the backend service
- **Docker**: used to containerize the application for easy deployment and portability

The application meets the requirements of the exercise:
1. Service built using quarkus that returns the requested sequence
2. Endpoints follow the desired convention
3. Caching is used both for the sequence and the API response using @CacheResult
4. Can return *l(100000)* in way less than 10 seconds

Any other questions, contact me.

## Requirements
- Docker

## Running the application
1. Clone the repository:
    ```bash
    git clone git@github.com:Ponte348/labseq_exercise.git
    ```
2. Navigate to the project directory:
    ```bash
    cd labseq_exercise
    ```
3. Start the application using Docker Compose:
    ```bash
    docker compose up --build
    ```

4. Test the service:

    i. Access the application at `http://localhost` or

    ii. Use curl:
    ```bash
    curl http://localhost:8080/labseq/100000
    ```

## Swagger Documentation
Access [Swagger documentation](http://localhost:8080/swagger). (Must be running the application first)