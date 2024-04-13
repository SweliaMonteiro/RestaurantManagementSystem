package com.example.models;

import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
public class Bill extends BaseModel {

    private Map<MenuItem, Integer> orderedItems;

    private double totalAmount;

    private double gst;

    private double serviceCharge;

}
