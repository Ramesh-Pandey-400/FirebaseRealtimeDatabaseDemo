package com.rameshpandey.firebaserealtimedatabasedemo.model;

public class User {
    String userID;
    String userName;
    String userOccupation;

    public User(){

    }

    public User(String userID, String userName, String userOccupation) {
        this.userID = userID;
        this.userName = userName;
        this.userOccupation = userOccupation;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserOccupation() {
        return userOccupation;
    }
}
