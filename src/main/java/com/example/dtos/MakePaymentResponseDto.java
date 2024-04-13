package com.example.dtos;

import com.example.enums.ResponseStatus;
import com.example.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MakePaymentResponseDto {

    private ResponseStatus responseStatus;

    private String txnId;

    private PaymentStatus paymentStatus;

}
