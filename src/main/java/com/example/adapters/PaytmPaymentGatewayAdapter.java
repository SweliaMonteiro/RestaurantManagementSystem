package com.example.adapters;

import com.example.enums.PaymentStatus;
import com.example.models.*;
import com.example.libraries.paytm.*;

public class PaytmPaymentGatewayAdapter implements PaymentGatewayAdapter {

    private PaytmApi api;

    public PaytmPaymentGatewayAdapter() {
        api = new PaytmApi();
    }

    public Payment makePayment(long billId, double amount) {
        // Make payment using Paytm API
        PaytmPaymentResponse response = api.makePayment(billId, amount);
        // Convert PaytmPaymentResponse to Payment object and return
        Payment payment = new Payment();
        payment.setTxnId(response.getTxnId());
        payment.setBillId(billId);
        if(response.getPaymentStatus().equals("SUCCESS")) {
            payment.setPaymentStatus(PaymentStatus.SUCCESS);
        }
        else {
            payment.setPaymentStatus(PaymentStatus.FAILURE);
        }
        return payment;
    }
}
