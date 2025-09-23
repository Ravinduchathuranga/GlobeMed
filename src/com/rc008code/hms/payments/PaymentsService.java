package com.rc008code.hms.payments;

import com.rc008code.hms.enums.PaymentTypes;
import com.rc008code.hms.payments.service.Payment;
import com.rc008code.hms.payments.api.PaymentProcessor;
import com.rc008code.hms.payments.model.PaymentContext;
import com.rc008code.hms.payments.model.PaymentResult;
import com.rc008code.hms.payments.service.*;

/**
 * Facade for simple payment operations using the Bridge pattern implementation.
 * This does not persist payments; it just simulates processing and returns a result.
 */
public class PaymentsService {

    public PaymentResult process(PaymentContext context) {
        if (context == null || context.getType() == null) {
            return PaymentResult.failed("Missing payment context/type");
        }
        PaymentProcessor processor = selectProcessor(context.getType());
        Payment payment = createAbstraction(context.getType(), processor);
        return payment.pay(context);
    }

    private PaymentProcessor selectProcessor(PaymentTypes type) {
        switch (type) {
            case CASH:
                return new CashPaymentProcessor();
            case CREDIT_CARD:
                return new CardPaymentProcessor();
            case INSURANCE:
            default:
                return new InsurancePaymentProcessor();
        }
    }

    private Payment createAbstraction(PaymentTypes type, PaymentProcessor processor) {
        switch (type) {
            case CASH:
                return new CashPayment(processor);
            case CREDIT_CARD:
            case INSURANCE:
            default:
                return new OnlinePayment(processor);
        }
    }
}
