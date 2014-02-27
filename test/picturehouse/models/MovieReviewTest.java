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
		// create a customer
		new CustomerController().create("johnDoe", "Passw0rdA1b", "1111222233334444");
		int customer_id =  Integer.parseInt(Customer.findFirst("username = 'johnDoe'").getString("id"));

		new MovieController().create("Inception", "http://www.youtube.com", "good one", Date.valueOf("2011-12-25"));
        int movie_id =  Integer.parseInt(Movie.findFirst("title = 'Inception'").getString("id"));
		//set missing values
		mr.set("customer_id", customer_id, "movie_id", movie_id, "content", "good movie");
		// test that all is good now:
		the(mr).shouldBe("valid");

	}
}
