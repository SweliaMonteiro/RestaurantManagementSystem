package com.example.models;

import com.example.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment extends BaseModel {

    private String txnId;

    private PaymentStatus paymentStatus;

    private long billId;

}
