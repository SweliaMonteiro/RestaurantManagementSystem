package com.test.repositories;

import com.test.models.MenuItem;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public class MenuItemRepository {

    private Map<Long, MenuItem> map;

    public MenuItemRepository() {
        map = new HashMap<Long, MenuItem>();
    }

    public MenuItem add(MenuItem menuItem) {
        if(menuItem.getId()==0) {
            menuItem.setId((long)(map.size()+1));
        }
        map.put(menuItem.getId(), menuItem);
        return menuItem;
    }

    public Optional<MenuItem> findById(long id) {
        return Optional.of(map.get(id));
    }
}
