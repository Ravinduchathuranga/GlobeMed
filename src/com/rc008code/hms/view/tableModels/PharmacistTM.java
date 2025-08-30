package com.rc008code.hms.view.tableModels;

import javafx.scene.control.ButtonBar;

public class PharmacistTM {
    private String pharmacistId;
    private String name;
    private String address;
    private String contact;
    private String email;
    private String qualification;
    private ButtonBar buttonBar;

    public PharmacistTM() {
    }

    public PharmacistTM(String pharmacistId, String name, String address, String contact, String email, String qualification, ButtonBar buttonBar) {
        this.pharmacistId = pharmacistId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.qualification = qualification;
        this.buttonBar = buttonBar;
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

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public ButtonBar getButtonBar() {
        return buttonBar;
    }

    public void setButtonBar(ButtonBar buttonBar) {
        this.buttonBar = buttonBar;
    }
}
