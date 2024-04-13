package com.test.controllers;

import com.test.dtos.*;
import com.test.enums.ResponseStatus;
import com.test.models.Order;
import com.test.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;


    public GenerateBillResponseDto generateBill(GenerateBillRequestDto requestDto) {
        GenerateBillResponseDto response = new GenerateBillResponseDto();
        try {
            response.setBill(orderService.generateBill(requestDto.getUserId()));
            response.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e) {
            response.setResponseStatus(ResponseStatus.FAILURE);
        }
        return response;
    }


    public PlaceOrderResponseDto placeOrder(PlaceOrderRequestDto requestDto) {
        PlaceOrderResponseDto responseDto = new PlaceOrderResponseDto();
        try {
            Order order = orderService.placeOrder(requestDto.getUserId(), requestDto.getOrderedItems());
            responseDto.setOrder(order);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            return responseDto;
        }
        catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            return responseDto;
        }
    }
}

