package com.test.services;

import com.test.enums.*;
import com.test.exceptions.*;
import com.test.models.*;
import com.test.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class WaitListService {

    @Autowired
    private WaitListPositionRepository waitListPositionRepository;

    @Autowired
    private UserRepository userRepository;


    // Method to add customer to waitlist
    public int addUserToWaitList(long userId) throws UserNotFoundException {
        // Check if user is valid
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

        // Get all waitlist positions
        List<WaitListPosition> all = waitListPositionRepository.findAll();

        // Check if user is already in waitlist
        for (int i = 0; i < all.size(); i++) {
            // If user is already in waitlist, return the position of the user
            if (all.get(i).getUser().getId() == user.getId()) {
                return i + 1;
            }
        }

        // If user is not in waitlist, add user to waitlist
        WaitListPosition waitListPosition = new WaitListPosition();
        waitListPosition.setUser(user);
        waitListPosition.setInsertedAt(new Date());
        waitListPositionRepository.save(waitListPosition);

        // Return the new position of the user
        all = waitListPositionRepository.findAll();
        return all.size();
    }


    // Method to remove customer from waitlist
    public void removeUserFromWaitList(long userId) throws UserNotFoundException, UserNotInWaitListException {
        // Check if user is valid
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

        // Get all waitlist positions
        List<WaitListPosition> all = waitListPositionRepository.findAll();

        // Check if user is in waitlist
        for (int i = 0; i < all.size(); i++) {
            // If user is in waitlist, remove user from waitlist
            if (all.get(i).getUser().getId() == user.getId()) {
                waitListPositionRepository.delete(all.get(i));
                return;
            }
        }
        // If user is not in waitlist, throw exception
        throw new UserNotInWaitListException("User not in waitlist");
    }


    // Method to get waitlist position of customer
    public int getWaitListPosition(long userId) throws UserNotFoundException {
        // Check if user is valid
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

        // Get all waitlist positions
        List<WaitListPosition> all = waitListPositionRepository.findAll();

        // Check if user is in waitlist
        for (int i = 0; i < all.size(); i++) {
            // If user is in waitlist, return the position of the user
            if (all.get(i).getUser().getId() == user.getId()) {
                return i + 1;
            }
        }
        // If user is not in waitlist, return -1
        return -1;
    }


    // Method to remove the given number of customers from waitlist
    public void updateWaitList(long adminUserId, int numberOfSpots) throws UserNotFoundException, UnAuthorizedAccessException {
        // Check if user is valid
        User adminUser = userRepository.findById(adminUserId).orElseThrow(() -> new UserNotFoundException("User not found"));
        // Check if user is admin
        if(adminUser.getUserType() != UserType.ADMIN) {
            throw new UnAuthorizedAccessException("User not found");
        }

        // Get all waitlist positions
        List<WaitListPosition> all = waitListPositionRepository.findAll();

        // numberOfCustomersToBeRemoved > numberOfCustomersInWaitList ---> remove all the customers from the wait list
        // numberOfCustomersToBeRemoved <= numberOfCustomersInWaitList ---> remove the given number of customers from the wait list
        // Therefore get the minimum number of customers to be removed
        numberOfSpots = Math.min(numberOfSpots, all.size());

        // Remove the given number of customers from the wait list
        for (int i = 0; i < numberOfSpots; i++) {
            waitListPositionRepository.delete(all.get(i));
        }
    }
}
