package com.rc008code.hms.view.tableModels;

import javafx.scene.control.ButtonBar;

public class NurseTM {
    private String nurseId;
    private String name;
    private String contact;
    private String email;
    private String department;
    private ButtonBar buttonBar;

    public NurseTM() {
    }

    public NurseTM(String nurseId, String name, String contact, String email, String department, ButtonBar buttonBar) {
        this.nurseId = nurseId;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.department = department;
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

    public ButtonBar getButtonBar() {
        return buttonBar;
    }

    public void setButtonBar(ButtonBar buttonBar) {
        this.buttonBar = buttonBar;
    }
}
