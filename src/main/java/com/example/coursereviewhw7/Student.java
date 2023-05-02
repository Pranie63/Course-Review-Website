package com.example.coursereviewhw7;

import java.util.HashMap;

public class Student {
    private String name;
    private String password;
    private HashMap<Course, Review> reviewList;

    public Student(String name, String password) {
        this.name = name;
        this.password = password;

    }

    public Student(String name, String password, HashMap<Course, Review> reviewList) {
        this.name = name;
        this.password = password;
        this.reviewList = reviewList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HashMap<Course, Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(HashMap<Course, Review> reviewList) {
        this.reviewList = reviewList;
    }
}
