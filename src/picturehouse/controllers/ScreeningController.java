package picturehouse.controllers;
import java.sql.Date;
import java.util.List;
import picturehouse.models.Seat;
import picturehouse.models.Screening;
import picturehouse.models.TicketBooking;

public class ScreeningController {
    
    public ScreeningController() {
    }
    public void create(Integer movie_id, Double price, Date start_date){
        new Screening().set("movie_id", movie_id)
                       .set("price", price)
                       .set("start_date", start_date)
                       .saveIt();
    }
    public void destroy(int id){
        Screening s = Screening.findFirst("id = ?", id);
        s.delete();
    }
    public void update(int id, Integer movie_id, Double price, Date start_date){
        Screening s = Screening.findFirst("id = ?", id);
        s.set("movie_id", movie_id).set("price", price).set("start_date",start_date).saveIt();
    }
    public Screening loadScreening(Date date){
        Screening screening = Screening.findFirst("start_date = ?", date);
        
        if(screening != null){
            return screening;
        }
        return null;
    }
    public List<Screening> showScreeningsAfter(Date start_date){
        List<Screening> screeningList = Screening.where("start_date > ?", start_date);
        
        if(screeningList != null){
            return screeningList;
        }
        return null;
    }
    
    // load screening based on id
    public Screening show(Integer id){
        Screening screening = Screening.findFirst("id = ?", id);
        
        if(screening != null){
            return screening;
        } 
        return null;
    }
}   