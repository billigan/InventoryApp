
package inventoryapp.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author William Weaver
 */
public abstract class Part 
{
    protected IntegerProperty partID; 
    protected StringProperty name;   
    protected IntegerProperty inStock;               
    protected DoubleProperty price;
    protected IntegerProperty max;               
    protected IntegerProperty min;

    static int numOfParts; 
   
   public Part()
   {   
        partID = new SimpleIntegerProperty(numOfParts++);
        name =  new SimpleStringProperty("part");
        price = new SimpleDoubleProperty(2.0);
        inStock = new SimpleIntegerProperty();
        min = new SimpleIntegerProperty();
        max = new SimpleIntegerProperty();

   }
    public Integer getPartID() {
        return partID.get();
    }
    
    public String getName() {
        return name.get();
    }
    
    public Integer getInStock() {
        return inStock.get();
    }

    public Double getPrice() {
        return price.get();
    } 
    
    public int getMax() {
        return max.get();
    }
    
    public int getMin() {
        return min.get();
    }
    
    public void setPartID(Integer id) {
        this.partID.set(id);
    }  
    
    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }
    
    public void setInStock(int inv) {
        this.inStock = new SimpleIntegerProperty(inv);
    }
    
    public void setPrice(Double price) {
        this.price = new SimpleDoubleProperty(price);
    }
        
    public void setMax(int max) {
        this.max = new SimpleIntegerProperty(max);
    }
        
    public void setMin(int min) {
        this.min = new SimpleIntegerProperty(min);
    }  
   
    public IntegerProperty partIDProperty() {
        return partID;        
    }

    public StringProperty nameProperty() { 
        return name;
    }

    public IntegerProperty inStockProperty() { 
        return inStock;
    }

    public DoubleProperty priceProperty() {
        return price;
    }
    public IntegerProperty maxProperty() {
        return max;
    }
    public IntegerProperty minProperty() { 
        return min;
    }
}