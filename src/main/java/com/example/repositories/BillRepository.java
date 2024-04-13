package com.example.repositories;

import com.example.models.Bill;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public class BillRepository {

    private static long id = 1;
    Map<Long, Bill> map;

    public BillRepository() {
        map = new HashMap<Long, Bill>();
    }

    public Bill save(Bill bill) {
        if(bill.getId() == 0) {
            bill.setId(id++);
        }
        map.put(bill.getId(), bill);
        return bill;
    }

    public Optional<Bill> findById(long billId) {
        return Optional.ofNullable(map.get(billId));
    }
}
