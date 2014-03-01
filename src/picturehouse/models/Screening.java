package picturehouse.models;

import org.javalite.activejdbc.Model;
import picturehouse.validators.CustomerExistsValidator;

public class Screening extends Model {
    static {
        validatePresenceOf("movie_id").message("A movie id is required ");
        validatePresenceOf("price").message("Please specify the price for this screening");
        validatePresenceOf("start_date").message("Please enter the screening date for this movie");
    }    
}
