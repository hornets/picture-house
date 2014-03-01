package picturehouse.models;

import org.javalite.activejdbc.Model;

public class Screening extends Model {
    static {
        validatePresenceOf("movie_id").message("A movie id is required ");
        validatePresenceOf("price").message("Please specify the price for this screening");
        validatePresenceOf("start_date").message("Please enter the screening date for this movie");
    }

    public boolean isSeatBooked(int seat_number, int row_number){
        Seat seat = Seat.findFirst("seat_number = ? AND row_number = ?", seat_number, row_number);
        int seat_id =  Integer.parseInt(seat.getString("id"));
        TicketBooking booking = TicketBooking.findFirst("seat_id = ? AND screening_id = ?", seat_id, getString("id"));
        
        return booking != null;        
    }
}
