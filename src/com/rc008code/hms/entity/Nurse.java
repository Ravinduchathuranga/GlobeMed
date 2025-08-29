package com.rc008code.hms.entity;

import com.rc008code.hms.enums.Departments;

public class Nurse {
    private String nurseId;
    private String name;
    private String contact;
    private Departments department;
    private String email;
    private String password;

    public Nurse() {
    }

    public Nurse(String nurseId, String name, Departments department, String contact, String email, String password) {
        this.nurseId = nurseId;
        this.name = name;
        this.department = department;
        this.contact = contact;
        this.email = email;
        this.password = password;
    }

    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
