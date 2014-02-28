package picturehouse.models;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.javalite.test.jspec.JSpec.the;

public class RoomPlanTest {
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

	public RoomPlanTest() {
	}

	@Test
	public void shouldValidateMandatoryFields() {
		RoomPlan plan = new RoomPlan();

		//validation rules
                the(plan).shouldNotBe("valid");
		the(plan.errors().get("seat_number")).shouldBeEqual("A seat number existing in the room is required");

		//set attributes
		plan.set("seat_number", 1);
		//validation should pass now
		plan = new RoomPlan();
		plan.set("seat_number", 1);
		the(plan).shouldBe("valid");
	}
}
