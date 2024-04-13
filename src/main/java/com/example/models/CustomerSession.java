package com.example.models;

import com.example.enums.CustomerSessionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerSession extends BaseModel {

    private User user;

    private CustomerSessionStatus customerSessionStatus;

}