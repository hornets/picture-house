/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package picturehouse;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.javalite.activejdbc.Base;
import picturehouse.controllers.MovieController;
import picturehouse.controllers.MovieReviewController;
import picturehouse.controllers.NewsletterController;
import picturehouse.controllers.ScreeningController;
import picturehouse.controllers.TicketBookingController;
import picturehouse.models.Screening;
import picturehouse.models.Seat;
import picturehouse.models.TicketBooking;

/**
 *
 * @author sevabaskin
 */
public class DataInjector {
    

        
    public static void main(String[] args) {

//        Base.close();

//        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://db4free.net:3306/picturehouse", "picturehouse", "65CEerFwXESQmL9nDaE");
//        MovieReviewController controller = new MovieReviewController();
//        controller.create(1, 1, "very nice movie");
//        controller.create(1, 3, "very nice movie");
//        Base.close();
        
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://db4free.net:3306/picturehouse", "picturehouse", "65CEerFwXESQmL9nDaE");

        // Get calendar set to current date and time
//        Calendar c = Calendar.getInstance();
//        // Set the calendar to monday of the current week
//        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        c.add(Calendar.DATE, -5);
//        String lastWednesdayDate = df.format(c.getTime());
//        c.add(Calendar.DATE, 5+1);
//        String thisTuesdayDate = df.format(c.getTime());
//        c.add(Calendar.DATE, 5+5);
//        String nextFridayDate = df.format(c.getTime());
//        c.add(Calendar.DATE, -35);
//        String lastMonthDate = df.format(c.getTime());
//        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://db4free.net:3306/picturehouse", "picturehouse", "65CEerFwXESQmL9nDaE");
//        MovieController controller = new MovieController();
//        controller.create("Inception", "http://www.youtube.com", "good one", Date.valueOf(lastWednesdayDate));
//        controller.create("Flintstones", "http://www.youtube.com", "good one", Date.valueOf(thisTuesdayDate));
//        controller.create("Good Will Hunting", "http://www.youtube.com", "good one", Date.valueOf(nextFridayDate));
//        controller.create("Pulp fiction", "http://www.youtube.com", "good one", Date.valueOf(lastMonthDate));
        
//        String[] letters = {"A", "B", "C", "D", "E", "F", "G"};
//        for (String letter : letters) {
//            for (int j = 1; j < 11; j++) {
//                new Seat().set("seat_number", j).set("row_letter", letter).save();
//            }
//            
//        }
            ScreeningController controller = new ScreeningController();
            controller.create(1,10.00, Timestamp.valueOf("2014-05-07 10:00:00"));
            controller.create(1,10.00, Timestamp.valueOf("2014-05-13 10:00:00"));
            controller.create(2,10.00, Timestamp.valueOf("2014-05-20 10:00:00"));
            controller.create(2,10.00, Timestamp.valueOf("2014-05-26 10:00:00"));
            controller.create(3,10.00, Timestamp.valueOf("2014-05-25 10:00:00"));
            
            controller.create(1,10.00, Timestamp.valueOf("2014-06-01 10:00:00"));
            controller.create(1,10.00, Timestamp.valueOf("2014-06-02 10:00:00"));
            controller.create(2,10.00, Timestamp.valueOf("2014-06-05 10:00:00"));
            controller.create(2,10.00, Timestamp.valueOf("2014-06-06 10:00:00"));
            controller.create(3,10.00, Timestamp.valueOf("2014-06-09 10:00:00"));
            
            controller.create(1,10.00, Timestamp.valueOf("2014-06-10 10:00:00"));
            controller.create(1,10.00, Timestamp.valueOf("2014-06-11 10:00:00"));
            controller.create(2,10.00, Timestamp.valueOf("2014-06-12 10:00:00"));
            controller.create(2,10.00, Timestamp.valueOf("2014-06-18 10:00:00"));
            controller.create(3,10.00, Timestamp.valueOf("2014-06-23 10:00:00"));
            
            controller.create(1,10.00, Timestamp.valueOf("2014-06-14 10:00:00"));
            controller.create(1,10.00, Timestamp.valueOf("2014-06-16 10:00:00"));
            controller.create(2,10.00, Timestamp.valueOf("2014-06-17 10:00:00"));
            controller.create(2,10.00, Timestamp.valueOf("2014-06-24 10:00:00"));
            controller.create(3,10.00, Timestamp.valueOf("2014-06-28 10:00:00"));
//        new Screening().set("movie_id", 2)
//                       .set("price", 12.13)
//                       .set("start_date", Timestamp.valueOf("2014-05-01 14:00:00"))
//                       .save();
//        TicketBookingController controller = new TicketBookingController();
//        controller.create(1, 5, 4, false);
//                new TicketBooking().set("customer_id", 1)
//                           .set("screening_id", 5)
//                           .set("seat_id", 4)
//                           .set("is_printed", false)
//                           .save();
//                new TicketBooking().set("customer_id", 3)
//                           .set("screening_id", 5)
//                           .set("seat_id", 23)
//                           .set("is_printed", false)
//                           .save();
//        TicketBooking tb = TicketBooking.findFirst("id = ?", 20);
//        tb.print();
        
//
        Base.close();
        
        
    }
}
