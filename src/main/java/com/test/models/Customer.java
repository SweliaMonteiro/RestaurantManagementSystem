package com.test.models;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Customer extends BaseModel {

    private String name;

    private int visitCount;

    private List<Bill> bills;

}
