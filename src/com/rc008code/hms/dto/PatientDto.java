package com.rc008code.hms.dto;

import com.rc008code.hms.enums.Gender;

public class PatientDto {
    private String patientId;
    private String name;
    private String address;
    private int age;
    private Gender gender;
    private String contact;
    private String email;

    public PatientDto() {
    }

    public PatientDto(String patientId, String name, String address, int age, Gender gender, String contact, String email) {
        this.patientId = patientId;
        this.name = name;
        this.address = address;
        this.age = age;
        this.gender = gender;
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
    
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
