package picturehouse.models;

import org.javalite.activejdbc.Model;


public class Seat extends Model{
    static {
        validatePresenceOf("seat_number").message("A valid seat number is required");
        validatePresenceOf("row_number").message("A valid row number is required");
        // TODO: verify Numericality of seat_number & row_number ?
    }    
}