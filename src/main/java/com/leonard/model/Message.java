package com.leonard.model;

import java.util.Objects;

public class Message {
    private long message_id;
    private long posted_by;
    private String message_text;
    private long time_posted_epoch;

    public Message() {
    }

    public Message(long posted_by, String message_text, long time_posted_epoch) {
        this.posted_by = posted_by;
        this.message_text = message_text;
        this.time_posted_epoch = time_posted_epoch;
    }

    public Message(long message_id, long posted_by, String message_text, long time_posted_epoch) {
        this.message_id = message_id;
        this.posted_by = posted_by;
        this.message_text = message_text;
        this.time_posted_epoch = time_posted_epoch;
    }

    public long getMessage_id() {
        return this.message_id;
    }

    public void setMessage_id(long message_id) {
        this.message_id = message_id;
    }

    public long getPosted_by() {
        return this.posted_by;
    }

    public void setPosted_by(long posted_by) {
        this.posted_by = posted_by;
    }

    public String getMessage_text() {
        return this.message_text;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }

    public long getTime_posted_epoch() {
        return this.time_posted_epoch;
    }

    public void setTime_posted_epoch(long time_posted_epoch) {
        this.time_posted_epoch = time_posted_epoch;
    }

    public Message message_id(long message_id) {
        setMessage_id(message_id);
        return this;
    }

    public Message posted_by(long posted_by) {
        setPosted_by(posted_by);
        return this;
    }

    public Message message_text(String message_text) {
        setMessage_text(message_text);
        return this;
    }

    public Message time_posted_epoch(long time_posted_epoch) {
        setTime_posted_epoch(time_posted_epoch);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Message)) {
            return false;
        }
        Message message = (Message) o;
        return message_id == message.message_id && posted_by == message.posted_by
                && Objects.equals(message_text, message.message_text) && time_posted_epoch == message.time_posted_epoch;
    }

    @Override
    public int hashCode() {
        return Objects.hash(message_id, posted_by, message_text, time_posted_epoch);
    }

    @Override
    public String toString() {
        return "{" +
                " message_id='" + getMessage_id() + "'" +
                ", posted_by='" + getPosted_by() + "'" +
                ", message_text='" + getMessage_text() + "'" +
                ", time_posted_epoch='" + getTime_posted_epoch() + "'" +
                "}";
    }

}
