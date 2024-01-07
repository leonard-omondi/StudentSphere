package com.leonard.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/studentsphere";
    // Use your own MySQL Workbench username and password below
    private static final String USERNAME = "your_username_here";
    private static final String PASSWORD = "your_password_here";

    public static Connection getConnection() {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Database connected successfully? " + connection.isValid(10));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
}
