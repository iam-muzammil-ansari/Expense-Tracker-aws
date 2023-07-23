# Expense Tracker API - AWS

The Expense Tracker API is a Spring Boot-based API that allows users to track their expenses. It provides various endpoints for managing expenses and user authentication. Users can add expenses, retrieve expenses for specific dates, calculate total expenditure for a given month, and get expenses for the last three months.

## Table Of Content
- [Frameworks and Language Used](#frameworks-and-language-used)
- [Data Flow](#data-flow)
    1. [Controller](#controller)
    2. [Services](#services)
    3. [Repository](#repository)
    4. [Utility](#utility)
    5. [Database Design](#database-design)
- [Data Structure Used](#data-structure-used)
- [Project Summary](#project-summary)
- [Getting Started](#getting-started)
- [Testing Endpoints](#testing-endpoints)
- [License](#license)

## Frameworks and Language Used
- Spring Boot (Version 3.1.2)
- Java (Version 17)
- MySQL (Database)

## Data Flow

### Controller
- ExpenseController: Responsible for handling expense-related HTTP requests and responses.
- UserController: Responsible for handling user-related HTTP requests and responses.

### Services
- ExpenseService: Contains the business logic for expense-related operations.
- UserService: Contains the business logic for user-related operations.

### Repository
- AuthRepo: Repository interface for managing authentication tokens.
- ExpenseRepo: Repository interface for managing expenses.
- UserRepo: Repository interface for managing users.

### Utility
- EmailHandler: Utility class for sending emails.
- PasswordEncrypter: Utility class for encrypting passwords.

### Database Design
- The project uses the MySQL database to store user information and expense data.
- The main entities are User and Expense, and their relationships are defined in the model classes.

## Data Structure Used
- Data Transfer Objects (DTOs) are used for SignInInput and SignUpOutput classes.

## Project Summary
The Expense Tracker API is designed to help users manage their expenses efficiently. Users can register and sign in to their accounts to start tracking their expenses. The API provides endpoints to add new expenses, retrieve expenses for specific dates, calculate total expenditure for a given month, and get expenses for the last three months.

## Getting Started
To run the Expense Tracker API locally, follow these steps:
1. Clone the project from GitHub.
2. Set up a MySQL database and update the database configurations in the `application.properties` file.
3. Build and run the project using Maven.

## Testing Endpoints
You can test the API endpoints using tools like Postman or cURL. Refer to the API documentation for the available endpoints and their functionalities.
- `API Endpoints`

### User Endpoints

#### Register User
- URL : `POST /user/register`
- Description : Registers a new user with the provided details.
- Request Body : User object with userName, email, and password.
- Response : SignUpOutput object with signUpStatus and signUpStatusMessage.

#### Sign In User
- URL : `POST /user/signin`
- Description : Signs in an existing user with the provided email and password.
- Request Body : SignInInput object with email and password.
- Response : A token will be sent to the user's email upon successful sign-in.

#### Sign Out User
- URL : `DELETE /user/signout`
- Description : Signs out the user associated with the provided email and token.
- Request Parameters : email (String) and token (String).
- Response : A message indicating successful sign-out or an error message.

### Expense Endpoints

#### Add Expense
- URL : `POST /expenses`
- Description : Adds a new expense for the user associated with the provided email and token.
- Request Body : Expense object with title, description, price, date, and time.
- Request Parameters : email (String) and token (String).
- Response : A message indicating successful expense creation or an error message.

#### Get Expenses By Date
- URL : `GET /expenses`
- Description : Retrieves all expenses of the user associated with the provided email and token for a specific date.
- Request Parameters : email (String), token (String), and date (LocalDate).
- Response : List of Expense objects for the specified date.

#### Get Total Expenditure for a Month
- URL : `GET /expenses/total`
- Description : Calculates the total expenditure of the user associated with the provided email and token for a specific month.
- Request Parameters : email (String), token (String), year (int), and month (int).
- Response : A message containing the total expenditure for the specified month and year or an error message.

#### Get Expenses for Last Three Months
- URL : `GET /expenses/lastThreeMonths`
- Description : Retrieves all expenses of the user associated with the provided email and token for the last three months.
- Request Parameters : email (String) and token (String).
- Response : List of Expense objects for the last three months.

---
Note : Make sure to replace placeholders (e.g., `<email>`, `<token>`, etc.) in the actual API documentation with actual values. The provided information is based on the code and details available in the provided files. Ensure that all relevant details are updated in the actual API documentation when deploying the application.


## License
This project is not licensed.

