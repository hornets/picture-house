package picturehouse;
import picturehouse.models.Customer;
import org.javalite.activejdbc.Base;


/**
 *
 * @author sevabaskin
 */
// TODO: make as a singleton
public class PictureHouse {

    private boolean isAuthorized;
    private Customer currentCustomer = null;
    public PictureHouse() {
        this.isAuthorized = false;
    }

    public static void main(String[] args) {
//        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/activejdbc_test", "testuser", "testuserpassword");
//        Base.close();
        
        
    }
    public void setCurrentCustomer(Customer customer) {
        this.currentCustomer = customer;
    }
    public Customer getCurrentCustomer() {
        return currentCustomer;
    }
    public boolean isCurrentUserAuthorized() {
        return this.isAuthorized;
    }
    public void authorizeCurrentUser(){
        this.isAuthorized = true;
    }
    public void deauthorizeCurrentUser(){
        this.isAuthorized = false;
    }
    
}
