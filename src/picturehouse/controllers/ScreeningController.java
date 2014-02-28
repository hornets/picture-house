package picturehouse.controllers;
import java.sql.Date;
import java.util.List;
import picturehouse.models.RoomPlan;
import picturehouse.models.Screening;
import picturehouse.models.SeatBooking;

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
    public List<Screening> searchScreening(Date start_date){
        List<Screening> screeningList = Screening.where("start_date > ?", start_date);
        
        if(screeningList != null){
            return screeningList;
        }
        return null;
    }
    // load screening based on id
    public Screening load(Integer id){
        Screening screening = Screening.findFirst("id = ?", id);
        
        if(screening != null){
            return screening;
        } 
        return null;
    }
    public boolean isSeated(Integer id, Integer seat){
        RoomPlan room = RoomPlan.findFirst("seat_number = ?", seat);
        int seat_id =  Integer.parseInt(room.getString("id"));
        
        SeatBooking booking = SeatBooking.findFirst("seat_id = ? AND screening_id = ?", seat_id, id);
        
        return booking != null;        
    }
}   