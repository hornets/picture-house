package picturehouse.controllers;

import picturehouse.models.Customer;

/**
 *
 * @author sevabaskin
 */
public class CustomerController {

    public CustomerController() {
    }
    public void create(String username, String password, String credit_card_number) {
       new Customer().set("username", username).set("password", password).set("credit_card_number", credit_card_number).saveIt();
    }

    boolean verifyCredentials(String username, String password) {
        Customer customer = Customer.findFirst("username = ?", username);
        return customer.getString("password").equals(password);
    }
}
