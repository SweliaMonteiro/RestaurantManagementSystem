package com.example.controllers;

import com.example.dtos.*;
import com.example.enums.ResponseStatus;
import com.example.services.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class RevenueController {

    @Autowired
    private RevenueService revenueService;


    public CalculateRevenueResponseDto calculateRevenue(CalculateRevenueRequestDto requestDto) {
        CalculateRevenueResponseDto response = new CalculateRevenueResponseDto();
        try {
            response.setAggregatedRevenue(revenueService.calculateRevenue(requestDto.getUserId(), requestDto.getRevenueQueryType()));
            response.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e) {
            response.setResponseStatus(ResponseStatus.FAILURE);
        }
        return response;
    }
}
