package com.test.services;

import com.test.enums.*;
import com.test.exceptions.*;
import com.test.models.*;
import com.test.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class OrderService {

    @Autowired
    private CustomerSessionRepository customerSessionRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private BillRepository billRepository;


    // Method to generate bill for the given userId
    public Bill generateBill(long userId) throws CustomerSessionNotFound {
        // Find all the orders for the given userId
        List<Order> orders = orderRepository.findOrdersByCustomerSession(userId);
        if(orders.isEmpty()) {
            throw new CustomerSessionNotFound("Active Customer Session Not Found");
        }

        // Calculate the total amount, gst and service charge for the orders of the given userId
        Map<MenuItem, Integer> orderedItems = new HashMap<>();
        double totalAmount = 0;
        for(Order order:orders) {
            for(MenuItem menuItem:order.getOrderedItems().keySet()) {
                if(orderedItems.containsKey(menuItem)) {
                    orderedItems.put(menuItem, orderedItems.get(menuItem)+order.getOrderedItems().get(menuItem));
                }
                else {
                    orderedItems.put(menuItem, order.getOrderedItems().get(menuItem));
                }
                totalAmount += (menuItem.getPrice() * order.getOrderedItems().get(menuItem));
            }
        }

        // Check if the user has an active customer session. Update the customer session status to ENDED and save in the DB
        Optional<CustomerSession> customerSessionOptional = customerSessionRepository.findActiveCustomerSessionByUserId(userId);
        if(customerSessionOptional.isEmpty()) {
            throw new CustomerSessionNotFound("Customer Session Not Found");
        }
        CustomerSession customerSession = customerSessionOptional.get();
        customerSession.setCustomerSessionStatus(CustomerSessionStatus.ENDED);
        customerSessionRepository.save(customerSession);

        // Create a new bill and save in the DB
        Bill bill = new Bill();
        bill.setOrderedItems(orderedItems);
        bill.setGst(totalAmount * 0.05);
        bill.setServiceCharge(totalAmount * 0.1);
        bill.setTotalAmount(totalAmount + (totalAmount * 0.05) + (totalAmount * 0.1));
        bill = billRepository.save(bill);
        return bill;
    }


    // Method to place an order for the given userId
    public Order placeOrder(long userId, Map<Long,Integer> orderedItems) throws UserNotFoundException, InvalidMenuItemException {
        // Check if a customer session is active for the given userId. If not, create a new customer session.
        Optional<CustomerSession> customerSessionOptional = customerSessionRepository.findActiveCustomerSessionByUserId(userId);
        CustomerSession customerSession = null;

        // If no active customer session is found, create a new customer session for the given userId if the user exists
        if(customerSessionOptional.isEmpty() || customerSessionOptional.get().getCustomerSessionStatus().equals(CustomerSessionStatus.ENDED)) {
            Optional<User> userOptional = userRepository.findById(userId);
            if(userOptional.isEmpty()) {
                throw new UserNotFoundException("User not found with the given userId");
            }
            User user = userOptional.get();
            customerSession = new CustomerSession();
            customerSession.setUser(user);
            customerSession.setCustomerSessionStatus(CustomerSessionStatus.ACTIVE);
            customerSession = customerSessionRepository.save(customerSession);
        }
        // If an active customer session is found, use the existing customer session
        else {
            customerSession = customerSessionOptional.get();
        }

        // Create a new order with the given ordered items and save in the DB
        Map<MenuItem, Integer> orderedItemsList = new HashMap<>();
        for(Long menuItemId:orderedItems.keySet()) {
            // Check if the menu item exists
            Optional<MenuItem> menuItemOptional = menuItemRepository.findById(menuItemId);
            if(menuItemOptional.isEmpty()) {
                throw new InvalidMenuItemException("No MenuItem found with the given menuItemId");
            }
            // If the menu item valid, add it to the order
            orderedItemsList.put(menuItemOptional.get(), orderedItems.get(menuItemId));
        }

        // Create a new order and save the order in the DB
        Order order = new Order();
        order.setCustomerSession(customerSession);
        order.setOrderedItems(orderedItemsList);
        order = orderRepository.save(order);
        return order;
    }
}
