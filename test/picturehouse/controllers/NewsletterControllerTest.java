package picturehouse.controllers;

import java.sql.Date;
import java.util.List;
import org.javalite.activejdbc.Base;
import static org.javalite.test.jspec.JSpec.the;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import picturehouse.models.Newsletter;


/**
 *
 * @author Most likely Seva Baskin
 */

public class NewsletterControllerTest {
    public NewsletterControllerTest() {}
    
    //  Open database connection before each test and create a new transaction
    @Before
    public void before() {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:8889/picturehouse_test", "root", "root");
        Base.openTransaction();
    }

    //  Close database connection before after each test and rollback (delete anything added by the test)
    @After
    public void after() {
        Base.rollbackTransaction();
        Base.close();
    }

    @Test
    public void shouldCreateNewsletter() {
        // create newsletter record
        NewsletterController controller = new NewsletterController();
        controller.create("Some Newsletter Content", Date.valueOf("2014-02-28"));
        List<Newsletter> newsletter = Newsletter.where("date = '"+Date.valueOf("2014-02-28")+"'");
        the(newsletter.size()).shouldBeEqual(1);
    }


    @Test
    public void shouldUpdateNewsletter() {
        // create and retrieve a newsletter record
        NewsletterController controller = new NewsletterController();
        controller.create("Some Newsletter Content", Date.valueOf("2014-02-28"));
        Newsletter newsletter = Newsletter.findFirst("date = '"+Date.valueOf("2014-02-28")+"'");
        int id =  Integer.parseInt(newsletter.getString("id"));
        // update newsletter record
        controller.update(id, "Some Newsletter Content", Date.valueOf("2014-02-27"));
        // verify the record exists
        the(Newsletter.findFirst("date = '"+Date.valueOf("2014-02-27")+"'")).shouldBe("valid");
    }
    
    
    @Test
    public void shouldDestroyNewsletter() {
        // create and retrieve a newsletter record
        NewsletterController controller = new NewsletterController();
        controller.create("Some Newsletter Content", Date.valueOf("2014-02-28"));
        Newsletter newsletter = Newsletter.findFirst("date = '"+Date.valueOf("2014-02-28")+"'");
        int id =  Integer.parseInt(newsletter.getString("id"));
        
        //destroy newsletter record
        controller.destroy(id);
        // make sure is destroyed
        assertNull(Newsletter.findFirst("id = ?", id));
    }
    
    @Test
    public void shouldReturnANewsletter() {
        // Create a new movie record
        NewsletterController controller = new NewsletterController();
        controller.create("Some Newsletter Content", Date.valueOf("2014-02-28"));
        
        // return the newly created newsletter through method
        Newsletter newsletterCache = controller.loadNewsletter(Date.valueOf("2014-02-28"));
        
        // make sure the return movie is the expected movie
        assertEquals(newsletterCache.getDate("date"), Date.valueOf("2014-02-28"));
    }
    

    @Test
    public void shouldReturnANewsletterList() {
        // Create two newsletter records
        NewsletterController controller = new NewsletterController();
        controller.create("Some Newsletter Content", Date.valueOf("2014-02-27"));
        controller.create("Some Other Newsletter Content", Date.valueOf("2014-01-27"));

        // make sure no newsletters are returned when wrong dates are selected
        List<Newsletter> newsletters = controller.searchNewsletter(Date.valueOf("2014-02-28"),Date.valueOf("2014-03-05"));
        assertEquals(newsletters.size(), 0);
        
        // make sure that correct number of newsletters are returned given the correct dates
        newsletters = controller.searchNewsletter(Date.valueOf("2014-01-25"),Date.valueOf("2014-02-28"));
        assertEquals(newsletters.size(), 2);
        
        newsletters = controller.searchNewsletter(Date.valueOf("2014-01-29"),Date.valueOf("2014-02-28"));
        assertEquals(newsletters.size(), 1);
    }
}
