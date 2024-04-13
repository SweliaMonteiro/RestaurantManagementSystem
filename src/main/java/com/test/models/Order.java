package com.test.models;

import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
public class Order extends BaseModel {

    private CustomerSession customerSession;

    private Map<MenuItem, Integer> orderedItems;

}
