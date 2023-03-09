package com.example.myapplication;

public class message {
    private String email;
    private String message;
    private String time;

    public message() {

    }
    public message(String email, String message, String time) {
        this.email = email;
        this.message = message;
        this.time = time;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getPass() {
//        return message;
//    }
//
//    public void setPass(String message) {

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
//        this.message = message;
//    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
