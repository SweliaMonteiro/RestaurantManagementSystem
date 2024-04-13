package com.example.repositories;

import com.example.models.DailyRevenue;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public class DailyRevenueRepository {

    private Map<Long, DailyRevenue> map;

    public DailyRevenueRepository() {
        map = new HashMap<Long, DailyRevenue>();
    }

    public DailyRevenue save(DailyRevenue dailyRevenue) {
        if(dailyRevenue.getId()==0) {
            dailyRevenue.setId((long)(map.size()+1));
        }
        map.put(dailyRevenue.getId(), dailyRevenue);
        return dailyRevenue;
    }

    public List<DailyRevenue> getDailyRevenueBetweenDates(Date startDate, Date endDate) {
        List<DailyRevenue> list = new ArrayList<>();
        // Iterate over the map and get the daily revenue between the start and end date (inclusive - including the start and end date)
        for(DailyRevenue dailyRevenue:map.values()) {
            if((dailyRevenue.getDate().compareTo(startDate)>=0 || equalDates(dailyRevenue.getDate(), startDate)) && (dailyRevenue.getDate().compareTo(endDate)<=0 || equalDates(dailyRevenue.getDate(), endDate))) {
                list.add(dailyRevenue);
            }
        }
        return list;
    }

    // Method to check if two dates are equal
    public boolean equalDates(Date date1, Date date2) {
        if(date1.getMonth()==date2.getMonth() && date1.getYear()==date2.getYear() && date1.getDate()==date2.getDate()) {
            return true;
        }
        return false;
    }
}
