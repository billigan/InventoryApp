
package inventoryapp.view;

import inventoryapp.model.Inventory;
import inventoryapp.model.Part;
import inventoryapp.model.Product;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author William Weaver
 */
public class ModifyProductController {

    @FXML
    private AnchorPane ModifyProduct;
    @FXML
    private TableView<Part> PartTable3;       
    @FXML
    private TableColumn<Part, Integer> PartIdColumn2;
    @FXML
    private TableColumn<Part, String> PartNameColumn2;
    @FXML
    private TableColumn<Part, Integer> PartInventoryColumn2;
    @FXML
    private TableColumn<Part, Double> PartPriceColumn2;
    
    @FXML
    private TableView<Part> AddedPartTable2;    
    @FXML
    private TableColumn<Part, Integer> APartIdColumn;
    @FXML
    private TableColumn<Part, String> APartNameColumn;
    @FXML
    private TableColumn<Part, Integer> APartInventoryColumn;
    @FXML
    private TableColumn<Part, Double> APartPriceColumn;
    
    @FXML
    private TextField productInStock;
    @FXML
    private TextField productMin;
    @FXML
    private TextField textPartSearch2;
    @FXML
    private Button searchButton;
    @FXML
    private TextField productMax;
    @FXML
    private TextField productPrice;
    @FXML
    private TextField productName;
    @FXML
    private TextField productID;
    
    boolean okClicked = false;
    
    @FXML
    TableView<Product> ProductTable;
    
    private Product product;
    Product testProduct2;
    /**
     * Initializes the controller class.
     */
    
       public void initialize() {       
        if (MainAppController.selectedProduct != null) {
        productName.setText(MainAppController.selectedProduct.getProductName());
        productID.setText (MainAppController.selectedProduct.getProductID().toString());
        productInStock.setText (MainAppController.selectedProduct.getProductInStock().toString());
        productPrice.setText (MainAppController.selectedProduct.getProductPrice().toString());
        productMax.setText (Integer.toString(MainAppController.selectedProduct.getProductMax()));
        productMin.setText (Integer.toString(MainAppController.selectedProduct.getProductMin()));
        }else{
        productID.setText("0"); //requirement for default value of 0.
        }
        PartIdColumn2.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        PartNameColumn2.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        PartInventoryColumn2.setCellValueFactory(cellData -> cellData.getValue().inStockProperty().asObject());
        
        PartPriceColumn2.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        PartTable3.setItems(Inventory.PartData);
        
        APartIdColumn.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        APartNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        APartInventoryColumn.setCellValueFactory(cellData -> cellData.getValue().inStockProperty().asObject());
        APartPriceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());  
        AddedPartTable2.setItems(MainAppController.selectedProduct.getmembers());
        
