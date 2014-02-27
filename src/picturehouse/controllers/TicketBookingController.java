package picturehouse.controllers;

import picturehouse.models.TicketBooking;
import java.util.List;

/**
*
* @author Armand
*/
public class TicketBookingController {    
    public TicketBookingController() {
    }
    public void create(int customer_id, int screening_id, String seat, Boolean is_printed){
        new TicketBooking().set("customer_id", customer_id)
                           .set("screening_id", screening_id)
                           .set("seat", seat)
                           .set("is_printed", is_printed)
                           .saveIt();
    }
    public List<TicketBooking> returnUnprintedTickets(int customer_id) {
        return TicketBooking.where("customer_id = ? AND is_printed = ?", customer_id, false);
    }

}
