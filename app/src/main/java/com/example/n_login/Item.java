package com.example.n_login;

public class Item {
    String pw;
    String name;
    int q_num;
    String q_answer;
    String birth;

    public Item(){

    }

    public Item(String Pw, String name, int q_num, String q_answer, String birth){
        this.pw = Pw;
        this.name = name;
        this.q_num = q_num;
        this.q_answer = q_answer;
        this.birth = birth;
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

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getBirth() {
        return birth;
    }
}

