
package inventoryapp.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * @author William Weaver
 */
public class InHousePart extends Part {
    
    public IntegerProperty machID;
    
    public InHousePart() {
    }
    
    public int getMachID() {
        return machID.get();
    }
    
    public void setMachID(int machID) {
        this.machID =  new SimpleIntegerProperty(machID);
    }
    
    public IntegerProperty machIDProperty() { 
        return machID;
    }
}
