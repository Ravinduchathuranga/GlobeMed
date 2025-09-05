package com.rc008code.hms.view.tableModels;

import javafx.scene.control.ButtonBar;

public class DoctorTM {
    private String doctorId;
    private String name;
    private String specialization;
    private String contact;
    private String email;
    private String availableTime;
    private ButtonBar buttonBar;

    public DoctorTM() {
    }

    public DoctorTM(String doctorId, String name, String specialization, String contact, String email, String availableTime, ButtonBar buttonBar) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.contact = contact;
        this.email = email;
        this.availableTime = availableTime;
        this.buttonBar = buttonBar;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
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

    public String getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(String availableTime) {
        this.availableTime = availableTime;
    }

    public ButtonBar getButtonBar() {
        return buttonBar;
    }

    public void setButtonBar(ButtonBar buttonBar) {
        this.buttonBar = buttonBar;
    }
}
