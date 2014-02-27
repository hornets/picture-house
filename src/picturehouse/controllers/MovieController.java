package picturehouse.controllers;
import picturehouse.models.Movie;
import java.sql.Date;
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
                   .saveIt();
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
    public Movie showMovie(int id){
        Movie movie = Movie.findFirst("id = ?", id);
        return movie; 
    }
    public List<Movie> showMoviesAfter(Date premieredAfter){
        List<Movie> movies = Movie.where("start_date > ?", premieredAfter);
        return movies;
    }
}