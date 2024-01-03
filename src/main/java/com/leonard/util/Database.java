package com.leonard.util;

import java.sql.*;

public class Database {

    private static final String DATABASE_NAME = "studentsphere";
    private static final String TABLE_NAME1 = "account";
    private static final String TABLE_NAME2 = "message";

    public static void setup() {
        try (Connection connection = ConnectionUtil.getConnection();
                Statement statement = connection.createStatement()) {

            statement.executeUpdate("USE " + DATABASE_NAME);

            String createAccountTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME1 + " ("
                    + "account_id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "username VARCHAR(255) NOT NULL,"
                    + "password VARCHAR(255) NOT NULL"
                    + ")";
            statement.executeUpdate(createAccountTable);
            System.out.println("Table '" + TABLE_NAME1 + "' created successfully or already exists.");

            String createMessageTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME2 + " ("
                    + "message_id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "posted_by INT,"
                    + "message_text VARCHAR(255) NOT NULL,"
                    + "time_posted_epoch BIGINT,"
                    + "FOREIGN KEY (posted_by) REFERENCES " + TABLE_NAME1 + "(account_id)"
                    + ")";
            statement.executeUpdate(createMessageTable);
            System.out.println("Table '" + TABLE_NAME2 + "' created successfully or already exists.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
