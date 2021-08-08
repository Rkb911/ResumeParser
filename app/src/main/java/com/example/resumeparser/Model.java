package com.example.resumeparser;

public class Model {

    String name, email, phone, skills, designation, degree, college, experience;

    public Model() {

    }

    public Model(String name, String email, String phone, String skills, String designation, String degree, String college, String experience) {

        this.name = name;
        this.email = email;
        this.phone = phone;
        this.skills = skills;
        this.designation = designation;
        this.degree = degree;
        this.college = college;
        this.experience = experience;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
