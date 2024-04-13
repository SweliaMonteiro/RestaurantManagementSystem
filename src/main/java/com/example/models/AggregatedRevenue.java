package com.example.models;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class AggregatedRevenue {

    private double revenueFromFoodSales;

    private Date fromDate;

    private Date toDate;

    private double totalGst;

    private double totalServiceCharge;

    private double totalRevenue;

}
