package com.rc008code.hms.entity;

import com.rc008code.hms.enums.Gender;
import sun.net.www.content.text.Generic;

public class Patient {
    private String patientId;
    private String name;
    private String address;
    private int age;
    private Gender gender;
    private String contact;
    private String email;

    public Patient() {
    }

    public Patient(String patientId, String name, String address, int age, Gender gender,String contact, String email) {
        this.patientId = patientId;
        this.name = name;
        this.address = address;
        this.age = age;
        this.gender=gender;
        this.contact = contact;
        this.email = email;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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
}
