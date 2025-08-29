package com.rc008code.hms.dto;

public class PharmacistDto {
    private String pharmacistId;
    private String name;
    private String contactNumber;
    private String email;
    private String password;

    public PharmacistDto() {
    }

    public PharmacistDto(String pharmacistId, String name, String contactNumber, String email, String password) {
        this.pharmacistId = pharmacistId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
        this.password = password;
    }

    public String getPharmacistId() {
        return pharmacistId;
    }

    public void setPharmacistId(String pharmacistId) {
        this.pharmacistId = pharmacistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
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
