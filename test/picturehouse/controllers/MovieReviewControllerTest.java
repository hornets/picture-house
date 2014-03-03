package picturehouse.controllers;

import org.javalite.activejdbc.Base;
import static org.javalite.test.jspec.JSpec.the;
import org.junit.After;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
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
		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://sql3.freesqldatabase.com:3306/sql331660", "sql331660", "bY9!dI1%");
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

		// create fake ids
		int customer_id = 1;
		int movie_id = 1;

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
