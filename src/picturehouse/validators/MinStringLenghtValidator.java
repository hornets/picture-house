package picturehouse.validators;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.validation.ValidatorAdapter;

/**
 *
 * @author sevabaskin
 */


public class MinStringLenghtValidator extends ValidatorAdapter{
    private String attribute;
    private int minLength;
    
    public MinStringLenghtValidator(String attribute, int minLength) {
        this.attribute = attribute;
        this.minLength = minLength;
    }
    
    @Override
    public void validate(Model m){
        boolean valid = true;
        //perform whatever validation logic, then add errors to model if validation did not pass:
       
        // make sure attribute value is not Null
        if(m.get(attribute) == null){
            m.addValidator(this, attribute);
            return;
        }
        
        // assign attribute DB value to  the value variable
        Object value = m.get(attribute);
        
        // verify length of value is at least minLength
        if(value.toString().length() < minLength) {
            m.addValidator(this, attribute);
        }
    }

}
