package com.test.dtos;

import com.test.enums.ResponseStatus;
import com.test.models.MenuItem;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class GetMenuItemsResponseDto {

    private List<MenuItem> menuItems;

    private ResponseStatus responseStatus;

}

