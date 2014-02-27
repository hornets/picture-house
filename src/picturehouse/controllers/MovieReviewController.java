package picturehouse.controllers;
import picturehouse.models.MovieReview;


/**
 *
 * @author Akshay, sevabaskin
 */
public class MovieReviewController {
    
    public MovieReviewController() {
    }
    public void create(int customer_id, int movie_id, String content){
        new MovieReview().set("customer_id", customer_id)
                         .set("movie_id", movie_id)
                         .set("content", content)
                         .saveIt();
    }
    public void destroy(int id){
        MovieReview m = MovieReview.findFirst("id = ?", id);
        m.delete();
    }
    public void update(int id, int customer_id, int movie_id, String content){
        MovieReview m = MovieReview.findFirst("id = ?", id);
        m.set("customer_id", customer_id).set("movie_id", movie_id).set("content", content).saveIt();
    }
}
