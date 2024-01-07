package com.leonard.dao;

import com.leonard.model.Message;
import com.leonard.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class MessageDAO {

    public ArrayList<Message> getAllMessages() {
        ArrayList<Message> messages = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM message")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long message_id = rs.getLong("message_id");
                long posted_by = rs.getLong("posted_by");
                String message_text = rs.getString("message_text");
                long time_posted_epoch = rs.getLong("time_posted_epoch");
                Message message = new Message(message_id, posted_by, message_text, time_posted_epoch);
                messages.add(message);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return messages;
    }

    public Message createMessage(Message newMessage) {
        try {
            // Check if the message_text is not blank and is under 255 characters
            if (newMessage.getMessage_text() != null && !newMessage.getMessage_text().trim().isEmpty()
                    && newMessage.getMessage_text().length() <= 255) {
                AccountDAO accountDAO = new AccountDAO();
                if (accountDAO.isAccountIdExists(newMessage.getPosted_by())) {
                    // Create new message
                    String sql = "INSERT INTO message(posted_by, message_text, time_posted_epoch) VALUES(?,?,?)";
                    try (Connection connection = ConnectionUtil.getConnection();
                            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                        ps.setLong(1, newMessage.getPosted_by());
                        ps.setString(2, newMessage.getMessage_text());
                        ps.setLong(3, System.currentTimeMillis());
                        int result = ps.executeUpdate();
                        if (result > 0) {
                            try (ResultSet pk = ps.getGeneratedKeys()) {
                                if (pk.next()) {
                                    long generatedId = pk.getLong(1);
                                    newMessage.setMessage_id(generatedId);
                                    return newMessage;
                                }
                            }
                        }
                    }
                } else {
                    System.out.println("User does not exist");
                }
            } else {
                System.out.println("Invalid message text");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return null;

    }

    public Message getMessageById(long message_id) {
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM message WHERE message_id=?")) {
            ps.setLong(1, message_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long messageId = rs.getLong("message_id");
                long posted_by = rs.getLong("posted_by");
                String message_text = rs.getString("message_text");
                long time_posted_epoch = rs.getLong("time_posted_epoch");
                Message message = new Message(messageId, posted_by, message_text, time_posted_epoch);
                return message;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return null;

    }

    public Message deleteMessageById(long message_id) {
        Message deletedMessage = getMessageById(message_id);
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement ps = connection.prepareStatement("DELETE FROM message WHERE message_id=?")) {
            ps.setLong(1, message_id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return deletedMessage;
            } else {
                System.out.println("Message not found");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;

    }

    public ArrayList<Message> getAllMessageFromUser(long account_id) {
        ArrayList<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM message WHERE posted_by=?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, account_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long message_id = rs.getLong("message_id");
                long posted_by = rs.getLong("posted_by");
                String message_text = rs.getString("message_text");
                long time_posted_epoch = rs.getLong("time_posted_epoch");
                Message message = new Message(message_id, posted_by, message_text, time_posted_epoch);
                messages.add(message);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return messages;

    }

    public Message updateMessageByID(long message_id, String message_text) {
        Message messageToUpdate = getMessageById(message_id);
        if (messageToUpdate != null) {
            if (message_text != null && !message_text.trim().isEmpty() && message_text.length() <= 255) {
                String sql = "UPDATE message SET message_text = ? WHERE message_id = ?";
                try (Connection connection = ConnectionUtil.getConnection();
                        PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setString(1, message_text);
                    ps.setLong(2, message_id);
                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected > 0) {
                        Message updatedMessage = getMessageById(message_id);
                        return updatedMessage;
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            } else {
                System.out.println("Invalid new message text");
            }
        } else {
            System.out.println("Message not found");
        }

        return null;

    }

}
