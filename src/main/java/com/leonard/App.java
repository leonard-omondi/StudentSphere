package com.leonard;

import com.leonard.controller.Controller;
import com.leonard.dao.AccountDAO;
import com.leonard.dao.MessageDAO;
import com.leonard.model.Account;
import com.leonard.util.Database;

public class App {
    public static void main(String[] args) {

        Database.setup();
        Controller controller = new Controller();
        controller.setup();

        // MessageDAO messageDAO = new MessageDAO();
        // messageDAO.updateMessageByID(2, "PATCH test was successful!");

        // AccountDAO accountDAO = new AccountDAO();

        // String username = "Ultrons";
        // String password = "password3";

        // boolean userExists = accountDAO.isUserIdExists(2);
        // System.out.println(userExists);

        // boolean userExists2 = accountDAO.isAccountIdExists(14);
        // System.out.println(userExists2);

        // Account loggedInAccount = accountDAO.accountLogin(username, password);
        // if (loggedInAccount != null) {
        // System.out.println("Login successful. User details:");
        // System.out.println("Account ID: " + loggedInAccount.getAccount_id());
        // System.out.println("Username: " + loggedInAccount.getUsername());
        // System.out.println("Password: " + loggedInAccount.getPassword());
        // } else {
        // System.out.println("Login failed. Invalid username or password.");

        // }
        // boolean userExists = accountDAO.accountExists(username);
        // System.out.println(userExists);
        // System.out.println("------------------");
        // boolean isVerified = accountDAO.isAuthenticated(username, password);
        // System.out.println(isVerified);
        // accountDAO.accountRegistration("Batman", "darkmode");

    }
}
/*
 * Javalin app = Javalin.create().start(9000);
 * 
 * Connection connection = ConnectionUtil.getConnection();
 * 
 * if (connection != null) {
 * System.out.println("Database connected successfully.");
 * // Close the connection when done
 * try {
 * connection.close();
 * } catch (Exception e) {
 * e.printStackTrace();
 * }
 * } else {
 * System.out.println("Failed to connect to the database.");
 * }
 */