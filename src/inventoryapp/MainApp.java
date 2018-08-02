
package inventoryapp;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author William Weaver
 */
public class MainApp extends Application {

    public static Stage primaryStage;
    private AnchorPane rootLayout;
      
    public MainApp() {
    }

    @Override
    public void start(Stage primaryStage) {
        MainApp.primaryStage = primaryStage;
        MainApp.primaryStage.setTitle("Inventory Management System");

        initRootLayout();    
    }    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
             //Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/MainApp.fxml"));
            rootLayout = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();           
             
        } catch (IOException e) {
    }
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);    
    } 
}