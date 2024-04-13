package com.example.controllers;

import com.example.dtos.*;
import com.example.enums.ResponseStatus;
import com.example.models.MenuItem;
import com.example.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;


@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;


    public GetMenuItemsResponseDto getMenuItems(GetMenuItemsRequestDto requestDto) {
        GetMenuItemsResponseDto response = new GetMenuItemsResponseDto();
        try {
            List<MenuItem> menuItems = menuService.getMenuItems(requestDto.getDietaryRequirement());
            response.setMenuItems(menuItems);
            response.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e) {
            response.setResponseStatus(ResponseStatus.FAILURE);
        }
        return response;
    }


    public AddMenuItemResponseDto addMenuItem(AddMenuItemRequestDto requestDto) {
        AddMenuItemResponseDto response = new AddMenuItemResponseDto();
        try {
            MenuItem menuItem = menuService.addMenuItem(requestDto.getUserId(), requestDto.getName(), requestDto.getPrice(), requestDto.getDietaryRequirement(), requestDto.getItemType(), requestDto.getDescription());
            response.setMenuItem(menuItem);
            response.setStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e) {
            response.setStatus(ResponseStatus.FAILURE);
        }
        return response;
    }
}


