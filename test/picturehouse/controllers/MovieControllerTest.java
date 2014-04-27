package picturehouse.controllers;



import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import org.javalite.activejdbc.Base;
import static org.javalite.test.jspec.JSpec.the;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import picturehouse.models.Movie;


/**
 *
 * @author sevabaskin
 */
public class MovieControllerTest {
    public MovieControllerTest() {}
    
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

    @Test
    public void shouldCreateMovies() {
        MovieController controller = new MovieController();
        controller.create("Inception", "http://www.youtube.com", "good one", Date.valueOf("2011-12-25"));
        List<Movie> movies = Movie.where("title = 'Inception'");
        the(movies.size()).shouldBeEqual(1);
    }


    @Test
    public void shouldUpdateMovies() {
        // Create a new movie record
        MovieController controller = new MovieController();
        controller.create("Inception", "http://www.youtube.com", "good one", Date.valueOf("2011-12-25"));
        Movie movie = Movie.findFirst("title = 'Inception'");
        int id =  Integer.parseInt(movie.getString("id"));

        // try updating the record
        controller.update(id, "Flintstones", "http://www.youtube.com", "good one", Date.valueOf("2011-12-25"));
        the(Movie.findFirst("title = 'Flintstones'")).shouldBe("valid");
    }
    @Test
    public void shouldDestroyMovies() {
        // Create and retrieve new movie record
        MovieController controller = new MovieController();
        controller.create("Inception", "http://www.youtube.com", "good one", Date.valueOf("2011-12-25"));
        Movie movie = Movie.findFirst("title = 'Inception'");
        int id =  Integer.parseInt(movie.getString("id"));
        // destory the record
        controller.destroy(id);
        // make sure is destroyed
        assertNull(Movie.findFirst("id = ?", id));
    }

    @Test
    public void shouldReturnAMovieList() {
        // Create and retrieve new movie record
        MovieController controller = new MovieController();
        controller.create("Inception", "http://www.youtube.com", "good one", Date.valueOf("2011-12-25"));
        controller.create("Flintstones", "http://www.youtube.com", "good one", Date.valueOf("2011-12-27"));

        // make sure no movies are returned, when there're no movies after the specified date
        List<Movie> movies = controller.showMoviesAfter(Date.valueOf("2012-12-25"));
        assertEquals(movies.size(), 0);
        // make sure that correct number of movies are returned given the date
        movies = controller.showMoviesAfter(Date.valueOf("2011-12-24"));
        assertEquals(movies.size(), 2);
        movies = controller.showMoviesAfter(Date.valueOf("2011-12-26"));
        assertEquals(movies.size(), 1);
    }

    @Test
    public void shouldReturnNextWeekMovies() {
        // Get calendar set to current date and time
        Calendar c = Calendar.getInstance();
        // Set the calendar to monday of the current week
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        c.add(Calendar.DATE, -5);
        String lastWednesdayDate = df.format(c.getTime());
        c.add(Calendar.DATE, 5+1);
        String thisTuesdayDate = df.format(c.getTime());
        c.add(Calendar.DATE, 5+5);
        String nextFridayDate = df.format(c.getTime());
        c.add(Calendar.DATE, -35);
        String lastMonthDate = df.format(c.getTime());

        // Create and retrieve new movie record
        MovieController controller = new MovieController();
        controller.create("Inception", "http://www.youtube.com", "good one", Date.valueOf(lastWednesdayDate));
        controller.create("Flintstones", "http://www.youtube.com", "good one", Date.valueOf(thisTuesdayDate));
        controller.create("Good Will Hunting", "http://www.youtube.com", "good one", Date.valueOf(nextFridayDate));
        controller.create("Pulp fiction", "http://www.youtube.com", "good one", Date.valueOf(lastMonthDate));
        
        Movie movie = null;
        List<Movie> lastWeekMovies = controller.showLastWeekMovies();
        assertEquals(lastWeekMovies.size(), 1);
        movie = lastWeekMovies.get(0);
        assertEquals(movie.getString("title"), "Inception");
        
        List<Movie> nextWeekMovies = controller.showThisAndNextWeekMovies();
        assertEquals(nextWeekMovies.size(), 2);
        movie = nextWeekMovies.get(0);
        assertEquals(movie.getString("title"), "Flintstones");
        movie = nextWeekMovies.get(1);
        assertEquals(movie.getString("title"), "Good Will Hunting");
        
    }
    
    @Test
    public void shouldReturnAMovie() {
        // Create and retrieve new movie record
        MovieController controller = new MovieController();
        controller.create("Inception", "http://www.youtube.com", "good one", Date.valueOf("2011-12-25"));
        Movie movie = Movie.findFirst("title = 'Inception'");
        int id =  Integer.parseInt(movie.getString("id"));

        // return the newly created movie
        movie = controller.show(id);
        
        // make sure the return movie is the expected movie
        assertEquals(movie.getString("title"), "Inception");
    }

}
