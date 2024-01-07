package com.leonard.service;

import java.util.ArrayList;

import com.leonard.dao.AccountDAO;
import com.leonard.dao.MessageDAO;
import com.leonard.model.Account;

public class AccountService {

    private AccountDAO accountDAO;
    private MessageDAO messageDAO;

    public AccountService() {
        this.accountDAO = new AccountDAO();
        this.messageDAO = new MessageDAO();
    }

    public ArrayList<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }

    public Account accountRegistration(Account account) {
        return accountDAO.accountRegistration(account.getUsername(), account.getPassword());
    }

    public Account accountLogin(String username, String password) {
        return accountDAO.accountLogin(username, password);
    }

    public boolean isAccountIdExists(long account_id) {
        return accountDAO.isAccountIdExists(account_id);
    }
}
