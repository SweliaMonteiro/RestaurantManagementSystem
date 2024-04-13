package com.test.services;

import com.test.enums.*;
import com.test.exceptions.*;
import com.test.models.*;
import com.test.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class RevenueService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DailyRevenueRepository dailyRevenueRepository;


    // Method to calculate aggregated revenue based on the query type
    public AggregatedRevenue calculateRevenue(long userId, String queryType) throws UnAuthorizedAccessException, UserNotFoundException {
        // Check if the user is valid
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found with the given userId");
        }
        User user = userOptional.get();

        // Check if the user has billing privilege
        if(!user.getUserType().equals(UserType.BILLING)) {
            throw new UnAuthorizedAccessException("Access denied");
        }

        // Calculate revenue based on the query type
        RevenueQueryType revenueQueryType = RevenueQueryType.valueOf(queryType);
        Date startDate = new Date();
        Date endDate = new Date();
        Calendar calStartDate = Calendar.getInstance();
        Calendar calEndDate  = Calendar.getInstance();

        // Set the start and end date based on the query type

        // CURRENT_FY - Current Financial Year
        if(revenueQueryType.equals(RevenueQueryType.CURRENT_FY)) {
            // Set the start date to 1st January of the current year
            calStartDate.set(calStartDate.get(Calendar.YEAR), Calendar.JANUARY, 1);
            startDate = calStartDate.getTime();
            // Set the end date to 31st December of the current year
            calEndDate.set(calEndDate.get(Calendar.YEAR), Calendar.DECEMBER, 31);
            endDate = calEndDate.getTime();
        }
        // PREVIOUS_FY - Previous Financial Year
        else if(revenueQueryType.equals(RevenueQueryType.PREVIOUS_FY)) {
            // Set the start date to 1st January of the previous year
            calStartDate.set(calStartDate.get(Calendar.YEAR)-1, Calendar.JANUARY, 1);
            startDate = calStartDate.getTime();
            // Set the end date to 31st December of the previous year
            calEndDate.set(calEndDate.get(Calendar.YEAR)-1, Calendar.DECEMBER, 31);
            endDate = calEndDate.getTime();
        }
        // CURRENT_MONTH - Current Month
        else if(revenueQueryType.equals(RevenueQueryType.CURRENT_MONTH)) {
            // Set the start date to 1st of the current month
            calStartDate.set(Calendar.DAY_OF_MONTH, 1);
            startDate = calStartDate.getTime();
            // Set the end date to the last day of the current month
            calEndDate.set(Calendar.DAY_OF_MONTH, calEndDate.getActualMaximum(Calendar.DAY_OF_MONTH));
            endDate = calEndDate.getTime();
        }
        // PREVIOUS_MONTH - Previous Month
        else if(revenueQueryType.equals(RevenueQueryType.PREVIOUS_MONTH)) {
            // Set the start date to 1st of the previous month
            calStartDate.set(Calendar.DAY_OF_MONTH, 1);
            calStartDate.add(Calendar.MONTH, -1);
            startDate = calStartDate.getTime();
            // Set the end date to the last day of the previous month
            calEndDate.add(Calendar.MONTH, -1);
            calEndDate.set(Calendar.DAY_OF_MONTH, calEndDate.getActualMaximum(Calendar.DAY_OF_MONTH));
            endDate = calEndDate.getTime();
        }

        // Get the daily revenues between the start and end date (inclusive)
        List<DailyRevenue> dailyRevenues = dailyRevenueRepository.getDailyRevenueBetweenDates(startDate, endDate);

        // Calculate the revenue from food sales, total GST and total service charge for the daily revenues fetched from the DB
        double revenueFromFoodSales = 0;
        double totalGst = 0;
        double totalServiceCharge = 0;
        for(DailyRevenue dailyRevenue: dailyRevenues) {
            revenueFromFoodSales += dailyRevenue.getRevenueFromFoodSales();
            totalGst += dailyRevenue.getTotalGst();
            totalServiceCharge += dailyRevenue.getTotalServiceCharge();
        }

        // Create a new AggregatedRevenue, set the calculated values and return it
        AggregatedRevenue aggregatedRevenue = new AggregatedRevenue();
        aggregatedRevenue.setFromDate(startDate);
        aggregatedRevenue.setToDate(endDate);
        aggregatedRevenue.setRevenueFromFoodSales(revenueFromFoodSales);
        aggregatedRevenue.setTotalGst(totalGst);
        aggregatedRevenue.setTotalServiceCharge(totalServiceCharge);
        aggregatedRevenue.setTotalRevenue(revenueFromFoodSales + totalGst + totalServiceCharge);
        return aggregatedRevenue;
    }
}
