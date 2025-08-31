package com.rc008code.hms.dto;

public class AdminStaffDto {
    private String staffId;
    private String name;
    private String contactNumber;
    private String email;
    private String password;

    public AdminStaffDto() {
    }

    public AdminStaffDto(String staffId, String name, String contactNumber, String email, String password) {
        this.staffId = staffId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
        this.password = password;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
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
