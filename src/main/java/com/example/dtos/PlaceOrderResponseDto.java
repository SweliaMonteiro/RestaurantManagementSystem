package com.example.dtos;

import com.example.enums.ResponseStatus;
import com.example.models.Order;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceOrderResponseDto {

    private ResponseStatus responseStatus;

    private Order order;

}

