package picturehouse.models;

import org.javalite.activejdbc.Model;

/**
 * Class Seat contains information about the position of a seat in a room (seat and room number).
 * All the records in the `seats` database table form the layout plan of the seats in the cinema room.
 */
public class Seat extends Model{
    static {
        validatePresenceOf("seat_number").message("A valid seat number is required");
        validatePresenceOf("row_number").message("A valid row number is required");
    }    
}