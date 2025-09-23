package com.rc008code.hms.payments.service;

import com.rc008code.hms.payments.api.PaymentProcessor;
import com.rc008code.hms.payments.model.PaymentContext;
import com.rc008code.hms.payments.model.PaymentResult;

import java.util.UUID;

/**
 * ConcreteImplementor: processes cash payments (trivially successful).
 */
public class CashPaymentProcessor implements PaymentProcessor {
    @Override
    public PaymentResult process(PaymentContext context) {
        String txId = "CASH-" + UUID.randomUUID();
        return PaymentResult.success(txId, "Cash payment accepted");
    }
}
