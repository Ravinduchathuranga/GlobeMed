package com.rc008code.hms.entity;

public class Patient {
    private String patientId;
    private String name;
    private String address;
    private int age;
    private String contact;
    private String email;

    public Patient() {
    }

    public Patient(String patientId, String name, String address, int age, String contact, String email) {
        this.patientId = patientId;
        this.name = name;
        this.address = address;
        this.age = age;
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
