package com.example.coursereviewhw7;

public class Course {
    String department;
    int catalogNumber;

    public Course(String department, int catalogNumber){
        this.department = department;
        this.catalogNumber = catalogNumber;
    }

    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department){
        this.department = department;
    }
    public int getCatalogNumber(){
        return catalogNumber;
    }
    public void setCatalogNumber(int catalogNumber){
        this.catalogNumber = catalogNumber;
    }

    @Override
    public int hashCode() {
        String hashString = department + " " + catalogNumber;
        return hashString.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Course)) {
            return false;
        }
        Course otherCourse = (Course)other;
        return this.catalogNumber == otherCourse.catalogNumber && this.department.equals(otherCourse.department);
    }
}
