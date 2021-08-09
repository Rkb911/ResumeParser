package com.example.resumeparser;

public class Models {

    String name, email, phone, skills, designation, degree, college, experience, timestamp, resume, text;
    Integer id;
    Boolean is_parsed;

    public Models() {

    }




    public Models(String name, String email, String phone, String skills, String designation, String degree,
                  String college, String experience, Integer id, Boolean is_parsed, String timestamp, String resume, String text) {

        this.name = name;
        this.email = email;
        this.phone = phone;
        this.skills = skills;
        this.designation = designation;
        this.degree = degree;
        this.college = college;
        this.experience = experience;
        this.id = id;
        this.is_parsed = is_parsed;
        this.timestamp = timestamp;
        this.resume = resume;
        this.text = text;

    }

    /*"id": 7,
            "timestamp": "2021-07-28T15:08:00.212925+05:30",
            "resume": "/media/files/2Ben.pdf",
            "text": null,
            "name": null,
            "phone": null,
            "email": null,
            "skills": null,
            "designation": null,
            "degree": null,
            "college": null,
            "experience": null,
            "is_parsed": false*/

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIs_parsed() {
        return is_parsed.toString();
    }

    public void setIs_parsed(Boolean is_parsed) {
        this.is_parsed = is_parsed;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
