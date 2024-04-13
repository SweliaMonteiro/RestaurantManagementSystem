package com.example.repositories;

import com.example.models.WaitListPosition;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public class WaitListPositionRepository {

    private Map<Long, WaitListPosition> map;

    public WaitListPositionRepository() {
        this.map = new HashMap<>();
    }

    private static int id = 0;

    public WaitListPosition save(WaitListPosition waitListPosition) {
        if(waitListPosition.getId() == 0){
            waitListPosition.setId(++id);
        }
        map.put(waitListPosition.getId(), waitListPosition);
        return waitListPosition;
    }

    public List<WaitListPosition> findAll() {
        return map.values().stream().toList();
    }

    public WaitListPosition delete(WaitListPosition waitListPosition) {
        return map.remove(waitListPosition.getId());
    }
}
