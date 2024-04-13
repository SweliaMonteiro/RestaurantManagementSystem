# Design a Restaurant Management System

## Problem Statement

You are building a Restaurant Management System. As a part of this system, you need to build below functionalities:
- Restaurant admin can add menu items to the system
- Customers can browse the menu items
- Orders placed by a customer can be tracked
- Customers can generate a bill for their order
- Integrate Paytm and Razorpay's payment gateway in the system
- Restaurant can manage the wait list of customers
- Users with billing privileges can generate aggregated revenue report for the restaurant

## Requirements
### Add Menu Items
1. The add menu item request will contain the following details:
   - User id of the admin who is adding the menu item
   - Name of the menu item
   - Description of the menu item
   - Price of the menu item
   - Dietary details of the menu item - whether it is veg or non-veg or vegan
   - Type of the menu item - whether it is a daily special or a regular menu item
2. Non admin users should not be able to add menu items.
3. Persist the menu item details in the database.
4. Once the menu item is added, the system should return the menu item in response.

### Browse Menu Items
1. The get menu items request will get dietary preference as input. The dietary preference can be either "veg" or "non-veg" or "vegan".
2. If an invalid dietary preference is given to this functionality, we should get an error saying "Invalid dietary preference".
3. When a valid dietary preference is given, you need to get the menu items from the database and filter the menu items based on the dietary preference and return the menu items in response.
4. If dietary preference is not given, you need to return all the menu items in response.


### Place Order
1. Generally a customer places multiple orders before requesting for the bill. Hence, our system must be able to track orders placed by a customer.
   - We should have an entity called as CustomerSession in our system which will help us track the orders placed by a customer.
   - Once the customer places their 1st order, we should create a CustomerSession for them with status as ACTIVE.
   - All the subsequent orders placed by the customer should be associated with the CustomerSession created for them.
2. The request for placing an order will contain the user id of the customer and a Map, where the key will be menu item id and value is the quantity of the menu item ordered.
3. This functionality should store the order details in the database.
4. If the order is placed for a customer who is not present in the database, then we should throw an error.
5. If an order contains a menu item which is not present in the database, then we should throw an error.
6. Return the order details in the response.

### Generate Bill
1. Generally a customer places multiple orders before requesting for the bill. Hence, our system must be able to track orders placed by a customer.
   - We should have an entity called as CustomerSession in our system which will help us track the orders placed by a customer.
   - Once the customer places their 1st order, we should create a CustomerSession for them with status as ACTIVE.
   - All the subsequent orders placed by the customer should be associated with the CustomerSession created for them.
   - Once the customer requests for the bill, we should mark the CustomerSession as ENDED.
2. The request for generating the bill will contain just the customer id.
3. This functionality should aggregate the items ordered by the customer and calculate the total amount to be paid by the customer.
4. This functionality should also calculate GST and service charge on the total amount.
5. GST will be 5% of the total food cost and service charge will be 10% of the total food cost.
6. Return the bill details in the response.

### Integrate Payment Gateway
1. Make request functionality will get bill id as input.
2. If an invalid bill id is given to this functionality, we should get an error saying "Invalid bill id".
3. When a valid bill id is given, you need to get the total amount from the bill details stored in the database and make a call to payment gateway to start the transaction. Once the transaction completes, we need to give the payment status and transaction id in response.
4. We need to implement the payment gateways integration in such a way that it should be very easy for us to migrate from 1 gateway to another.

### Manage Wait List
#### a) Add Customer to Wait List
1. This functionality will be used by customers.
2. A customer should be already registered with the restaurant (i.e. their details should be present in the database) before they can add themselves to the wait list.
3. If a customer is not registered with the restaurant, they should get an error message saying "User not found".
4. Once a customer is added to the wait list, we should return their position in the wait list in the response.
5. If a customer is already present in the wait list, we should return their position in the wait list in the response.

#### b) Remove Customer from Wait List
1. This functionality will be used by customers.
2. A customer should be already registered with the restaurant (i.e. their details should be present in the database) before they can remove themselves from the wait list.
3. If a customer is not registered with the restaurant, they should get an error message saying "User not found".
4. If a customer is not present in the wait list, they should get an error message saying "User not in wait list".
5. If a customer is present in the wait list, we should remove them from the wait list.

#### c) Get Wait List position of a Customer
1. This functionality will be used by customers.
2. A customer should be already registered with the restaurant (i.e. their details should be present in the database) before they can get their position in the wait list.
3. If a customer is not registered with the restaurant, they should get an error message saying "User not found".
4. If a customer is not present in the wait list, they should get -1 as their position in the wait list.
5. If a customer is present in the wait list, they should get their position in the wait list.

#### d) Remove Customers from Wait List by Restaurant Admin
1. This functionality should be accessible by restaurant admin.
2. If a customer tries to access this functionality, they should get an error message saying "Access Denied".
3. The restaurant admin should be already registered with the restaurant (i.e. their details should be present in the database) before they can remove customers from the wait list.
4. The restaurant admin will give the number of customers to be removed from the wait list.
5. If the number of customers to be removed is greater than the number of customers in the wait list, we should remove all the customers from the wait list.
6. If the number of customers to be removed is less than or equal to the number of customers in the wait list, we should remove the given number of customers from the wait list.

### Generate Revenue Report
1. The revenue made by the restaurant is stored in the database in daily_revenue table. This table has aggregated data for each day.
2. This table contains 4 columns:
   - id (primary key)
   - date (date on which the revenue was generated)
   - revenueFromFoodSales (revenue generated from food sales)
   - totalGst (total GST collected)
   - totalServiceCharge (total service charge collected)
3. Our functionality should support 4 types of queries:
   - Get revenue for current month
   - Get revenue for current financial year
   - Get revenue for previous month
   - Get revenue for previous financial year
4. The request for calculating revenue will contain user id and query type.
5. Depending upon the type of query the functionality should aggregate the relevant revenue data and return the response.
6. This functionality will only available to users with billing privileges.
