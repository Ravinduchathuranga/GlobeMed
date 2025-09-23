package com.rc008code.hms.payments.service;

import com.rc008code.hms.payments.api.PaymentProcessor;
import com.rc008code.hms.payments.model.PaymentContext;
import com.rc008code.hms.payments.model.PaymentResult;

/**
 * RefinedAbstraction: Online payment using card/insurance processors.
 */
public class OnlinePayment extends Payment {
    public OnlinePayment(PaymentProcessor processor) {
        super(processor);
    }

    @Override
    public PaymentResult pay(PaymentContext context) {
        return processor.process(context);
    }
}
