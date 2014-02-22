package picturehouse.models;

import org.javalite.activejdbc.Model;

/**
 *
 * @author sevabaskin
 */
public class Customer extends Model {
    static {
        validatePresenceOf("username").message("Please, provide your username");
        validatePresenceOf("credit_card_number").message("Please, provide your credit card number");
        validatePresenceOf("password").message("Please, provide a password");
    }
}
