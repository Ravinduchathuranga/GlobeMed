package com.rc008code.hms.payments.model;

import com.rc008code.hms.enums.PaymentStatus;

public class PaymentResult {
    private PaymentStatus status;
    private String message;
    private String transactionId;

    public PaymentResult() {}

    public PaymentResult(PaymentStatus status, String message, String transactionId) {
        this.status = status;
        this.message = message;
        this.transactionId = transactionId;
    }

    public static PaymentResult success(String transactionId, String message) {
        return new PaymentResult(PaymentStatus.COMPLETED, message, transactionId);
    }

    public static PaymentResult failed(String message) {
        return new PaymentResult(PaymentStatus.FAILED, message, null);
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
