package com.rc008code.hms.payments.service;

import com.rc008code.hms.payments.api.PaymentProcessor;
import com.rc008code.hms.payments.model.PaymentContext;
import com.rc008code.hms.payments.model.PaymentResult;


public abstract class Payment {
    protected final PaymentProcessor processor;

    protected Payment(PaymentProcessor processor) {
        this.processor = processor;
    }


    public abstract PaymentResult pay(PaymentContext context);
}
