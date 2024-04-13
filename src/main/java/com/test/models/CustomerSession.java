package com.test.models;

import com.test.enums.CustomerSessionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerSession extends BaseModel {

    private User user;

    private CustomerSessionStatus customerSessionStatus;

}