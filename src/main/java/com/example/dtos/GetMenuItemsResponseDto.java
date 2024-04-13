package com.example.dtos;

import com.example.enums.ResponseStatus;
import com.example.models.MenuItem;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class GetMenuItemsResponseDto {

    private List<MenuItem> menuItems;

    private ResponseStatus responseStatus;

}

