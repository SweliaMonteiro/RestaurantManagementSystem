package com.test.adapters;

import com.test.models.Payment;

public interface PaymentGatewayAdapter {

    Payment makePayment(long billId, double amount);

}
