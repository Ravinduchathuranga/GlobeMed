package com.rc008code.hms.entity;

import com.rc008code.hms.enums.Departments;

public class Doctor {
    private String doctorId;
    private String name;
    private String specialty;
    private String contact;
    private Departments department;
    private String email;
    private String password;

    public Doctor() {
    }

    public Doctor(String doctorId, String name, String specialty, String contact, Departments department, String email, String password) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialty = specialty;
        this.contact = contact;
        this.department = department;
        this.email = email;
        this.password = password;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
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
