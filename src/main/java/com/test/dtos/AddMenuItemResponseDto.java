package com.test.dtos;

import com.test.enums.ResponseStatus;
import com.test.models.MenuItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMenuItemResponseDto {

    private ResponseStatus status;

    private MenuItem menuItem;

}
