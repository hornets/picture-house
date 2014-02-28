package picturehouse.validators;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.Registry;
import org.javalite.activejdbc.validation.ValidatorAdapter;
import picturehouse.models.Customer;


public class CustomerExistsValidator extends ValidatorAdapter{
    private final Integer customerId;

    public CustomerExistsValidator(Integer customerId) {
        this.customerId = customerId;
    }
    
    @Override
    public void validate(Model m){
        boolean valid = true;
        
        Customer c = new Customer();
        
        if(c.findById(this.customerId) == null){
            valid = false;
        }
        
        if(!valid){
            m.addValidator(this, "customer does not exist");
        }
    }
}
