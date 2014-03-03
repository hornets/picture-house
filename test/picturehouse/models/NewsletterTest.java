package picturehouse.models;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.javalite.test.jspec.JSpec.the;

public class NewsletterTest {
    //  Open database connection before each test and create a new transaction
	@Before
	public void before() {
		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://sql3.freesqldatabase.com:3306/sql331660", "sql331660", "bY9!dI1%");
		Base.openTransaction();
	}

    //  Close database connection before after each test and rollback (delete anything added by the test)
	@After
	public void after() {
		Base.rollbackTransaction();
		Base.close();
	}


	public NewsletterTest() {
	}

	@Test
	public void shouldValidateMandatoryFields() {
		Newsletter newsletter = new Newsletter();

		//validation rules
        the(newsletter).shouldNotBe("valid");
		the(newsletter.errors().get("content")).shouldBeEqual("The content of this newsletter cannot be left empty");
        the(newsletter.errors().get("date")).shouldBeEqual("Please enter the date you wish the newsletter to be published");

		//set attributes
		newsletter.set("content", "some major content", "date", "2014-02-28");
		//validation should pass now
		newsletter = new Newsletter();
		newsletter.set("content", "Like a boss", "date", "2014-02-28");
		the(newsletter).shouldBe("valid");
	}
}
