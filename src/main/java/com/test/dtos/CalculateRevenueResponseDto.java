package com.test.dtos;

import com.test.enums.ResponseStatus;
import com.test.models.AggregatedRevenue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalculateRevenueResponseDto {

    private AggregatedRevenue aggregatedRevenue;

    private ResponseStatus responseStatus;

}
