package picturehouse.controllers;
import picturehouse.models.Movie;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


/**
 *
 * @author Akshay, sevabaskin
 */
public class MovieController {
    public MovieController(){}

    public void create(String title, String trailer_url, String synopsis, Date start_date){
        new Movie().set("title", title)
                   .set("trailer_url", trailer_url)
                   .set("synopsis", synopsis)
                   .set("start_date", start_date)
                   .save();
    }
    public void update(int id, String title, String trailer_url, String synopsis, Date start_date){
        Movie.findFirst("id = ?", id).set("title", title)
                                     .set("trailer_url", trailer_url)
                                     .set("synopsis", synopsis)
                                     .set("start_date", start_date)
                                     .saveIt();
    }
    public void destroy(int id){
        Movie.findFirst("id = ?", id).delete();
    }
    public Movie show(int id){
        return Movie.findFirst("id = ?", id);
    }
    public List<Movie> showMoviesAfter(Date premieredAfter){
        return Movie.where("start_date > ?", premieredAfter);
    }


    public List<Movie> showLastWeekMovies() {
        // Get calendar set to current date and time
        Calendar c = Calendar.getInstance();
        // Set the calendar to monday of the current week
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        // Get last Monday
        c.add(Calendar.DATE, -7);
        String lastMonday = df.format(c.getTime());
        // Get last Sunday
        c.add(Calendar.DATE, 6);
        String lastSunday = df.format(c.getTime());
        return Movie.findBySQL("select distinct m.id, m.title FROM movies m INNER JOIN screenings s ON (m.id = s.movie_id) where s.start_date BETWEEN ? AND ?",  lastMonday, lastSunday);
    }

    public List<Movie> showThisAndNextWeekMovies() {
        // Get calendar set to current date and time
        Calendar c = Calendar.getInstance();
        // Set the calendar to monday of the current week
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        // Get this Monday date
        String thisMonday = df.format(c.getTime());
        // Get next Sunday date
        c.add(Calendar.DATE, 13);
        String nextSunday = df.format(c.getTime());
        return Movie.findBySQL("select distinct m.id, m.title FROM movies m INNER JOIN screenings s ON (m.id = s.movie_id) where s.start_date BETWEEN ? AND ?",  thisMonday, nextSunday);
    }
}