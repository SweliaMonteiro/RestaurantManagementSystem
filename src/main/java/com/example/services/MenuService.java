package com.example.services;

import com.example.enums.*;
import com.example.exceptions.*;
import com.example.models.*;
import com.example.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserRepository userRepository;


    // Method to get all the menu items or menu items based on dietary preference
    public List<MenuItem> getMenuItems(String itemType) {
        // If dietary preference is not given then return all the menu items
        if(itemType==null) {
            return menuRepository.getAll();
        }

        // For a valid dietary preference, get the menu items based on the dietary preference from DB
        if(itemType.equals(DietaryRequirement.VEG.name()) || itemType.equals(DietaryRequirement.NON_VEG.name()) || itemType.equals(DietaryRequirement.VEGAN.name())) {
            return menuRepository.getByDietaryRequirement(DietaryRequirement.valueOf(itemType));
        }
        // For invalid dietary preference, throw an exception
        else {
            throw new RuntimeException("Invalid dietary preference");
        }
    }


    // Method to add a menu item
    public MenuItem addMenuItem(long userId, String name, double price, String dietaryRequirement, String itemType, String description) throws UserNotFoundException, UnAuthorizedAccessException {
        // Check if the user is valid
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) {
            throw new UserNotFoundException("Invalid user id");
        }

        // Check if the user is an admin
        User user = userOptional.get();
        if(!user.getUserType().equals(UserType.ADMIN)) {
            throw new UnAuthorizedAccessException("Invalid user, access denied");
        }

        // Add the menu item with the given details in the DB
        MenuItem menuItem = new MenuItem();
        menuItem.setName(name);
        menuItem.setPrice(price);
        menuItem.setDietaryRequirement(DietaryRequirement.valueOf(dietaryRequirement));
        menuItem.setItemType(ItemType.valueOf(itemType));
        menuItem.setDescription(description);
        menuItem = menuRepository.add(menuItem);
        return menuItem;
    }
}
