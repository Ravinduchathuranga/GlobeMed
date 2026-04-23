package com.rc008code.hms.payments.api;

import com.rc008code.hms.payments.model.PaymentContext;
import com.rc008code.hms.payments.model.PaymentResult;


public interface PaymentProcessor {
    PaymentResult process(PaymentContext context);
}
