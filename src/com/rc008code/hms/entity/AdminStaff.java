package com.rc008code.hms.entity;

public class AdminStaff {
    private String staff_id;
    private String name;
    private String contact_number;
    private String email;
    private String password;

    public AdminStaff() {
    }

    public AdminStaff(String staff_id, String name, String contact_number, String email) {
        this.staff_id = staff_id;
        this.name = name;
        this.contact_number = contact_number;
        this.email = email;

    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
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
