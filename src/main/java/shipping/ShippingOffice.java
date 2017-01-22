package shipping;

import customer.Account;
import ordering.Order;

/**
 * Created by MARC LAZOLA TORRE on 22/01/2017.
 */
public class ShippingOffice {

    public static void sendTheOrder (Order order){
        Account account = order.getAccount();
        String firstNameOfTheClient = account.getFistNameCustomer();
        String secondNameOfTheClient = account.getSecondNameCustomer();
        System.out.println("The order have been sent to " + firstNameOfTheClient + " " + secondNameOfTheClient +
                                " to " + account.getOrderingAddress());
    }
}
