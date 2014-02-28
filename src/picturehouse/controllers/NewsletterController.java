package picturehouse.controllers;
import java.sql.Date;
import java.util.List;
import picturehouse.models.Newsletter;

/**
 *
 * @author Probably Seva Baskin
 */

public class NewsletterController {
    
    public NewsletterController() {
    }
    public void create(String content, Date start_date){
        new Newsletter().set("content", content)
                         .set("date", start_date)
                         .saveIt();
    }
    public void destroy(int id){
        Newsletter n = Newsletter.findFirst("id = ?", id);
        n.delete();
    }
    public void update(int id, String content, Date start_date){
        Newsletter n = Newsletter.findFirst("id = ?", id);
        n.set("content", content).set("date", start_date).saveIt();
    }
    public Newsletter loadNewsletter(Date date){
        Newsletter newsletter = Newsletter.findFirst("date = ?", date);
        
        if(newsletter != null){
            return newsletter;
        }
        
        return null;
    }
    public List<Newsletter> searchNewsletter(Date start_date, Date end_date){
        List<Newsletter> newsletterList = Newsletter.where("date > ? AND date < ?", start_date, end_date);
        
        if(newsletterList != null){
            return newsletterList;
        }
        
        return null;
    }
}
