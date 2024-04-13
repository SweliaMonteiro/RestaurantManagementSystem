package com.example.dtos;

import com.example.enums.ResponseStatus;
import com.example.models.MenuItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMenuItemResponseDto {

    private ResponseStatus status;

    private MenuItem menuItem;

}
