package com.example.n_login;

public class Item {
    String id;
    String pw;
    String name;
    int q_num;
    String q_answer;

    public Item(){

    }

    public Item(String Id, String Pw, String name, int q_num, String q_answer){
        this.id = Id;
        this.pw = Pw;
        this.name = name;
        this.q_num = q_num;
        this.q_answer = q_answer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQ_num() {
        return q_num;
    }

    public void setQ_num(int q_num) {
        this.q_num = q_num;
    }

    public String getQ_answer() {
        return q_answer;
    }

    public void setQ_answer(String q_answer) {
        this.q_answer = q_answer;
    }
}
