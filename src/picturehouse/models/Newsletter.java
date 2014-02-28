package picturehouse.models;

import org.javalite.activejdbc.Model;

public class Newsletter extends Model {
    static {
        validatePresenceOf("content").message("The content of this newsletter cannot be left empty");
        validatePresenceOf("date").message("Please enter the date you wish the newsletter to be published");
    }    
}
