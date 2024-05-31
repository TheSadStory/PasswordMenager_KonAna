package com.example.passwordmenager_konana;

public class UserDataTable {
    private String platformName, username, password;
    private int userID;
    private int dataID;

    public UserDataTable(int dataID, String platformName, String username, String password, int userID) {
        this.dataID = dataID;
        this.platformName = platformName;
        this.username = username;
        this.password = password;
        this.userID = userID;
    }

    public UserDataTable() {
    }

    //GETTERS AND SETTERS

    public int getDataID() {
        return dataID;
    }

    public void setDataID(int dataID) {
        this.dataID = dataID;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
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
