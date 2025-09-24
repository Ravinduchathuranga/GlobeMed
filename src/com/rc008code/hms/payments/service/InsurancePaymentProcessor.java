package com.rc008code.hms.payments.service;

import com.rc008code.hms.payments.api.PaymentProcessor;
import com.rc008code.hms.payments.model.PaymentContext;
import com.rc008code.hms.payments.model.PaymentResult;

import java.util.UUID;


public class InsurancePaymentProcessor implements PaymentProcessor {
    @Override
    public PaymentResult process(PaymentContext context) {
        if (context == null || context.getAmount() <= 0) {
            return PaymentResult.failed("Invalid amount");
        }
        boolean approved = context.getAmount() <= 50000;
        if (!approved) {
            return PaymentResult.failed("Insurance claim denied");
        }
        String txId = "INS-" + UUID.randomUUID();
        return PaymentResult.success(txId, "Insurance claim approved");
    }
}
