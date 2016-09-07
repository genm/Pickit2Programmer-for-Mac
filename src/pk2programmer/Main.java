package pk2programmer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.omg.SendingContext.RunTime;
import org.apache.commons.exec.*;
/**
 *
 * @author genm1023
 */
public class Main extends Application {
            
    
    public static Stage currentPage;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        currentPage = primaryStage;
        currentPage.setTitle("Pickit2 Programmer");
        Scene scene = new Scene(new Pane());        
        MainViewController.getInstance().show();
        currentPage.show();

        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
