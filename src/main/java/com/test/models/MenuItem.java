package com.test.models;

import com.test.enums.DietaryRequirement;
import com.test.enums.ItemType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItem extends BaseModel {

    private String name;

    private double price;

    private DietaryRequirement dietaryRequirement;

    private ItemType itemType;

    private String description;

}
