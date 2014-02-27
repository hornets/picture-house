package picturehouse.controllers;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.javalite.test.jspec.JSpec.the;
import picturehouse.models.Customer;

/**
 *
 * @author sevabaskin
 */
public class CustomerControllerTest {
    public CustomerControllerTest() {}
    
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


    /**
     * Test of verifyCredentials method, of class CustomerController.
     */
    @Test
    public void testVerifyCredentials() {
        // Create a new customer record
        CustomerController controller = new CustomerController();
        controller.create("Marilyn", "Passw0rd", "1111222233334444");

        // try logging in with wrong credentials should return false
        assertFalse(controller.verifyCredentials("Marilyn", "Passw0rds"));
        
        // try logging in with correct credentials should return false
        assertTrue(controller.verifyCredentials("Marilyn", "Passw0rd"));
    }
    
    /**
     * Test of update method, of class CustomerController.
     */
    @Test
    public void testUpdateCustomer() {
        // Create a new customer record
        CustomerController controller = new CustomerController();
        controller.create("Marilyn", "Passw0rd", "1111222233334444");
        Customer customer = Customer.findFirst("username = 'Marilyn'");
        int id =  Integer.parseInt(customer.getString("id"));

        // try updating the record
        controller.update(id, "Marilyns", "Passw0rd", "1111222233334444");
        the(Customer.findFirst("username = 'Marilyns'")).shouldBe("valid");
    }

}
