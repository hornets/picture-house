/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package picturehouse;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.javalite.activejdbc.Base;
import picturehouse.controllers.MovieController;
import picturehouse.controllers.MovieReviewController;

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

        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/picturehouse_development", "testuser", "testuserpassword");
        MovieReviewController controller = new MovieReviewController();
//        controller.create(1, 1, "very nice movie");
        controller.create(1, 3, "very nice movie");
        Base.close();
    }
}
