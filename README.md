# StudentSphere Social Media Blog API

This project is a fully functional API for a hypothetical social media application called StudentSphere. It is designed to manage user accounts and messages within the application. StudentSphere allows users to register, log in, create, update, delete, and retrieve messages. The project uses Maven, the Javalin web framework, and MySQL as the database.

## Features

### 1. User Registration

- Users can create a new account by sending a POST request to `localhost:8080/register`.
- Registration is successful if the username is not blank, the password is at least four characters long, and the username does not already exist.
- The response contains the newly created account with its `account_id`.

### 2. User Login

- Users can verify their login by sending a POST request to `localhost:8080/login`.
- Login is successful if the provided username and password match an existing account.
- The response contains the user account with its `account_id`.

### 3. Create Messages

- Users can submit new posts by sending a POST request to `localhost:8080/messages`.
- Message creation is successful if the text is not blank, under 255 characters, and posted by an existing user.
- The response contains the newly created message with its `message_id`.

### 4. Retrieve All Messages

- Users can retrieve all messages by sending a GET request to `localhost:8080/messages`.
- The response contains a JSON representation of a list containing all messages retrieved from the database.

### 5. Retrieve Message by ID

- Users can retrieve a message by its ID by sending a GET request to `localhost:8080/messages/{message_id}`.
- The response contains a JSON representation of the message identified by the `message_id`.

### 6. Delete Message by ID

- Users can delete a message by its ID by sending a DELETE request to `localhost:8080/messages/{message_id}`.
- If the message exists, the response contains the deleted message.

### 7. Update Message Text by ID

- Users can update a message's text by sending a PATCH request to `localhost:8080/messages/{message_id}`.
- The update is successful if the message ID exists, and the new text is not blank and not over 255 characters.
- The response contains the entire updated message.

### 8. Retrieve Messages by User

- Users can retrieve all messages posted by a particular user by sending a GET request to `localhost:8080/accounts/{account_id}/messages`.
- The response contains a JSON representation of a list containing all messages the specified user posts.

## Project Structure

The project follows a 3-layer architecture, including DAO and Service classes to manage data persistence and business logic.

## Database Configuration

To run this project, you will need to provide your MySQL username and password in the configuration file.

1. Open the `src/main/resources/application.properties` file.
2. Replace `your-mysql-username` and `your-mysql-password` with your MySQL credentials:

```properties
spring.datasource.username=your-mysql-username
spring.datasource.password=your-mysql-password



```
