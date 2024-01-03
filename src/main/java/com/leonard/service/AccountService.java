package com.leonard.service;

import java.util.ArrayList;

import com.leonard.dao.AccountDAO;
import com.leonard.model.Account;

public class AccountService {

    private AccountDAO accountDAO;
    private MessageDAO messageDao;

    public AccountService() {
        this.accountDAO = new AccountDAO();
        this.messageDao = new MessageDAO();
    }

    public ArrayList<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }
}
