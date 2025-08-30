package com.rc008code.hms.entity;

import com.rc008code.hms.enums.PaymentStatus;
import com.rc008code.hms.enums.PaymentTypes;

public class Payment {
    private String payment_id;
    private String patient_id;
    private String appointment_id;
    private double amount;
    private String payment_date;
    private PaymentTypes method;
    private PaymentStatus status;

    public Payment() {
    }

    public Payment(String payment_id, String patient_id, String appointment_id, double amount, String payment_date, PaymentTypes method, PaymentStatus status) {
        this.payment_id = payment_id;
        this.patient_id = patient_id;
        this.appointment_id = appointment_id;
        this.amount = amount;
        this.payment_date = payment_date;
        this.method = method;
        this.status = status;
    }


    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(String appointment_id) {
        this.appointment_id = appointment_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public PaymentTypes getMethod() {
        return method;
    }

    public void setMethod(PaymentTypes method) {
        this.method = method;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
