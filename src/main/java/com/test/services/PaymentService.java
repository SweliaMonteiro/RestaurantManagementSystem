package com.test.services;

import com.test.exceptions.*;
import com.test.models.*;
import com.test.repositories.*;
import java.util.*;
import com.test.adapters.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private PaymentGatewayAdapter paymentGatewayAdapter;


    // Method to make payment for the given billId
    public Payment makePayment(long billId) throws InvalidBillException {
        // Check if the billId is valid
        Optional<Bill> billOptional = billRepository.findById(billId);
        if(billOptional.isEmpty()) {
            throw new InvalidBillException("Invalid bill id");
        }

        // Get the total amount for the given billId and make payment using the payment gateway
        double totalAmount = billOptional.get().getTotalAmount();
        return paymentGatewayAdapter.makePayment(billId, totalAmount);
    }
}
