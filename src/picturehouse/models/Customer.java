package picturehouse.models;

import org.javalite.activejdbc.Model;
import picturehouse.validators.UniquenessValidator;

/**
 *
 * @author sevabaskin
 */
public class Customer extends Model {
    static {
        validatePresenceOf("username").message("Please, provide your username");
        validatePresenceOf("credit_card_number").message("Please, provide your credit card number");
        validatePresenceOf("password").message("Please, provide a password");
        
        validateRegexpOf("password", "^.{8,}$").message("Password must be at least 8 characters long");
        validateRegexpOf("password", "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,})").message("Password must contain at least one uppercase, lowercase character and a digit");
        
        validateRegexpOf("credit_card_number", "(\\d{16})").message("Credit card number can only contain 16 digits");

        validateWith(new UniquenessValidator("username")).message("Unfortunately this username is already taken, please choose a different one");

    }
}
