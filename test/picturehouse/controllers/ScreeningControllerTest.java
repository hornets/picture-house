package picturehouse.controllers;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import org.javalite.activejdbc.Base;
import static org.javalite.test.jspec.JSpec.the;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import picturehouse.models.Screening;

public class ScreeningControllerTest {
    public ScreeningControllerTest() {}
    
    //  Open database connection before each test and create a new transaction
    @Before
    public void before() {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/picturehouse_test", "testuser", "testuserpassword");
        Base.openTransaction();
    }

    //  Close database connection before after each test and rollback (delete anything added by the test)
    @After
    public void after() {
        Base.rollbackTransaction();
        Base.close();
    }

    @Test
    public void shouldCreateScreening() {
        // create a screening record
        ScreeningController controller = new ScreeningController();
        controller.create(1,10.00, Date.valueOf("2014-02-28"));
        List<Screening> screening = Screening.where("movie_id = 1");
        the(screening.size()).shouldBeEqual(1);
    }

    @Test
    public void shouldUpdateScreening() {
        // create and retrieve a screening record
        ScreeningController controller = new ScreeningController();
        controller.create(1,10.00, Date.valueOf("2014-02-28"));
        Screening screening = Screening.findFirst("start_date = '"+Date.valueOf("2014-02-28")+"'");
        int id =  Integer.parseInt(screening.getString("id"));
        // update screening record
        controller.update(id,1, 20.00, Date.valueOf("2014-02-27"));
        // verify the record exists
        the(Screening.findFirst("start_date = '"+Date.valueOf("2014-02-27")+"'")).shouldBe("valid");
    }
    
 
    @Test
    public void shouldDestroyScreening() {
        // create and retrieve a screening record
        ScreeningController controller = new ScreeningController();
        controller.create(1,10.00, Date.valueOf("2014-02-28"));
        Screening screening = Screening.findFirst("start_date = '"+Date.valueOf("2014-02-28")+"'");
        int id =  Integer.parseInt(screening.getString("id"));
        
        //destroy screening record
        controller.destroy(id);
        // make sure is destroyed
        assertNull(Screening.findFirst("id = ?", id));
    }
       
    @Test
    public void shouldReturnAscreening() {
        // Create a new movie record
        ScreeningController controller = new ScreeningController();
        controller.create(1,10.00, Date.valueOf("2014-02-28"));
        
        // return the newly created newsletter through method
        Screening screeningCache = controller.loadScreening(Date.valueOf("2014-02-28"));
        
        // make sure the return movie is the expected movie
        assertEquals(screeningCache.getDate("start_date"), Date.valueOf("2014-02-28"));
    }
    


    @Test
    public void shouldReturnAListOfScreeningThatScreenAfterSpecificDate() {
        // Create two screening records
        ScreeningController controller = new ScreeningController();
        controller.create(1,10.00, Date.valueOf("2014-02-28"));
        controller.create(2,30.00, Date.valueOf("2014-03-12"));
        controller.create(2,30.00, Date.valueOf("2014-03-14"));

        // make sure no screenings are returned when wrong date is selected
        List<Screening> screenings = controller.showScreeningsAfter(Date.valueOf("2014-03-28"));
        assertEquals(screenings.size(), 0);
        
        // make sure that correct number of screenings are returned given the correct date
        screenings = controller.showScreeningsAfter(Date.valueOf("2014-01-25"));
        assertEquals(screenings.size(), 3);
        
        screenings = controller.showScreeningsAfter(Date.valueOf("2014-03-01"));
        assertEquals(screenings.size(), 2);
    }
    
    
    @Test
    public void shouldReturnAListOfScreeningsForThisAndNextWeekForGivenMovie() {
        // Get calendar set to current date and time
        Calendar c = Calendar.getInstance();
        // Set the calendar to monday of the current week
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

        // Create two screening records
        ScreeningController controller = new ScreeningController();
        controller.create(1,10.00, Date.valueOf(lastWednesdayDate));
        controller.create(2,30.00, Date.valueOf(thisTuesdayDate));
        controller.create(2,15.00, Date.valueOf(nextFridayDate));
        controller.create(2,17.00, Date.valueOf(lastMonthDate));
        
        
        List<Screening> screenings = controller.showCurrentScreeningsForMovie(2);
        assertEquals(screenings.size(), 2);
        // make sure the right screenings are returned
        assertEquals(screenings.get(0).getString("Price"), "30");
        assertEquals(screenings.get(1).getString("Price"), "15");
        
        
    }
    
    @Test
    public void shouldReturnAScreeningBasedOnId() {
         // Create a new movie record
        ScreeningController controller = new ScreeningController();
        controller.create(1,10.00, Date.valueOf("2014-02-28"));
        Screening screening = controller.loadScreening(Date.valueOf("2014-02-28"));
        int id =  Integer.parseInt(screening.getString("id"));

        
        // return the newly created newsletter through method
        Screening screeningCache = controller.show(id);
        
        // System.out.println(id);
        
        // make sure the return movie is the expected movie
        assertEquals(screeningCache.getDate("start_date"), Date.valueOf("2014-02-28"));
    }
}
