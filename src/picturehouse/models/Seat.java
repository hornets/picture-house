package picturehouse.models;

import org.javalite.activejdbc.Model;

/**
 * Class Seat contains information about the position of a seat in a room (seat and room number).
 * All the records in the `seats` database table form the layout plan of the seats in the cinema room.
 */
public class Seat extends Model{
    static {
        validatePresenceOf("seat_number").message("A valid seat number is required");
        validatePresenceOf("row_letter").message("A valid row letter is required");
    }
    
    public static Seat getSeatByString(String seatString){
        String row_letter = seatString.split("[0-9]+")[0];
        int seat_number = Integer.parseInt(seatString.split("[A-Z]+")[1]);
        return Seat.findFirst("seat_number = ? AND row_letter = ?", seat_number, row_letter);
    }
}