package com.example.models;

import com.example.enums.DietaryRequirement;
import com.example.enums.ItemType;
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
