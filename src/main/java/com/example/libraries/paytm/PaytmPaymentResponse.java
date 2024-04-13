package com.example.libraries.paytm;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class PaytmPaymentResponse {

    private String txnId;

    private String paymentStatus;

    private String orderId;

    private double txnAmount;

    private Date txnDate;

}
