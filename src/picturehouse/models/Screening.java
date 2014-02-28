package picturehouse.models;

import org.javalite.activejdbc.Model;
import picturehouse.validators.CustomerExistsValidator;

public class Screening extends Model {
    static {
        validatePresenceOf("movie_id").message("This screening requires a movie id");
        validatePresenceOf("price").message("This screening requires a price");
        validatePresenceOf("start_date").message("Please enter the date this movie is going to start screening");
    }    
}
