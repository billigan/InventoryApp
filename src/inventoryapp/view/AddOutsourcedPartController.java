
package inventoryapp.view;

import inventoryapp.model.Inventory;
import inventoryapp.model.OutsourcedPart;
import inventoryapp.model.Part;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author William Weaver
 */
public class AddOutsourcedPartController {

    @FXML
    private AnchorPane AddOutsourcedPart;
    @FXML
    private RadioButton radio1;
    @FXML
    private ToggleGroup radioGroup1;
    @FXML
    private RadioButton radio2;
    @FXML
    private Label lbl2;
    @FXML
    private TextField partID2;
    @FXML
    private TextField name2;
    @FXML
    private TextField inStock2;
    @FXML
    private TextField price2;
    @FXML
    private TextField max2;    
    @FXML
    private TextField min2;
    @FXML
    private TextField companyName;

    boolean okClicked = false;

    @FXML
    TableView PartTable;
    private Part part;
   
    public boolean isOkClicked() {
        return okClicked;
    }   

     @FXML
    private void handleOk() throws IOException {

        if (isInputValid()) {
        
        part = new OutsourcedPart();

        part.setName(name2.getText());
        part.setInStock(Integer.parseInt(inStock2.getText()));
        part.setPrice(Double.parseDouble(price2.getText()));
        part.setMax(Integer.parseInt(max2.getText()));
        part.setMin(Integer.parseInt(min2.getText()));
        ((OutsourcedPart)part).setCompanyName(companyName.getText());
        
        okClicked = true;
        if (part.getInStock() > part.getMax()|| part.getInStock() < part.getMin()) { 
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Inventory Level Error");
            alert.setHeaderText("Inventory cannot be more than Max or Less then MIn");
            alert.setContentText("Please check your Inventory value.");
            alert.showAndWait();
        }else if (part.getMax() < part.getMin()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Order Level Error");
            alert.setHeaderText("Min cannot be larger than Max.");
            alert.setContentText("Please check your max and min values.");
            alert.showAndWait();
        
        }else{   
        Inventory.getPartData().add(part);
        
        Stage stage;
        Parent root;
        stage=(Stage) name2.getScene().getWindow();
        //load up other FXML documnet
        root = FXMLLoader.load(getClass().getResource("MainApp.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
    }
    }
    
     private boolean isInputValid() {
        String errorMessage = "";

        if (partID2.getText() == null || partID2.getText().length() == 0) {
            errorMessage += "No valid id!\n"; 
        }
        if (name2.getText() == null || name2.getText().length() == 0) {
            errorMessage += "No valid name!\n"; 
        }
        if (inStock2.getText() == null || inStock2.getText().length() == 0) {
            errorMessage += "No valid inventory level!\n"; 
        }

        if (price2.getText() == null || price2.getText().length() == 0) {
            errorMessage += "No valid price!\n"; 
        }else{
            // try to parse the price code into a double.
            try {
                Double.parseDouble(price2.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid price (must be a double)!\n"; 
        }
            
        if (max2.getText() == null || max2.getText().length() == 0) {
            errorMessage += "No valid max level!\n"; 
        }
        
        if (min2.getText() == null || min2.getText().length() == 0) {
            errorMessage += "No valid min level!\n"; 
        }
        
        if (companyName.getText() == null || companyName.getText().length() == 0) {
            errorMessage += "No valid Company name!\n"; 
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
        stage=(Stage) lbl2.getScene().getWindow();
        //load up other FXML documnet
        root = FXMLLoader.load(getClass().getResource("MainApp.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        }else{   
        }        
    }

    @FXML
    void handleSwitchScreen(ActionEvent event) throws IOException {
        Stage stage; 
        Parent root;
       //get reference to the button's stage         
       stage=(Stage) lbl2.getScene().getWindow();
       root = FXMLLoader.load(getClass().getResource("AddInHousePart.fxml"));
       stage.setScene(new Scene(root));
       stage.setTitle("My modal window"); 
    } 
}