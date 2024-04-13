package com.test.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMenuItemRequestDto {

    private long userId;

    private String name;

    private double price;

    private String dietaryRequirement;

    private String itemType;

    private String description;

}
