package com.example.repositories;

import com.example.models.User;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public class UserRepository {

    private Map<Long, User> map;

    public UserRepository() {
        map = new HashMap<Long, User>();
    }

    public Optional<User> findById(long id) {
        return Optional.of(map.get(id));
    }

    public User save(User user) {
        if(user.getId()==0) {
            user.setId((long)(map.size()+1));
        }
        map.put(user.getId(), user);
        return user;
    }
}
