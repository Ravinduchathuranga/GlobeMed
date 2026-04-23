package com.rc008code.hms.payments.model;

import com.rc008code.hms.enums.PaymentTypes;


public class PaymentContext {
    private String patientId;
    private String appointmentId;
    private double amount;
    private PaymentTypes type;

    public PaymentContext() {}

    public PaymentContext(String patientId, String appointmentId, double amount, PaymentTypes type) {
        this.patientId = patientId;
        this.appointmentId = appointmentId;
        this.amount = amount;
        this.type = type;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentTypes getType() {
        return type;
    }

    public void setType(PaymentTypes type) {
        this.type = type;
    }
}
