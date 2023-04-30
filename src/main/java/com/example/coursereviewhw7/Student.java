package com.example.coursereviewhw7;

import java.util.HashMap;

public class Student {
    private String name;
    private String passcode;
    private HashMap<Course, Review> reviewList;

    public Student(String name, String passcode, HashMap<Course, Review> reviewList) {
        this.name = name;
        this.passcode = passcode;
        this.reviewList = reviewList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public HashMap<Course, Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(HashMap<Course, Review> reviewList) {
        this.reviewList = reviewList;
    }
}
