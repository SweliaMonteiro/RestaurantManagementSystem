package com.test.dtos;

import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
public class PlaceOrderRequestDto {

    private long userId;

    private Map<Long,Integer> orderedItems;

}

