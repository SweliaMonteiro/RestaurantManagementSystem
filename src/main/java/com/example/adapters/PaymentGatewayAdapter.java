package com.example.adapters;

import com.example.models.Payment;

public interface PaymentGatewayAdapter {

    Payment makePayment(long billId, double amount);

}
