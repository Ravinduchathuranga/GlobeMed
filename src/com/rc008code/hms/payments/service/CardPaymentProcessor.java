package com.rc008code.hms.payments.service;

import com.rc008code.hms.payments.api.PaymentProcessor;
import com.rc008code.hms.payments.model.PaymentContext;
import com.rc008code.hms.payments.model.PaymentResult;

import java.util.UUID;

/**
 * ConcreteImplementor: simulates a card gateway.
 */
public class CardPaymentProcessor implements PaymentProcessor {
    @Override
    public PaymentResult process(PaymentContext context) {
        if (context == null || context.getAmount() <= 0) {
            return PaymentResult.failed("Invalid amount");
        }
        // Simulate a simple approval rule
        boolean approved = context.getAmount() < 10000; // arbitrary rule
        if (!approved) {
            return PaymentResult.failed("Transaction declined by gateway");
        }
        String txId = "CARD-" + UUID.randomUUID();
        return PaymentResult.success(txId, "Card payment approved");
    }
}
