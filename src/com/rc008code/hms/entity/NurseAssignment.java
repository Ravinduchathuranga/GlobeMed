package com.rc008code.hms.entity;

import java.util.Date;

public class NurseAssignment {
    private String assignment_id;
    private String nurse_id;
    private String patient_id;
    private Date assigned_date;

    public NurseAssignment() {
    }

    public NurseAssignment(String assignment_id, String nurse_id, String patient_id, Date assigned_date) {
        this.assignment_id = assignment_id;
        this.nurse_id = nurse_id;
        this.patient_id = patient_id;
        this.assigned_date = assigned_date;
    }

    public String getAssignment_id() {
        return assignment_id;
    }

    public void setAssignment_id(String assignment_id) {
        this.assignment_id = assignment_id;
    }

    public String getNurse_id() {
        return nurse_id;
    }

    public void setNurse_id(String nurse_id) {
        this.nurse_id = nurse_id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public Date getAssigned_date() {
        return assigned_date;
    }

    public void setAssigned_date(Date assigned_date) {
        this.assigned_date = assigned_date;
    }
}
