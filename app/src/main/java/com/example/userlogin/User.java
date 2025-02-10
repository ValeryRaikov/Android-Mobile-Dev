package com.example.userlogin;


public class User {
    int _id;
    String _UserName;
    String _password;

    public User() {}

    public User(int id, String Username, String _pass) {
        this._id = id;
        this._UserName = Username;
        this._password = _pass;
    }

    public User(String Username, String _pass) {
        this._UserName = Username;
        this._password = _pass;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getUserName() {
        return _UserName;
    }

    public void setUserName(String _UserName) {
        this._UserName = _UserName;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String _password) {
        this._password = _password;
    }
}
