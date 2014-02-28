package picturehouse.models;

import org.javalite.activejdbc.Model;

public class SeatBooking extends Model{
    static {
        validatePresenceOf("seat_id").message("A seat id is required");
        validatePresenceOf("screening_id").message("A screening id is required");
    }    
}