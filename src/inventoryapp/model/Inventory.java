
package inventoryapp.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
    
/**
 * @author William Weaver
 */
public class Inventory {
    
    @FXML
    public TableView<Part> PartTable;
    @FXML
    private TableColumn<Part, Integer> PartIdColumn;
    @FXML
    private TableColumn<Part, String> PartNameColumn;
    @FXML
    private TableColumn<Part, Integer> PartInventoryColumn;
    @FXML
    private TableColumn<Part, Double> PartPriceColumn;   
    
    public Inventory() {
    }
 
    public static ObservableList <Part> PartData = FXCollections.observableArrayList();
    public static ObservableList <Product> ProductData = FXCollections.observableArrayList();
    
    public static ObservableList<Part> getPartData() {
        return PartData;
    }
    
    public static ObservableList<Product> getProductData() {
        return ProductData;
    } 
}