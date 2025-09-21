package com.rc008code.hms.payments.service;

import com.rc008code.hms.payments.api.PaymentProcessor;
import com.rc008code.hms.payments.model.PaymentContext;
import com.rc008code.hms.payments.model.PaymentResult;

/**
 * RefinedAbstraction: Cash payment using a provided processor (usually CashPaymentProcessor).
 */
public class CashPayment extends Payment {
    public CashPayment(PaymentProcessor processor) {
        super(processor);
    }

    @Override
    public PaymentResult pay(PaymentContext context) {
        return processor.process(context);
    }
}
