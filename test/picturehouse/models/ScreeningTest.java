package picturehouse.models;


import java.sql.Date;
import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import picturehouse.controllers.TicketBookingController;

public class ScreeningTest {
    
    public ScreeningTest() {
    }
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

    @Test
    public void shouldReutrnTrueIfSeatIsBookedForThisBooking() {
        // create a new screening
        Screening screening = Screening.createIt("movie_id", 1 ,"price",10.00, "start_date", Date.valueOf("2014-02-28"));
        // create a new TicketBooking and book the seat
        TicketBookingController controller = new TicketBookingController();

        // create a new seat
        int seat_number = 1;
        int row_number = 1;
        Seat seat = Seat.createIt("seat_number", seat_number, "row_number", row_number);

        // ensure the seat is available for booking
        assertFalse(screening.isSeatBooked(seat_number, row_number));
        // create a new TicketBooking and book the seat
        controller.create(1, screening.getInteger("id"), seat.getInteger("id"), true);
        // ensure the seat has indeed been booked
        assertTrue(screening.isSeatBooked(seat_number, row_number));
    }
    
}
