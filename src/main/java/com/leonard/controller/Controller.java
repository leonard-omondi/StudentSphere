package com.leonard.controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.ArrayList;

import com.leonard.model.Account;
import com.leonard.model.Message;
import com.leonard.service.AccountService;
import com.leonard.service.MessageService;

public class Controller {
    private AccountService accountService;
    private MessageService messageService;

    public Controller() {
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }

    // Handlers
    public void setup() {
        Javalin app = Javalin.create().start(9000);
        app.get("/", ctx -> {
            System.out.println("Testing testing, anyone there?!");
            ctx.result("Hello World!");
        });
        app.get("/accounts", this::getAllAccounts);
        app.post("/register", this::accountRegistration);
        app.post("/login", this::accountLogin);
        app.get("/messages", this::getAllMessages);
        app.get("/messages/{message_id}", this::getMessageById);
        app.delete("/messages/{message_id}", this::deleteMessageById);
        app.post("/messages", this::createMessage);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesFromUser);
        app.patch("/messages/{message_id}", this::updateMessageByID);
    }

    private void updateMessageByID(Context ctx) {
        try {
            long message_id = Long.parseLong(ctx.pathParam("message_id"));
            String newMessage = ctx.body();
            if (isValidMessageText(newMessage)) {
                Message updatedMessage = messageService.updateMessageByID(message_id, newMessage);
                if (updatedMessage != null) {
                    ctx.json(updatedMessage).status(200);
                } else {
                    ctx.result("Failed to update message text").status(400); // Fix the typo here
                }
            } else {
                ctx.result("Invalid new message text").status(400);
            }
        } catch (NumberFormatException e) {
            // Invalid message_id in the path parameter
            ctx.result("Invalid message_id in the path: " + ctx.pathParam("message_id")).status(400);
        } catch (Exception e) {
            // Exception occurred
            ctx.result("Internal server error").status(500);
            e.printStackTrace();
        }
    }

    private boolean isValidMessageText(String messageText) {
        // Validate new message text
        return messageText != null
                && !messageText.trim().isEmpty()
                && messageText.length() <= 255;
    }

    private void getAllMessagesFromUser(Context ctx) {
        long account_id = Long.parseLong(ctx.pathParam("account_id"));
        ArrayList<Message> messages = messageService.getAllMessagesFromUser(account_id);
        ctx.json(messages).status(200);

    }

    private void createMessage(Context ctx) {
        try {
            // Parse the message from the request body
            Message newMessage = ctx.bodyAsClass(Message.class);

            // Validate the message_text length and user existence
            if (newMessage.getMessage_text() != null
                    && !newMessage.getMessage_text().trim().isEmpty()
                    && newMessage.getMessage_text().length() <= 255
                    && accountService.isAccountIdExists(newMessage.getPosted_by())) {

                // Create the message
                Message createdMessage = messageService.createMessage(newMessage);

                if (createdMessage != null) {
                    ctx.json(createdMessage).status(200);
                } else {
                    ctx.result("Message creation failed").status(400);
                }
            } else {
                ctx.result("Invalid message or user does not exist").status(400);
            }
        } catch (Exception e) {
            ctx.result("Server error").status(500);
        }
    }

    private void deleteMessageById(Context ctx) {
        long idFromPath = Long.parseLong(ctx.pathParam("message_id"));
        Message deletedMessage = messageService.deleteMessageById(idFromPath);
        if (deletedMessage != null) {
            ctx.json(deletedMessage).status(200);
        } else {
            ctx.result("Message not found or deleted").status(404);
        }
    }

    private void getMessageById(Context ctx) {
        long idFromPath = Long.parseLong(ctx.pathParam("message_id"));
        Message message = messageService.getMessageById(idFromPath);
        if (message != null) {
            ctx.json(message).status(200);
        } else {
            ctx.result("No message associated with this id").status(400);
        }
    }

    private void getAllMessages(Context ctx) {
        ArrayList<Message> messages = messageService.getAllMessages();
        ctx.json(messages).status(200);
    }

    private void accountLogin(Context ctx) {
        Account accountInfo = ctx.bodyAsClass(Account.class);
        Account loggedInAccount = accountService.accountLogin(accountInfo.getUsername(), accountInfo.getPassword());
        if (loggedInAccount != null) {
            ctx.json(loggedInAccount).status(200);
        } else {
            ctx.result("Unauthorized login attempt").status(401);
        }
    }

    private void getAllAccounts(Context ctx) {
        ArrayList<Account> accounts = accountService.getAllAccounts();
        ctx.json(accounts).status(200);
    }

    private void accountRegistration(Context ctx) {
        Account accountInfo = ctx.bodyAsClass(Account.class);
        Account accountRegistered = accountService.accountRegistration(accountInfo);
        if (accountRegistered != null) {
            ctx.json(accountRegistered).status(200);
        } else {
            ctx.result("Client Error").status(400);
        }

    }

}
/*
 * private void updateMessageByID(Context ctx) {
 * try {
 * long message_id = Long.parseLong(ctx.pathParam("message_id"));
 * String newMessage = ctx.body();
 * if (isValidMessageText(newMessage)) {
 * Message updatedMessage = messageService.updateMessageByID(message_id,
 * newMessage);
 * if (updatedMessage != null) {
 * ctx.json(updatedMessage).status(200);
 * } else {
 * ctx.result("Failed to update message text").status(400); // Fix the typo here
 * }
 * } else {
 * ctx.result("Invalid new message text").status(400);
 * }
 * } catch (NumberFormatException e) {
 * // Invalid message_id in the path parameter
 * ctx.result("Invalid message_id in the path: " +
 * ctx.pathParam("message_id")).status(400);
 * } catch (Exception e) {
 * // Exception occurred
 * ctx.result("Internal server error").status(500);
 * e.printStackTrace();
 * }
 * }
 * 
 * private boolean isValidMessageText(String messageText) {
 * // Validate new message text
 * return messageText != null
 * && !messageText.trim().isEmpty()
 * && messageText.length() <= 255;
 * }
 */