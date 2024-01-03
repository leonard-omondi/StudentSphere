package com.leonard;

import com.leonard.controller.Controller;
import com.leonard.util.Database;

public class App {
    public static void main(String[] args) {

        Database.setup();
        Controller controller = new Controller();
        controller.setup();

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