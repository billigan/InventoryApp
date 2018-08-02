
package inventoryapp.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author William Weaver
 */
public class OutsourcedPart extends Part {
    
    public StringProperty companyName;
    
    public OutsourcedPart() {
    }
     
    public String getCompanyName() {
        return companyName.get();
    }

    public void setCompanyName(String companyName) {
        this.companyName = new SimpleStringProperty(companyName);
    }
    
        public StringProperty companyNameProperty() { 
        return companyName;
    }   
}