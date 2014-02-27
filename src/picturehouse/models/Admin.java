package picturehouse.models;

import org.javalite.activejdbc.Model;
import picturehouse.validators.UniquenessValidator;

/**
 *
 * @author sevabaskin
 */
public class Admin extends Model {
    static {
        validatePresenceOf("username").message("Please, provide your username");
        validatePresenceOf("password").message("Please, provide a password");
        
        validateWith(new UniquenessValidator("username")).message("Unfortunately this username is already taken, please choose a different one");

    }
}
