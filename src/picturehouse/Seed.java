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
        Get calendar set to current date and time
        Calendar c = Calendar.getInstance();
        
        //Set the calendar to monday of the current week
        
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        c.add(Calendar.DATE, -5);
        String lastWednesdayDate = df.format(c.getTime());
        c.add(Calendar.DATE, 5+1);
        String thisTuesdayDate = df.format(c.getTime());
        c.add(Calendar.DATE, 5+5);
        String nextFridayDate = df.format(c.getTime());
        c.add(Calendar.DATE, -35);
        String lastMonthDate = df.format(c.getTime());

        //movies

        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/picturehouse_development", "testuser", "testuserpassword");
        
        MovieController controller = new MovieController();
        //1
        controller.create("The Other Woman", "http://www.youtube.com", "Carly is happily in love with her new boyfriend Mark when she discovers he is a fraud and has a wife.", Date.valueOf(lastWednesdayDate));
        //2
        controller.create("The Amazing Spider-Man 2 3D", "http://www.youtube.com", " Spider-Man is faced with his most powerful foe to date and as his friend Harry Osborne returns to his life, Peter realises that all of his enemies are connected by one thing: OsCorp.", Date.valueOf(thisTuesdayDate));
        //3
        controller.create("Transcendence", "http://www.youtube.com", "When a genius researcher is attacked by anti-technology extremists, their plan to bring him down backfires when he becomes part of his own dangerous experiment.", Date.valueOf(nextFridayDate));
        //4
        controller.create("Pompeii 3D", "http://www.youtube.com", "Set in 79AD at the time when Mount Vesuvius wreaked havoc, a young slave come gladiator named Milo must fight to save the woman of his dreams.", Date.valueOf(lastMonthDate));
  		//5
        controller.create("Rio 2", "http://www.youtube.com", " Blu, Jewel and the kids are back! In this exciting sequel the family are leaving the city and moving to the Amazon to live life as real birds.", Date.valueOf(lastMonthDate));
          
        Base.close();

        //customers
        
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/picturehouse_development", "testuser", "testuserpassword");
        //1
       	CustomerController controller = new CustomerController();
       	controller.create("vlad", "1111222233335555" , "Pa$$w0rd");
       	//2
       	controller.create("katea", "1111222233336666" , "Pa$$ward");
		//3
       	controller.create("seva", "1111222233337777" , "Pa$$wxrd");
        Base.close();

        //reviews
        
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/picturehouse_development", "testuser", "testuserpassword");
        //1
       	MovieReviewController controller = new MovieReviewController();
       	controller.create(1, 1 , "Funny and Comedic At The Same Time");
       	controller.create(2, 1 , "I was all smiles throughout the day!");
       	controller.create(3, 1 , "Funniest movie I've seen in years");

       	controller.create(1, 2 , "The Not So-Amazing Spiderman Movie...");
       	controller.create(2, 2 , "spidey 2 - surprisingly good.");
       	controller.create(3, 2 , "I'm not finding this reboot all that enjoyable");

       	controller.create(1, 3 , "The Line Between Humanity & Evolving into something More.");
       	controller.create(2, 3 , "Exceptionally good at many things, superb at nothing");
       	controller.create(3, 3 , "This seems like wasted potential");

       	controller.create(1, 4 , "Horrible movie, full of clichés.");
       	controller.create(2, 4 , "3D first, Movie second");
       	controller.create(3, 4 , "Awful, awful movie");

       	controller.create(1, 5 , "Joyous, silly and affecting, this is a very welcome return to Rio.");
       	controller.create(2, 5 , "Inherently funny");
       	controller.create(3, 5 , "An enjoyable sequel");


       	Base.close();

       	//screenings

        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/picturehouse_development", "testuser", "testuserpassword");
        
        ScreeningController controller = new ScreeningController();
        //1
        controller.create(1,10, Date.valueOf(lastWednesdayDate));
        //2
        controller.create(2,10, Date.valueOf(thisTuesdayDate));
        //3
        controller.create(3,10, Date.valueOf(nextFridayDate));
        //4
        controller.create(4,10, Date.valueOf(lastMonthDate));
  		//5
        controller.create(5,10, Date.valueOf(lastMonthDate));
          
        Base.close();
        

        //seats
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/picturehouse_development", "testuser", "testuserpassword");
        String[] letters = {"A", "B", "C", "D", "E", "F", "G"};
        for (String letter : letters) {
            for (int j = 1; j < 11; j++) {
                new Seat().set("seat_number", j).set("row_letter", letter).save();
            }
            
        }
        
        Base.close();

        //bookings

        //screenings

        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/picturehouse_development", "testuser", "testuserpassword");
        
        TicketBookingController controller = new TicketBookingController();
        //1
        controller.create(1, 1, 1, FALSE);
        //2
        controller.create(2, 2, 3, TRUE);
        //3
        controller.create(3, 1, 2, TRUE);
        
        Base.close();
        
        
    }
}
