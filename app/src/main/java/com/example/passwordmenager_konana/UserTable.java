package com.example.passwordmenager_konana;

public class UserTable {
    private String username, password;
    private int userID;

    public UserTable(String username, String password, int userID) {
        this.username = username;
        this.password = password;
        this.userID = userID;
    }

    public UserTable() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
