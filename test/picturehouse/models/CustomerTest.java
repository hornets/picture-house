package picturehouse.models;

//import picturehouse.models.Customer;
import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.javalite.test.jspec.JSpec.the;


/**
 *
 * @author sevabaskin
 */
public class CustomerTest {
    @Before
    public void before(){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/picturehouse_test", "testuser", "testuserpassword");
        Base.openTransaction();
    }

    @After
    public void after(){
        Base.rollbackTransaction();
        Base.close();
    }
    
    public CustomerTest() {
    }
   

    @Test
    public void shouldValidateMandatoryFields(){
        Customer customer = new Customer();

        //check errors
        the(customer).shouldNotBe("valid");
        the(customer.errors().get("username")).shouldBeEqual("Please, provide your username");
        the(customer.errors().get("credit_card_number")).shouldBeEqual("Please, provide your credit card number");
        the(customer.errors().get("password")).shouldBeEqual("Please, provide a password");
        
        //set missing values
        customer.set("username", "johnDoe", "credit_card_number", "1111222233334444", "password", "pa$$w0rd");
        
        //all is good:
        the(customer).shouldBe("valid");
    }
    
    @Test
    public void shouldValidateUniqueUsername(){
    }

    
    @Test
    public void shouldValidatePasswordIsSecure(){}
    
    @Test
    public void shouldValidateCreditCardNumber(){}
}
