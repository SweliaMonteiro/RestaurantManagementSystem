package com.test.repositories;

import com.test.models.*;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public class CustomerSessionRepository {

    private Map<Long, CustomerSession> map;

    public CustomerSessionRepository() {
        map = new HashMap<Long, CustomerSession>();
    }

    public CustomerSession save(CustomerSession customerSession) {
        if(customerSession.getId()==0) {
            customerSession.setId((long)(map.size()+1));
        }
        map.put(customerSession.getId(), customerSession);
        return customerSession;
    }

    public Optional<CustomerSession> findActiveCustomerSessionByUserId(long userId) {
        return Optional.of(map.get(userId));
    }
}

