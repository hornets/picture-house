package picturehouse.controllers;

import org.javalite.activejdbc.validation.ValidationException;
import picturehouse.models.Customer;

/**
 *
 * @author sevabaskin
 */
public class CustomerController {

    public CustomerController() {
    }
    public void create(String username, String password, String credit_card_number) {
        // IMPORTANT: Don't use saveIt() method, as it doesn't run custom validation of username uniqueness
        Customer c = new Customer();
        c.set("username", username, "password", password, "credit_card_number", credit_card_number);
//        c.validate();
        if (!c.save()) {
            throw new ValidationException(c);
        }
        // Compile this everytime!!!!!

//        Customer c = new Customer();
//        c.set("username", username, "password", password, "credit_card_number", credit_card_number);
//        c.validate();
//        c.validate();
//        System.out.println(c.errors());
//        if (c.save()) {
//        } else {
//            throw new ValidationException(c);
//        }
        

//        Customer c = new Customer().createIt("username", username, "password", password, "credit_card_number", credit_card_number);
//        if (!c.save()) {
//            throw new ValidationException(c);
//        }
        
        
//        Customer c = new Customer().create("username", username, "password", password, "credit_card_number", credit_card_number);
//        Customer c = new Customer();
//        c.set("username", username, "password", password, "credit_card_number", credit_card_number);
//        if (!c.save()) {
//            throw new ValidationException(c);
//        };
    }

    public boolean verifyCredentials(String username, String password) {
        Customer customer = Customer.findFirst("username = ?", username);
        return customer == null ? false : customer.getString("password").equals(password);
    }

    public void update(int id, String username, String password, String credit_card_number) {
    	Customer customer = Customer.findFirst("id = ?", id);
    	customer.set("username", username)
                .set("password", password)
                .set("credit_card_number", credit_card_number)
                .saveIt();
    }
}
