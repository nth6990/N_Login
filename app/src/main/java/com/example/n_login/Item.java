package com.example.n_login;

public class Item {
    String id;
    String pw;
    public Item(){

    }

    public Item(String Id, String Pw){
        this.id = Id;
        this.pw = Pw;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
