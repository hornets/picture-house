package picturehouse.controllers;

import picturehouse.models.TicketBooking;
import java.util.List;
import picturehouse.models.Customer;

/**
*
* @author Armand
*/
public class TicketBookingController {    
    public TicketBookingController() {
    }
    // create a new record and return it
    public TicketBooking create(int customer_id, int screening_id, int seat_id, Boolean is_printed){
        TicketBooking ticketBooking = new TicketBooking();
        ticketBooking.set("customer_id", customer_id)
                           .set("screening_id", screening_id)
                           .set("seat_id", seat_id)
                           .set("is_printed", is_printed)
                           .save();
        return ticketBooking;
    }
    public List<TicketBooking> returnUnprintedTickets(int customer_id) {
        return TicketBooking.where("customer_id = ? AND is_printed = ?", customer_id, false);
    }

}
