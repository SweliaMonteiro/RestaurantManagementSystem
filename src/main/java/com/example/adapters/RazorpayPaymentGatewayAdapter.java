package com.example.adapters;

import com.example.enums.PaymentStatus;
import com.example.models.*;
import com.example.libraries.razorpay.*;

public class RazorpayPaymentGatewayAdapter implements PaymentGatewayAdapter {

    private RazorpayApi api;

    public RazorpayPaymentGatewayAdapter() {
        api = new RazorpayApi();
    }

    public Payment makePayment(long billId, double amount) {
        // Make payment using Razorpay API
        RazorpayPaymentResponse response = api.processPayment(billId, amount);
        //  Convert RazorpayPaymentResponse to Payment object and return
        Payment payment = new Payment();
        payment.setTxnId(response.getTransactionId());
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
