package com.rc008code.hms.payments.service;

import com.rc008code.hms.payments.api.PaymentProcessor;
import com.rc008code.hms.payments.model.PaymentContext;
import com.rc008code.hms.payments.model.PaymentResult;

import java.util.UUID;

/**
 * ConcreteImplementor: simulates insurance claim processing.
 */
public class InsurancePaymentProcessor implements PaymentProcessor {
    @Override
    public PaymentResult process(PaymentContext context) {
        if (context == null || context.getAmount() <= 0) {
            return PaymentResult.failed("Invalid amount");
        }
        // Simulate slower approval; for demo we approve amounts up to 50000
        boolean approved = context.getAmount() <= 50000;
        if (!approved) {
            return PaymentResult.failed("Insurance claim denied");
        }
        String txId = "INS-" + UUID.randomUUID();
        return PaymentResult.success(txId, "Insurance claim approved");
    }
}
