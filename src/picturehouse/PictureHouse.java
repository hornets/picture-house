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
        
    public PictureHouse() {
        this.isAuthorized = false;
    }

    public static void main(String[] args) {
//        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/activejdbc_test", "testuser", "testuserpassword");
//        Base.close();
        
        
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
