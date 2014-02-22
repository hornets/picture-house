package picturehouse.validators;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.validation.ValidatorAdapter;


/**
 *
 * @author sevabaskin
 */
public class UniquenessValidator extends ValidatorAdapter {

//    private final String attribute;
//
//    public UniquenessValidator(String attribute) {
//        this.attribute = attribute;
//        setMessage("should be unique");
//    }
//
//    
//    @Override
//    public void validate(Model m) {
//        if(Base.count(this.getMetaModelLocal().getTableName(), attribute + " = ? AND id IS NOT ?", m.get(attribute), m.getId()) > 0) {
//            m.addValidator(this, attribute);
//        }
//    }

    @Override
    public void validate(Model t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