        testProduct2 = new Product(); //temp product group to hold the data
    } 
    
        public boolean isOkClicked() {
        return okClicked;
        }
        
        public void setProduct(Product product) {
        this.product = product;

        productID.setText(Integer.toString(product.getProductID()));
        productName.setText(product.getProductName());
        productInStock.setText(Integer.toString(product.getProductInStock()));
        productPrice.setText(Double.toString(product.getProductPrice()));
        productMax.setText(Integer.toString(product.getProductMax()));
        productMin.setText(Integer.toString(product.getProductMin()));
    }
        
    @FXML
    void handleOk() throws IOException {
        if (isInputValid()) {
            
        product = new Product();
        product.setProductName(productName.getText());
        product.setProductID(Integer.parseInt(productID.getText()));
        product.setProductInStock(Integer.parseInt(productInStock.getText()));
        product.setProductPrice(Double.parseDouble(productPrice.getText()));
        product.setProductMax(Integer.parseInt(productMax.getText()));
        product.setProductMin(Integer.parseInt(productMin.getText()));
        Inventory.ProductData.remove(MainAppController.selectedProduct);
        
        double priceOfParts = .0;
        for (Part p: product.getmembers()) {
            priceOfParts += p.getPrice();
        }

        okClicked = true;
        
       if (Objects.equals(productID.getText(), product.getProductID())) { 
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("ID Error");
           alert.setHeaderText("Product ID must be unique.");
           alert.setContentText("Please check your Product ID value.");
           alert.showAndWait();
        }else if (product.getProductInStock() > product.getProductMax() || product.getProductInStock() < product.getProductMin()) { 
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Inventory Level Error");
            alert.setHeaderText("Inventory cannot be more than Max or Less then MIn.");
            alert.setContentText("Please check your Inventory value.");
            alert.showAndWait();
        }else if (product.getProductMax() < product.getProductMin()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Order Level Error");
            alert.setHeaderText("Min cannot be larger than Max.");
            alert.setContentText("Please check your max and min values.");
            alert.showAndWait();
        }else if (product.getProductName().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Field Input");
            alert.setHeaderText("Name cannot be blank");
            alert.setContentText("Please fill out the Name field.");
            alert.showAndWait();
        }else if (product.getProductInStock() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Field Input");
            alert.setHeaderText("Name cannot be blank");
            alert.setContentText("Please fill out the Name field.");
            alert.showAndWait();
        }else if (product.getProductPrice() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Field Input");
            alert.setHeaderText("Name cannot be blank");
            alert.setContentText("Please fill out the Name field.");
            alert.showAndWait();
            
        }else if (product.getProductPrice() < priceOfParts) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Price Error");
            alert.setHeaderText("Product price must be higher than total price of parts");
            alert.setContentText("Please check your price.");
            alert.showAndWait();      
       
        }else{
            Inventory.getProductData().add(product);
            
            Stage stage;
            Parent root;
            stage=(Stage) productName.getScene().getWindow();
            //load up other FXML documnet
            root = FXMLLoader.load(getClass().getResource("MainApp.fxml"));
            //create a new scene with root and set the stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }  
    }
    }

    @FXML
    public void handleAddPart() {
        
        Part selectedPart = PartTable3.getSelectionModel().getSelectedItem();        
        if (selectedPart != null) {
            
            testProduct2.getmembers().add(selectedPart);
            AddedPartTable2.setItems(testProduct2.getmembers());
            
        }else{ //part not selected
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Part is selected");
            alert.setContentText("Please select a part from the top table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleRemovePart(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cannot remove");
            alert.setHeaderText("Part cannot be removed");
            alert.setContentText("Product must have at least one part.");

            alert.showAndWait();
    }
    
     @FXML
    private void handleCancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel...");
        alert.setHeaderText("Cancelling...");
        alert.setContentText("Would you like to cancel?");        
        //alert.showAndWait();
        Stage stage;
        Parent root;
    Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
                    
        //load up other FXML documnet
        stage=(Stage) searchButton.getScene().getWindow();
        //load up other FXML documnet
        root = FXMLLoader.load(getClass().getResource("MainApp.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        }else{   
    }        
}
    
    private boolean isInputValid() {
        String errorMessage = "";

        if (productID.getText() == null || productID.getText().length() == 0) {
            errorMessage += "No valid id!\n"; 
        }
        if (productName.getText() == null || productName.getText().length() == 0) {
            errorMessage += "No valid name!\n"; 
        }
        if (productInStock.getText() == null || productInStock.getText().length() == 0) {
            errorMessage += "No valid inventory level!\n"; 
        }

        if (productPrice.getText() == null || productPrice.getText().length() == 0) {
            errorMessage += "No valid price!\n"; 
        }else{
            // try to parse the price into an int.
            try {
                Double.parseDouble(productPrice.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid price (must be a double)!\n"; 
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        }else{
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }        
    }   
        @FXML
    private void handleSearchParts(ActionEvent event) {
                String searchText = textPartSearch2.getText();
        FilteredList<Part> searchPartResults = searchParts(searchText);
        SortedList<Part> sortedData = new SortedList<>(searchPartResults);
        sortedData.comparatorProperty().bind(PartTable3.comparatorProperty());
        PartTable3.setItems(sortedData);
    } 
        public FilteredList<Part> searchParts(String s){
        return Inventory.getPartData().filtered(p -> p.getName().toLowerCase().contains(s.toLowerCase()));
        
    }     
}
 