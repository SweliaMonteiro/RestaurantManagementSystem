package com.test.controllers;

import com.test.dtos.*;
import com.test.enums.ResponseStatus;
import com.test.models.*;
import com.test.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    public MakePaymentResponseDto makePayment(MakePaymentRequestDto makePaymentRequestDto) {
        MakePaymentResponseDto response = new MakePaymentResponseDto();
        try{
            Payment payment = paymentService.makePayment(makePaymentRequestDto.getBillId());
            response.setTxnId(payment.getTxnId());
            response.setPaymentStatus(payment.getPaymentStatus());
            response.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e) {
            response.setResponseStatus(ResponseStatus.FAILURE);
        }
        return response;
    }
}
