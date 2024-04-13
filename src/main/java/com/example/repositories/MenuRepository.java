package com.example.repositories;

import com.example.enums.DietaryRequirement;
import com.example.models.*;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public class MenuRepository {

    private Map<Long, MenuItem> map;

    public MenuRepository() {
        map = new HashMap<Long, MenuItem>();
    }

    public MenuItem add(MenuItem menuItem) {
        if(menuItem.getId()==0) {
            menuItem.setId((long)(map.size()+1));
        }
        map.put(menuItem.getId(), menuItem);
        return menuItem;
    }

    public List<MenuItem> getAll() {
        List<MenuItem> list = new ArrayList<MenuItem>(map.values());
        return list;
    }

    public List<MenuItem> getByDietaryRequirement(DietaryRequirement dietaryRequirement) {
        List<MenuItem> list = new ArrayList<MenuItem>();
        for(MenuItem menuItem:map.values()) {
            if(menuItem.getDietaryRequirement().equals(dietaryRequirement)) {
                list.add(menuItem);
            }
        }
        return list;
    }

    public MenuItem save(MenuItem menuItem) {
        if(menuItem.getId()==0) {
            menuItem.setId((long)(map.size()+1));
        }
        map.put(menuItem.getId(), menuItem);
        return menuItem;
    }
}

