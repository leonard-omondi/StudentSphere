package com.leonard.model;

import java.util.Objects;

public class Account {
    private long account_id;
    private String username;
    private String password;

    public Account() {
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account(long account_id, String username, String password) {
        this.account_id = account_id;
        this.username = username;
        this.password = password;
    }

    public long getAccount_id() {
        return this.account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account account_id(long account_id) {
        setAccount_id(account_id);
        return this;
    }

    public Account username(String username) {
        setUsername(username);
        return this;
    }

    public Account password(String password) {
        setPassword(password);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Account)) {
            return false;
        }
        Account account = (Account) o;
        return account_id == account.account_id && Objects.equals(username, account.username)
                && Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account_id, username, password);
    }

    @Override
    public String toString() {
        return "{" +
                " account_id='" + getAccount_id() + "'" +
                ", username='" + getUsername() + "'" +
                ", password='" + getPassword() + "'" +
                "}";
    }

}
