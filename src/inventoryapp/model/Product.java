
package inventoryapp.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author William Weaver
 */
public class Product {
    
    private ObservableList<Part> prodmembers = FXCollections.observableArrayList();
    
    public IntegerProperty productID;
    protected StringProperty productName;
    protected DoubleProperty productPrice;
    protected IntegerProperty productInStock;
    protected IntegerProperty productMax;
    protected IntegerProperty productMin;

    static int numOfProducts;
    
    public Product() {
        productID = new SimpleIntegerProperty(0);
        productName =  new SimpleStringProperty("product");
        productPrice = new SimpleDoubleProperty(2.0);
        productInStock = new SimpleIntegerProperty();
        productMax = new SimpleIntegerProperty();
        productMin = new SimpleIntegerProperty();
    }
    
    public Product(int productID, String productName, int productInStock, double productPrice, int productMax, int productMin, Part firstPart) {
        //this.productID = new SimpleIntegerProperty(productID);
        this .productName = new SimpleStringProperty(productName);
        this.productPrice = new SimpleDoubleProperty(productPrice);
        this.productInStock = new SimpleIntegerProperty(productInStock);
        prodmembers.add(firstPart);
    }
    
    public ObservableList <Part> getmembers() {
        return prodmembers;
    }
    
    public void setMembers(ObservableList <Part> members) {
        prodmembers = members;
    }
            
    
    public void addPart(Part part) {
        prodmembers.add(part);
    }

    public Integer getProductID() {
        return productID.get();
    }

    public void setProductID(int productID) {
        this.productID = new SimpleIntegerProperty(productID);     
    }
    
    public String getProductName() {
        return productName.get();
    }

    public void setProductName(String productName) {
        this.productName = new SimpleStringProperty(productName);
    }

    public Integer getProductInStock() {
        return productInStock.get();
    }

    public void setProductInStock(Integer productInStock) {
        this.productInStock = new SimpleIntegerProperty(productInStock);
    }

    public Double getProductPrice() {
        return productPrice.get();
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = new SimpleDoubleProperty(productPrice);
    }
    
    public Integer getProductMax() {
        return productMax.get();
    }

    public void setProductMax(Integer productMax) {
        this.productMax = new SimpleIntegerProperty(productMax);
    }

    public Integer getProductMin() {
        return productMin.get();
    }

    public void setProductMin(Integer productMin) {
        this.productMin = new SimpleIntegerProperty(productMin);
    }
    
    public void addAssociatedPart() {
    }
    
    public static Integer lookupAssociatedPart() {
        return null;       
    }
    
    public boolean removeAssociatedPart() {
        return false;       
    }

    public IntegerProperty productIDProperty() {
        return productID;
    }

    public StringProperty productNameProperty() {
        return productName;
    }
    
    public IntegerProperty productInStockProperty() {
        return productInStock;
    }

    public DoubleProperty productPriceProperty() {
        return productPrice;
    }
    
    public IntegerProperty productMaxProperty() {
        return productMax;
    }
    
    public IntegerProperty productMinProperty() {
        return productMin;
    }
}