package picturehouse.models;

import org.javalite.activejdbc.Model;
import picturehouse.validators.UniquenessValidator;

/**
 *
 * @author Akshay, sevabaskin
 */
public class MovieReview extends Model {
    static {
        validatePresenceOf("customer_id").message("Error: a movie review must be written by someone");
        validatePresenceOf("movie_id").message("Error: a movie review must belong to a movie");
        validatePresenceOf("content").message("Please enter your review text");
    }    
}
