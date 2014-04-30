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
import picturehouse.controllers.ScreeningController;
import picturehouse.models.Screening;
import picturehouse.models.Seat;
import picturehouse.models.TicketBooking;

/**
 *
 * @author sevabaskin
 */
public class DataInjector {
    

        
    public static void main(String[] args) {
//        // Get calendar set to current date and time
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
//        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/picturehouse_development", "testuser", "testuserpassword");
//        MovieController controller = new MovieController();
//        controller.create("Inception", "http://www.youtube.com", "good one", Date.valueOf(lastWednesdayDate));
//        controller.create("Flintstones", "http://www.youtube.com", "good one", Date.valueOf(thisTuesdayDate));
//        controller.create("Good Will Hunting", "http://www.youtube.com", "good one", Date.valueOf(nextFridayDate));
//        controller.create("Pulp fiction", "http://www.youtube.com", "good one", Date.valueOf(lastMonthDate));
//        Base.close();

//        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/picturehouse_development", "testuser", "testuserpassword");
//        MovieReviewController controller = new MovieReviewController();
//        controller.create(1, 1, "very nice movie");
//        controller.create(1, 3, "very nice movie");
//        Base.close();
        
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/picturehouse_development", "testuser", "testuserpassword");
//        String[] letters = {"A", "B", "C", "D", "E", "F", "G"};
//        for (String letter : letters) {
//            for (int j = 1; j < 11; j++) {
//                new Seat().set("seat_number", j).set("row_letter", letter).save();
//            }
//            
//        }
//
//        new Screening().set("movie_id", 2)
//                       .set("price", 12.13)
//                       .set("start_date", Timestamp.valueOf("2014-05-01 14:00:00"))
//                       .save();
//        
//                new TicketBooking().set("customer_id", 1)
//                           .set("screening_id", 5)
//                           .set("seat_id", 4)
//                           .set("is_printed", true)
//                           .save();
//                new TicketBooking().set("customer_id", 1)
//                           .set("screening_id", 5)
//                           .set("seat_id", 23)
//                           .set("is_printed", true)
//                           .save();
        
//
        Base.close();
        
        
    }
}
