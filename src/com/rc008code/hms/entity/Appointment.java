package com.rc008code.hms.entity;

import com.rc008code.hms.enums.AppointmentStatus;

import java.util.Date;

public class Appointment {
    private String appointment_id;
    private String patient_id;
    private String doctor_id;
    private String admin_staff_id;
    private Date appointment_date;
    private AppointmentStatus status;

    public Appointment() {
    }

    public Appointment(String appointment_id, String patient_id, String doctor_id, String admin_staff_id, Date appointment_date, AppointmentStatus status) {
        this.appointment_id = appointment_id;
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.admin_staff_id = admin_staff_id;
        this.appointment_date = appointment_date;
        this.status = status;
    }

    public String getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(String appointment_id) {
        this.appointment_id = appointment_id;
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

    public String getAdmin_staff_id() {
        return admin_staff_id;
    }

    public void setAdmin_staff_id(String admin_staff_id) {
        this.admin_staff_id = admin_staff_id;
    }

    public Date getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(Date appointment_date) {
        this.appointment_date = appointment_date;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}
