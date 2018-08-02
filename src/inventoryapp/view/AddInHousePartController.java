
package inventoryapp.view;

import inventoryapp.model.InHousePart;
import inventoryapp.model.Inventory;
import inventoryapp.model.Part;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class AddInHousePartController {
    
    @FXML
    private AnchorPane AddInHousePart;
    @FXML
    private RadioButton radio1;
    @FXML
    private ToggleGroup radioGroup1;
    @FXML
    private RadioButton radio2;
    @FXML
    private Label lbl1;
    @FXML
    private TextField partID;
    @FXML
    private TextField min;
    @FXML
    private TextField name;
    @FXML
    private TextField inStock;
    @FXML
    private TextField price;
    @FXML
    private TextField max;
    @FXML
    private TextField machID;
    @FXML
    private Button save;
    @FXML
    private Button cancel;
    
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
        part = new InHousePart();

        part.setName(name.getText());
        part.setInStock(Integer.parseInt(inStock.getText()));
        part.setPrice(Double.parseDouble(price.getText()));
        part.setMax(Integer.parseInt(max.getText()));
        part.setMin(Integer.parseInt(min.getText()));
        ((InHousePart)part).setMachID(Integer.parseInt(machID.getText()));
        
        okClicked = true;
        if (part.getInStock() > part.getMax()|| part.getInStock() < part.getMin()) { 
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Inventory Level Error");
            alert.setHeaderText("Inventory cannot be more than Max or Less then MIn.");
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
        stage=(Stage) name.getScene().getWindow();
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
        stage=(Stage) lbl1.getScene().getWindow();
        //load up other FXML documnet
        root = FXMLLoader.load(getClass().getResource("MainApp.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        } else {   
        }        
    }

    @FXML
    void handleSwitchScreen(ActionEvent event) throws IOException {
        Stage stage; 
        Parent root;
       //get reference to the button's stage         
       stage=(Stage) lbl1.getScene().getWindow();
       //stage = new Stage();
       root = FXMLLoader.load(getClass().getResource("AddOutsourcedPart.fxml"));
       stage.setScene(new Scene(root));
       stage.setTitle("My modal window"); 
    }
    
     private boolean isInputValid() {
        String errorMessage = "";

        if (partID.getText() == null || partID.getText().length() == 0) {
            errorMessage += "No valid id!\n"; 
        }
        if (name.getText() == null || name.getText().length() == 0) {
            errorMessage += "No valid name!\n"; 
        }
        if (inStock.getText() == null || inStock.getText().length() == 0) {
            errorMessage += "No valid inventory level!\n"; 
        }

        if (price.getText() == null || price.getText().length() == 0) {
            errorMessage += "No valid price!\n"; 
        }else{
            // try to parse the price code into a double.
            try {
                Double.parseDouble(price.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid price (must be a double)!\n"; 
        }
            
        if (max.getText() == null || max.getText().length() == 0) {
            errorMessage += "No valid max level!\n"; 
        }
        
        if (min.getText() == null || min.getText().length() == 0) {
            errorMessage += "No valid min level!\n"; 
        }
        
        if (machID.getText() == null || machID.getText().length() == 0) {
            errorMessage += "No valid machine ID!\n"; 
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
}