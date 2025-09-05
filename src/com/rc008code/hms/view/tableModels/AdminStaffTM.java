package com.rc008code.hms.view.tableModels;

import javafx.scene.control.ButtonBar;

public class AdminStaffTM {
    private String staffId;
    private String name;
    private String address;
    private String contact;
    private String email;
    private String role;
    private ButtonBar buttonBar;

    public AdminStaffTM() {
    }

    public AdminStaffTM(String staffId, String name, String address, String contact, String email, String role, ButtonBar buttonBar) {
        this.staffId = staffId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.role = role;
        this.buttonBar = buttonBar;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ButtonBar getButtonBar() {
        return buttonBar;
    }

    public void setButtonBar(ButtonBar buttonBar) {
        this.buttonBar = buttonBar;
    }
}
