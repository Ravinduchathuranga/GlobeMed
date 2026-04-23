package com.rc008code.hms.dto;

import java.util.Date;

public class PrescriptionDto {
    private String prescription_id;
    private String record_id;
    private String pharmacist_id;
    private String patient_id;
    private String medication;
    private String dosage;
    private Date issue_date;

    public PrescriptionDto() {
    }

    public PrescriptionDto(String prescription_id, String record_id, String pharmacist_id, String patient_id, String medication, String dosage, Date issue_date) {
        this.prescription_id = prescription_id;
        this.record_id = record_id;
        this.pharmacist_id = pharmacist_id;
        this.patient_id = patient_id;
        this.medication = medication;
        this.dosage = dosage;
        this.issue_date = issue_date;
    }

    public String getPrescription_id() {
        return prescription_id;
    }

    public void setPrescription_id(String prescription_id) {
        this.prescription_id = prescription_id;
    }

    public String getRecord_id() {
        return record_id;
    }

    public void setRecord_id(String record_id) {
        this.record_id = record_id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getPharmacist_id() {
        return pharmacist_id;
    }

    public void setPharmacist_id(String pharmacist_id) {
        this.pharmacist_id = pharmacist_id;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public Date getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(Date issue_date) {
        this.issue_date = issue_date;
    }
}
