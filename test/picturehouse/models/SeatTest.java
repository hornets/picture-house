package picturehouse.models;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.javalite.test.jspec.JSpec.the;

public class SeatTest {
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


	public SeatTest() {
	}

	@Test
	public void shouldValidateMandatoryFields() {
		Seat seat = new Seat();

		//validation rules
        the(seat).shouldNotBe("valid");
		the(seat.errors().get("seat_number")).shouldBeEqual("A valid seat number is required");
		the(seat.errors().get("row_number")).shouldBeEqual("A valid row number is required");

		//set attributes
		seat.set("seat_number", 1);
		seat.set("row_number", 1);
		the(seat).shouldBe("valid");
	}
}
