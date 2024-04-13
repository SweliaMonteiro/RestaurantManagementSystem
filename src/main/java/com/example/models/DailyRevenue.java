package com.example.models;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class DailyRevenue extends BaseModel{

    private double revenueFromFoodSales;

    private Date date;

    private double totalGst;

    private double totalServiceCharge;

}
