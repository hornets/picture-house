package picturehouse.models;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.javalite.test.jspec.JSpec.the;


/**
*
* @author Armand, sevabaskin
*/
public class TicketBookingTest {
//  Open database connection before each test and create a new transaction
	@Before
	public void before() {
		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://sql3.freesqldatabase.com:3306/sql331660", "sql331660", "bY9!dI1%");
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
		the(ticketBooking.errors().get("seat_id")).shouldBeEqual("A seat id is required");
		the(ticketBooking.errors().get("is_printed")).shouldBeEqual("A ticket booking must have a printed status");

		//set missing values
		ticketBooking.set("customer_id", 1, "screening_id", 1, "seat_id", 1, "is_printed", false);
		// test the ticketBooking is correct
		the(ticketBooking).shouldBe("valid");
	}

}