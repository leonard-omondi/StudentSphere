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

    public Account accountRegistration(String username, String password) {
        if (username.trim().isBlank() || password.length() < 4) {
            return null;
        }
        if (accountExists(username)) {
            return null;
        }
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO account(username, password) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            while (rs.next()) {
                long account_id = rs.getLong(1);
                Account account = new Account(account_id, username, password);
                return account;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public boolean accountExists(String username) {
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement ps = connection.prepareStatement("SELECT 1 FROM account WHERE username=?")) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean isAuthenticated(String username, String password) {
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement ps = connection
                        .prepareStatement("SELECT * FROM account WHERE username=? AND password=?")) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public Account accountLogin(String username, String password) {
        if (isAuthenticated(username, password)) {
            try (Connection connection = ConnectionUtil.getConnection();
                    PreparedStatement ps = connection
                            .prepareStatement("SELECT * FROM account WHERE username=? AND password=?")) {
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    long account_id = rs.getLong(1);
                    String accUsername = rs.getString(2);
                    String accPassword = rs.getString(3);
                    Account account = new Account(account_id, accUsername, accPassword);
                    return account;
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return null;

    }

    public boolean isAccountIdExists(long account_id) {
        String sql = "SELECT 1 FROM account WHERE account_id=?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, account_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;

    }

}
