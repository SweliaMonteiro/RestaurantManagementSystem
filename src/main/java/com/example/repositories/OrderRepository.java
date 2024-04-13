package com.example.repositories;

import com.example.models.*;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public class OrderRepository {

    private Map<Long, Order> map;

    public OrderRepository() {
        map = new HashMap<Long, Order>();
    }

    public Order save(Order order) {
        if(order.getId()==0) {
            order.setId((long)(map.size()+1));
        }
        map.put(order.getId(), order);
        return order;
    }

    public List<Order> findOrdersByCustomerSession(long customerSessionId) {
        List<Order> orders = new ArrayList<Order>();
        for(Order order:map.values()) {
            if(order.getCustomerSession().getId() == customerSessionId) {
                orders.add(order);
            }
        }
        return orders;
    }
}

