package picturehouse.models;

import org.javalite.activejdbc.Model;

/**
 * Class Screening contains information regarding a particlar time when a particular movie is screened
 * as well as the ticket price for attending that screening.
 */
public class Screening extends Model {
    static {
        validatePresenceOf("movie_id").message("A movie id is required ");
        validatePresenceOf("price").message("Please specify the price for this screening");
        validatePresenceOf("start_date").message("Please enter the screening date for this movie");
    }

    public boolean isSeatBooked(int seat_number, String row_letter){
        Seat seat = Seat.findFirst("seat_number = ? AND row_letter = ?", seat_number, row_letter);
        int seat_id =  Integer.parseInt(seat.getString("id"));
        TicketBooking booking = TicketBooking.findFirst("seat_id = ? AND screening_id = ?", seat_id, getString("id"));
        
        return booking != null;        
    }
}
