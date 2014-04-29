package picturehouse.controllers;

import java.sql.Date;
import java.util.List;
import org.javalite.activejdbc.Base;
import static org.javalite.test.jspec.JSpec.the;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import picturehouse.models.*;

/**
 *
 * @author sevabaskin, Akshay
 */

public class TicketBookingControllerTest {
    //  Open database connection before each test and create a new transaction
    @Before
    public void before() {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/picturehouse_test", "testuser", "testuserpassword");
        Base.openTransaction();
    }

    //  Close database connection before after each test and rollback (delete anything added by the test)
    @After
    public void after() {
        Base.rollbackTransaction();
        Base.close();
    }
    public TicketBookingControllerTest() {}

    @Test
    public void shouldCreateNewTicketBookings() {
        // set some fake ids
        int customer_id = 1;
        int movie_id = 1;
        
        // create a screening
        Screening screening = Screening.createIt("movie_id", 1 ,"price",10.00, "start_date", Date.valueOf("2014-02-28"));
        int screening_id =  screening.getInteger("id");

        // create a seat in the room
        int seat_number = 1;
        String row_letter = "A";
        Seat seat = Seat.createIt("seat_number", seat_number, "row_letter", row_letter);
        int seat_id =  seat.getInteger("id");

        // ensure the seat is available for booking
        assertFalse(screening.isSeatBooked(seat_number, row_letter));

        // create a new TicketBooking and book the seat
        TicketBookingController controller = new TicketBookingController();
        controller.create(customer_id, screening_id, seat_id, true);
        
        // verify that a new ticket booking has been created 
        List<TicketBooking> ticketBookings = TicketBooking.where("seat_id = ?", seat_id);
        the(ticketBookings.size()).shouldBeEqual(1);
        
        // ensure the seat has indeed been booked
        assertTrue(screening.isSeatBooked(seat_number, row_letter));

    }
    @Test
    public void shouldReturnUnprintedTickets() {
        // set some fake ids
        int customer_id = 1;
        int movie_id = 1;
        int screening_id = 1;

        // create several seats in the room
        Seat seat1 = Seat.createIt("seat_number", 1, "row_letter", "A");
        Seat seat2 = Seat.createIt("seat_number", 2, "row_letter", "A");
        Seat seat3 = Seat.createIt("seat_number", 3, "row_letter", "A");
        // get seats' ids
        int seat_id1 =  Integer.parseInt(Seat.findFirst("seat_number = ?", 1).getString("id"));
        int seat_id2 =  Integer.parseInt(Seat.findFirst("seat_number = ?", 2).getString("id"));
        int seat_id3 =  Integer.parseInt(Seat.findFirst("seat_number = ?", 3).getString("id"));

        TicketBookingController controller = new TicketBookingController();
        controller.create(customer_id, screening_id, seat_id1, true);
        controller.create(customer_id, screening_id, seat_id1, false);
        controller.create(customer_id, screening_id, seat_id1, false);
        the(controller.returnUnprintedTickets(screening_id).size()).shouldBeEqual(2);
    }
}
