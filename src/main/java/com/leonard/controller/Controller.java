package com.leonard.controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.ArrayList;

import com.leonard.model.Account;
import com.leonard.service.AccountService;
import com.leonard.service.MessageService;

public class Controller {
    private AccountService accountService;
    private MessageService messageService;

    public Controller() {
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }

    public void setup() {
        Javalin app = Javalin.create().start(9000);
        app.get("/", ctx -> {
            System.out.println("Testing testing, anyone there?!");
            ctx.result("Hello World!");
        });
        app.get("/accounts", this::getAllAccounts);
    }

    private void getAllAccounts(Context ctx) {
        ArrayList<Account> accounts = accountService.getAllAccounts();
        ctx.json(accounts).status(200);
    }

}
