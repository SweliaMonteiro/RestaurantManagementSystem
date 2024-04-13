package com.test.dtos;

import com.test.enums.ResponseStatus;
import com.test.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MakePaymentResponseDto {

    private ResponseStatus responseStatus;

    private String txnId;

    private PaymentStatus paymentStatus;

}
