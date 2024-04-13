package com.test.dtos;

import com.test.enums.ResponseStatus;
import com.test.models.Order;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceOrderResponseDto {

    private ResponseStatus responseStatus;

    private Order order;

}

