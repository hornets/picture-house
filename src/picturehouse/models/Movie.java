package picturehouse.models;

import org.javalite.activejdbc.Model;

/**
 *
 * @author Akshay
 */
public class Movie extends Model {
    static {
        validatePresenceOf("title").message("Please enter a movie title");
        validatePresenceOf("trailer_url").message("Please enter a movie trailer");
        validatePresenceOf("synopsis").message("Please enter a movie for synopsis");
        validatePresenceOf("start_date").message("Please enter a movie start date");
    }
    
}
