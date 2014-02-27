package picturehouse.models;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.javalite.test.jspec.JSpec.the;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author sevabaskin
 */
public class AdminTest {
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

	public AdminTest() {
	}

	@Test
	public void shouldValidateMandatoryFields() {
		Admin admin = new Admin();

		//check errors
		the(admin).shouldNotBe("valid");
		the(admin.errors().get("username")).shouldBeEqual("Please, provide your username");
		the(admin.errors().get("password")).shouldBeEqual("Please, provide a password");

		//set missing values
		admin.set("username", "admin1", "password", "Passw0rdA1b");
		
		// verify the instance is valid
		the(admin).shouldBe("valid");
	}

	@Test
	public void shouldValidateUniqueUsername() {
		// create admin named admin1, and using assertTrue ensure the record was created
		assertTrue(new Admin().set("username", "admin1", "password", "Passw0rdA1b").saveIt());
       
		// attempt creating another admin named admin1
		Admin admin = new Admin();
		admin.set("username", "admin1", "password", "Passw0rdA1b").saveIt();
		the(admin).shouldNotBe("valid");
		the(admin.errors().get("username")).shouldBeEqual("Unfortunately this username is already taken, please choose a different one");
	}

}
