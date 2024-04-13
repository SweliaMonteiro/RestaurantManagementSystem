package com.example.dtos;

import com.example.enums.ResponseStatus;
import com.example.models.AggregatedRevenue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalculateRevenueResponseDto {

    private AggregatedRevenue aggregatedRevenue;

    private ResponseStatus responseStatus;

}
