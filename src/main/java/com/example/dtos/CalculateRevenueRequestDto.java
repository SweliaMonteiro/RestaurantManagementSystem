package com.example.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalculateRevenueRequestDto {

    private String revenueQueryType;

    private long userId;

}

