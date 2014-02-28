package picturehouse.models;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.javalite.test.jspec.JSpec.the;

public class SeatBookingTest {
    //  Open database connection before each test and create a new transaction
	@Before
	public void before() {
		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:8889/picturehouse_test", "root", "root");
		Base.openTransaction();
	}

    //  Close database connection before after each test and rollback (delete anything added by the test)
	@After
	public void after() {
		Base.rollbackTransaction();
		Base.close();
	}

	public SeatBookingTest() {
	}

	@Test
	public void shouldValidateMandatoryFields() {
		SeatBooking booking = new SeatBooking();

		//validation rules
                the(booking).shouldNotBe("valid");
		the(booking.errors().get("seat_id")).shouldBeEqual("A seat id is required");
		the(booking.errors().get("screening_id")).shouldBeEqual("A screening id is required");

		//set attributes
		booking.set("seat_id", 1, "screening_id", 1);
		//validation should pass now
		booking = new SeatBooking();
		booking.set("seat_id", 1, "screening_id", 1);
		the(booking).shouldBe("valid");
	}
}
