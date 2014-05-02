package picturehouse.controllers;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import picturehouse.models.Screening;

public class ScreeningController {
    
    public ScreeningController() {
    }
    public void create(Integer movie_id, Double price, Timestamp start_date){
        new Screening().set("movie_id", movie_id)
                       .set("price", price)
                       .set("start_date", start_date)
                       .save();
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
        return Screening.where("start_date > ?", start_date);
    }
    public List<Screening> showCurrentScreeningsForMovie(int movie_id) {
        // Get calendar set to current date and time
        Calendar c = Calendar.getInstance();
        // Set the calendar to monday of the current week
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        // Get this Monday date
        String thisMonday = df.format(c.getTime());
        thisMonday += " 00:00:00";
        // Get next Sunday date
        c.add(Calendar.DATE, 13);
        String nextSunday = df.format(c.getTime());
        nextSunday+=" 23:59:59";
        return Screening.where("movie_id = ? AND start_date BETWEEN ? AND ? ORDER BY start_date ASC", movie_id, thisMonday, nextSunday);        
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