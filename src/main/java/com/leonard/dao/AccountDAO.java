package com.leonard.dao;

import java.sql.*;
import java.util.ArrayList;

import com.leonard.model.Account;
import com.leonard.util.ConnectionUtil;

public class AccountDAO {

    // Get All Accounts
    public ArrayList<Account> getAllAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM account")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("account_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                Account account = new Account(id, username, password);
                accounts.add(account);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return accounts;

    }

}
