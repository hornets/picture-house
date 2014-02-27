package picturehouse.models;

import java.sql.Date;
import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.javalite.test.jspec.JSpec.the;
import picturehouse.controllers.CustomerController;
import picturehouse.controllers.MovieController;

/**
*
* @author Armand, sevabaskin
*/
public class TicketBookingTest {
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

	public TicketBookingTest() {
	}

	@Test
	public void shouldValidateMandatoryFields() {
		TicketBooking ticketBooking = new TicketBooking();

		//check errors
		the(ticketBooking).shouldNotBe("valid");
		the(ticketBooking.errors().get("customer_id")).shouldBeEqual("Error: a ticket must belong to someone");
		the(ticketBooking.errors().get("screening_id")).shouldBeEqual("You forgot to choose a movie screening for this ticket, please choose one");
		the(ticketBooking.errors().get("seat")).shouldBeEqual("Please enter the desired seat");
		the(ticketBooking.errors().get("is_printed")).shouldBeEqual("A ticket booking must have a printed status");

		// create a customer
		new CustomerController().create("johnDoe", "Passw0rdA1b", "1111222233334444");
		int customer_id =  Integer.parseInt(Customer.findFirst("username = 'johnDoe'").getString("id"));
		// create a movie
		new MovieController().create("Inception", "http://www.youtube.com", "good one", Date.valueOf("2011-12-25"));
	    int movie_id =  Integer.parseInt(Movie.findFirst("title = 'Inception'").getString("id"));
	    // create a screening
		new Screening().create("movie_id", movie_id, "price", 12.9, "start_date", Date.valueOf("2011-12-25"));
		int screening_id =  Integer.parseInt(Customer.findFirst("username = 'johnDoe'").getString("id"));

		//set missing values
		ticketBooking.set("customer_id", customer_id, "screening_id", screening_id, "seat", "3A", "is_printed", false);
		// test the ticketBooking is correct
		the(ticketBooking).shouldBe("valid");
	}

}