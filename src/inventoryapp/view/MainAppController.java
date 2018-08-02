
package inventoryapp.view;

import inventoryapp.model.InHousePart;
import inventoryapp.model.Inventory;
import inventoryapp.model.OutsourcedPart;
import inventoryapp.model.Part;
import inventoryapp.model.Product;
import java.io.IOException;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author William Weaver
 */
public class MainAppController {
    
    public static Part selectedPart;
    public static Product selectedProduct;
        
    @FXML
    TableView<Part> PartTable;
    @FXML
    private TableColumn<Part, Integer> PartIdColumn;
    @FXML
    private TableColumn<Part, String> PartNameColumn;
    @FXML
    private TableColumn<Part, Integer> PartInventoryColumn;
    @FXML
    private TableColumn<Part, Double> PartPriceColumn;   
    @FXML
    private TextField textPartSearch;
    @FXML
    private Button addPart;
    @FXML
    private Button modifyPart;
    @FXML
    private Button deletePart;
    
    @FXML
    private TableView<Product> ProductTable;
    @FXML
    private TableColumn<Product, Integer> ProductIdColumn;
    @FXML
    private TableColumn<Product, String> ProductNameColumn;
    @FXML
    private TableColumn<Product, Integer> ProductInventoryColumn;    
    @FXML
    private TableColumn<Product, Double> ProductPriceColumn;
    @FXML
    private Button searchPartsButton;
    @FXML
    private Button searchProductsButton;
    @FXML
    private TextField textProductSearch;
    @FXML
    private Button addProduct;
    @FXML
    private Button modifyProduct;
    @FXML
    private Button deleteProduct;
       
    public MainAppController(){        
    }
    
    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        // Initialize the Part and Product tables and columns
        PartIdColumn.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        PartNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        PartInventoryColumn.setCellValueFactory(cellData -> cellData.getValue().inStockProperty().asObject());
        PartPriceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        
        ProductIdColumn.setCellValueFactory(cellData -> cellData.getValue().productIDProperty().asObject());
        ProductNameColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        ProductInventoryColumn.setCellValueFactory(cellData -> cellData.getValue().productInStockProperty().asObject());
        ProductPriceColumn.setCellValueFactory(cellData -> cellData.getValue().productPriceProperty().asObject()); 
        
        PartTable.setItems(Inventory.PartData);
        ProductTable.setItems(Inventory.ProductData);       
    } 
    
    @FXML
    void handleNewPart(ActionEvent event) throws IOException {
        try {

            Stage stage;
            Parent root;

            stage=(Stage) searchPartsButton.getScene().getWindow();
            //load up other FXML documnet
            root = FXMLLoader.load(getClass().getResource("AddInHousePart.fxml"));    
            //create a new scene with root and set the stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();   
        } catch (IOException e) {
      }
    }
    
    @FXML
    private void handleModifyPart() throws IOException {
    selectedPart = PartTable.getSelectionModel().getSelectedItem();
        Part InHousePart = new InHousePart();
        Part OutsourcedPart = new OutsourcedPart();
    //this where we open the modify parts screen
    if (selectedPart != null && selectedPart instanceof InHousePart) {
        
        Stage stage;
        Parent root; 
        stage=(Stage) searchPartsButton.getScene().getWindow();
        //load up other FXML documnet
        root = FXMLLoader.load(getClass().getResource("ModifyInHousePart.fxml"));      
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }else if (selectedPart != null && selectedPart instanceof OutsourcedPart){
        Stage stage;
        Parent root;       
        stage=(Stage) searchPartsButton.getScene().getWindow();
        //load up other FXML documnet
        root = FXMLLoader.load(getClass().getResource("ModifyOutsourcedPart.fxml"));   
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }else{
        // Nothing selected.
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Selection");
        alert.setHeaderText("No Part Selected");
        alert.setContentText("Please select a part in the table.");

        alert.showAndWait();
    }
}

    @FXML
    void handleDeletePart(ActionEvent event) {
        Part selectedPart = PartTable.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
        //Confirm Delete
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete...");
        alert.setHeaderText("Deleting...");
        alert.setContentText("Would you like to  Delete " + selectedPart.getName()+" now ?");        
        alert.showAndWait()
            
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> Inventory.getPartData().remove(selectedPart));
        
        //update Parts table
        PartTable.setItems(Inventory.getPartData());
        
        }else{
        // Nothing selected.
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Selection");
        alert.setHeaderText("No Part Selected");
        alert.setContentText("Please select an Part from the table.");

        alert.showAndWait();
        }
    }

    @FXML
    private void handleNewProduct() throws IOException {
        if (PartTable.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText("You must add at least one part first.");
            alert.showAndWait();
        }else{
            Stage stage;
            Parent root;
            stage=(Stage) searchPartsButton.getScene().getWindow();
            //load up other FXML documnet
            root = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));    
            //create a new scene with root and set the stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();   
       
                
                }
    }
    @FXML
    private void handleModifyProduct() throws IOException {
    selectedProduct = ProductTable.getSelectionModel().getSelectedItem();
    //this where we open the modify products screen
    if (selectedProduct != null) {        
        Stage stage;
        Parent root;        
        stage=(Stage) searchPartsButton.getScene().getWindow();
        //load up other FXML documnet
        root = FXMLLoader.load(getClass().getResource("ModifyProduct.fxml"));        
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }else if (ProductTable.getItems().isEmpty()) { 
        // No products created yet
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Products");
        alert.setHeaderText("No Products available");
        alert.setContentText("There are no products in inventory.");

        alert.showAndWait();
        
    }else{
        //no product selected
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Product selected");
        alert.setHeaderText("No Product selected");
        alert.setContentText("Please select a product from the table.");
    }    
    }

    @FXML
    private void handleDeleteProduct(ActionEvent event) {
        Product selectedProduct = ProductTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
        //Confirm Delete
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText("Warning!");
        alert.setContentText("This Product has a part associated with it, are you sure you would you like to delete it?" + selectedProduct.getProductName()+" now ?");        
        alert.showAndWait()
            
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> Inventory.getProductData().remove(selectedProduct)); 
        //update Products table
        ProductTable.setItems(Inventory.getProductData());
        
        }else{
        // Nothing selected.
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Cannot delete");
        alert.setHeaderText("There are no products");
        alert.setContentText("There are no products in Inventory");

        alert.showAndWait();
        }    
    }

    @FXML
    private void exitApplication(ActionEvent event) {
        System.exit(0);
    }
    
    @FXML
    private void searchParts() {
        String searchText = textPartSearch.getText();
        FilteredList<Part> searchPartResults = searchParts(searchText);
        SortedList<Part> sortedData = new SortedList<>(searchPartResults);
        sortedData.comparatorProperty().bind(PartTable.comparatorProperty());
        PartTable.setItems(sortedData);
    } 
        public FilteredList<Part> searchParts(String s){
        return Inventory.getPartData().filtered(p -> p.getName().toLowerCase().contains(s.toLowerCase()));
        
    }  
    @FXML
    private void searchProducts() {
        String searchText = textProductSearch.getText();
        FilteredList<Product> searchProductResults = searchProducts(searchText);
        SortedList<Product> sortedData = new SortedList<>(searchProductResults);
        sortedData.comparatorProperty().bind(ProductTable.comparatorProperty());
        ProductTable.setItems(sortedData);
    } 
        public FilteredList<Product> searchProducts(String s){
        return Inventory.getProductData().filtered(p -> p.getProductName().toLowerCase().contains(s.toLowerCase()));       
    }  
}
