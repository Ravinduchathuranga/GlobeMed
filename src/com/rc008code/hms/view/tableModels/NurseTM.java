package com.rc008code.hms.view.tableModels;

import javafx.scene.control.ButtonBar;

public class NurseTM {
    private String nurseId;
    private String name;
    private String address;
    private String contact;
    private String email;
    private String department;
    private String shift;
    private ButtonBar buttonBar;

    public NurseTM() {
    }

    public NurseTM(String nurseId, String name, String address, String contact, String email, String department, String shift, ButtonBar buttonBar) {
        this.nurseId = nurseId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.department = department;
        this.shift = shift;
        this.buttonBar = buttonBar;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public ButtonBar getButtonBar() {
        return buttonBar;
    }

    public void setButtonBar(ButtonBar buttonBar) {
        this.buttonBar = buttonBar;
    }
}
