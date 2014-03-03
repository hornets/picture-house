package picturehouse.models;

import java.sql.Date;
import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.javalite.test.jspec.JSpec.the;

/**
 *
 * @author Akshay
 */
public class MovieTest {
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

	public MovieTest() {
	}

	@Test
	public void shouldValidateMandatoryFields() {
		Movie movie = new Movie();

		//check errors
        the(movie).shouldNotBe("valid");
		the(movie.errors().get("title")).shouldBeEqual("Please enter a movie title");
		the(movie.errors().get("trailer_url")).shouldBeEqual("Please enter a movie trailer");
		the(movie.errors().get("synopsis")).shouldBeEqual("Please enter a movie for synopsis");
        the(movie.errors().get("start_date")).shouldBeEqual("Please enter a movie start date");

		//set missing values
		movie.set("title", "Inception", "trailer_url", "http://www.youtube.com", "synopsis", "good movie", "start_date", Date.valueOf("2011-12-25"));
		//all is good:
		movie = new Movie();
		movie.set("title", "Inception", "trailer_url", "http://www.youtube.com", "synopsis", "good movie", "start_date", Date.valueOf("2011-12-25"));
		the(movie).shouldBe("valid");
	}
}
