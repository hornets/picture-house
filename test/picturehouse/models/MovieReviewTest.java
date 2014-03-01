package picturehouse.models;


import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.javalite.test.jspec.JSpec.the;

/**
 *
 * @author Akshay, sevabaskin
 */
public class MovieReviewTest {
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

	public MovieReviewTest() {
	}

	@Test
	public void shouldValidateMandatoryFields() {
		MovieReview mr = new MovieReview();

		//check errors
		the(mr).shouldNotBe("valid");
		the(mr.errors().get("customer_id")).shouldBeEqual("Error: a movie review must be written by someone");
		the(mr.errors().get("movie_id")).shouldBeEqual("Error: a movie review must belong to a movie");
		the(mr.errors().get("content")).shouldBeEqual("Please enter your review text");

		//set missing values
		mr.set("customer_id", 1, "movie_id", 1, "content", "good movie");
		// test that all is good now:
		the(mr).shouldBe("valid");

	}
}
