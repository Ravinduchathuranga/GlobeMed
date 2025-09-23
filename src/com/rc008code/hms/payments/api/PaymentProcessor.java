package com.rc008code.hms.payments.api;

import com.rc008code.hms.payments.model.PaymentContext;
import com.rc008code.hms.payments.model.PaymentResult;

/**
 * Implementor in the Bridge pattern. Represents a low-level processor/gateway.
 */
public interface PaymentProcessor {
    PaymentResult process(PaymentContext context);
}
