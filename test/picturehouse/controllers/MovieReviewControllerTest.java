package picturehouse.controllers;

import java.sql.Date;
import org.javalite.activejdbc.Base;
import static org.javalite.test.jspec.JSpec.the;
import org.junit.After;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import picturehouse.models.Customer;
import picturehouse.models.Movie;
import picturehouse.models.MovieReview;

/**
 *
 * @author Akshay, sevabaskin
 */

public class MovieReviewControllerTest {
    public MovieReviewControllerTest() {}
    
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

    /**
     * Test of methods is MovieReview, of class MovieReviewController.
     */
    @Test
    public void testMovieReviewController() {
		
		new CustomerController().create("johnDoe", "Passw0rdA1b", "1111222233334444");
		int customer_id =  Integer.parseInt(Customer.findFirst("username = 'johnDoe'").getString("id"));

		new MovieController().create("Inception", "http://www.youtube.com", "good one", Date.valueOf("2011-12-25"));
		int movie_id =  Integer.parseInt(Movie.findFirst("title = 'Inception'").getString("id"));

		// test creating a new movie review
		MovieReviewController controller = new MovieReviewController();
		controller.create(customer_id, movie_id, "very nice movie");
		MovieReview mr = MovieReview.findFirst("content = 'very nice movie'");
		int movie_review_id =  Integer.parseInt(mr.getString("id"));
        the(mr).shouldBe("valid");

		// test updating the MovieReview record
        controller.update(movie_review_id, customer_id, movie_id, "actually, not such a nice movie");
        mr = MovieReview.findFirst("content = 'actually, not such a nice movie'");

		// test destroying the MovieReview record
        controller.destroy(movie_review_id);
        assertNull(MovieReview.findFirst("id = ?", movie_review_id));
    }


}
