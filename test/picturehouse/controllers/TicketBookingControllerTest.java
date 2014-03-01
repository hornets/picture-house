package picturehouse.controllers;

import java.sql.Date;
import java.util.List;
import org.javalite.activejdbc.Base;
import static org.javalite.test.jspec.JSpec.the;
import org.junit.After;
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
        // create a customer
        new CustomerController().create("johnDoe", "Passw0rdA1b", "1111222233334444");
        int customer_id =  Integer.parseInt(Customer.findFirst("username = 'johnDoe'").getString("id"));
        // create a movie
        new MovieController().create("Inception", "http://www.youtube.com", "good one", Date.valueOf("2011-12-25"));
        int movie_id =  Integer.parseInt(Movie.findFirst("title = 'Inception'").getString("id"));
        // create a screening
        //! IMPORTANT: CHANGE to screening controller
        Screening screening = new Screening().create("movie_id", movie_id, "price", 12.9, "start_date", Date.valueOf("2011-12-25"));
        int screening_id =  Integer.parseInt(Customer.findFirst("username = 'johnDoe'").getString("id"));

        // Create a new customer record
        TicketBookingController controller = new TicketBookingController();
        controller.create(customer_id, screening_id, "1", true);
        // make a booking
        List<TicketBooking> ticketBookings = TicketBooking.where("seat = '1'");
        the(ticketBookings.size()).shouldBeEqual(1);
        // ensure the seat has indeed been booked
        // screening.isSeated(screening_id, )

    }
    @Test
    public void shouldReturnUnprintedTickets() {
        new CustomerController().create("johnDoe", "Passw0rdA1b", "1111222233334444");
        int customer_id =  Integer.parseInt(Customer.findFirst("username = 'johnDoe'").getString("id"));
        // create a movie
        new MovieController().create("Inception", "http://www.youtube.com", "good one", Date.valueOf("2011-12-25"));
        int movie_id =  Integer.parseInt(Movie.findFirst("title = 'Inception'").getString("id"));
        // create a screening
        new Screening().create("movie_id", movie_id, "price", 12.9, "start_date", Date.valueOf("2011-12-25"));
        int screening_id =  Integer.parseInt(Customer.findFirst("username = 'johnDoe'").getString("id"));
        TicketBookingController controller = new TicketBookingController();
        controller.create(customer_id, screening_id, "A3", true);
        controller.create(customer_id, screening_id, "A1", false);
        controller.create(customer_id, screening_id, "A2", false);
        the(controller.returnUnprintedTickets(screening_id).size()).shouldBeEqual(2);
    }

    @Test
    public void shouldEnsureTheSeatIsNotTaken() {   }
}
