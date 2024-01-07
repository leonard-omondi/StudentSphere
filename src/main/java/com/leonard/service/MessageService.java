package com.leonard.service;

import java.util.ArrayList;

import com.leonard.dao.AccountDAO;
import com.leonard.dao.MessageDAO;
import com.leonard.model.Message;

public class MessageService {

    private MessageDAO messageDAO;
    private AccountDAO accountDAO;

    public MessageService() {
        this.messageDAO = new MessageDAO();
        this.accountDAO = new AccountDAO();
    }

    public ArrayList<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(long message_id) {
        return messageDAO.getMessageById(message_id);
    }

    public Message deleteMessageById(long message_id) {
        return messageDAO.deleteMessageById(message_id);
    }

    public Message createMessage(Message newMessage) {
        return messageDAO.createMessage(newMessage);
    }

    public ArrayList<Message> getAllMessagesFromUser(long account_id) {
        return messageDAO.getAllMessageFromUser(account_id);
    }

    public Message updateMessageByID(long message_id, String message_text) {
        return messageDAO.updateMessageByID(message_id, message_text);
    }
}
