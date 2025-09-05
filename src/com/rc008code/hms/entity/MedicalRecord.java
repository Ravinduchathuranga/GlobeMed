package com.rc008code.hms.entity;

import java.util.Date;

public class MedicalRecord {
    private String record_id;
    private String patient_id;
    private String doctor_id;
    private String diagnosis;
    private String treatment;
    private Date record_date;


    public MedicalRecord() {
    }

    public MedicalRecord(String record_id, String patient_id, String doctor_id, String diagnosis, String treatment, Date record_date) {
        this.record_id = record_id;
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.record_date = record_date;
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

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public Date getRecord_date() {
        return record_date;
    }

    public void setRecord_date(Date record_date) {
        this.record_date = record_date;
    }
}
