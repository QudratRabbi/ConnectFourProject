/**
 * Qudrat Rabbi
 * Professor John Baugh
 * CIS 296
 * JavaFX
 * Assignment Two
 *
 **/


package ConnectFour;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Runner extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        
        stage.setScene(new Scene(root));
        stage.setResizable(false);// resizing makes it weird 
        
        stage.show();
    }   
    
}