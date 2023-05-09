package com.example.kadai2;

public class User {
    private int id;
    private String company;
    private String name;
    private int score;



    public User(String name, int score, String company, int id){
        this.id = id;
        this.company = company;
        this.name = name;
        this.score = score;


    }

    public int getId() {
        return id;
    }
    public String getCompany() {
        return company;
    }
    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }


    public void setCompany(String company) {
        this.company = company;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
