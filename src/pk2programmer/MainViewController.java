package pk2programmer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * FXML Controller class
 *
 * @author genm1023
 */
public class MainViewController implements Initializable {

    @FXML
    private TextField screen;
    @FXML
    private Label hexNameLabel;
    @FXML
    private Label picNameLabel;
    @FXML
    private CheckBox outputCheckBox;
    @FXML
    private Spinner voltageSpiner;
    
    private String hexPath; //あとでxmlとかにまとめる
    
    private PK2Command pk2cmd = new PK2Command();
    
    private SpinnerValueFactory svf = new SpinnerValueFactory.DoubleSpinnerValueFactory(2.5, 5.0, 5.0, 0.1);
    
    private static final MainViewController instance;
    private static final Scene SCENE;
    static{
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader(MainViewController.class.getResource("MainView.fxml"));
        try {
            fxmlLoader.load();            
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Parent parent = fxmlLoader.getRoot();
        Scene s = new Scene(parent);
        SCENE = s;
        instance = fxmlLoader.getController();
        
        
    } 

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO Spinerの設定
        svf.setValue(5.0);
        voltageSpiner.setValueFactory(svf);
    }    
    public void show(){
        Main.currentPage.setScene(SCENE);
    }
    public static MainViewController getInstance(){
        return instance;
    }

    @FXML
    private void onClickedMenuItem(ActionEvent event){
        MenuItem mi = (MenuItem)event.getSource();
        switch(mi.getId()){
            case "hexLoad":
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().addAll(
                        new ExtensionFilter("HEX Files", "*.hex"),
                        new ExtensionFilter("All Files", "*.*"));
                File selectedFile = fileChooser.showOpenDialog(Main.currentPage);
                if (selectedFile != null) {
                   hexNameLabel.setText(selectedFile.getName());
                   hexPath = selectedFile.getPath();
                    //screen.setText(selectedFile.getPath());
                   
                }                
                break;
                
            
        }
            
        
    }
    @FXML
    private void onClickedCheckBox(ActionEvent event){
        CheckBox chbx = (CheckBox)event.getSource();
        //文字列じゃなくてリソースで指定できればなお良い
        switch(chbx.getId()){
            case "outputCheckBox":
                if(outputCheckBox.isSelected()){
                    System.out.println("Voltage ON");
                    System.out.println("Vol :" + voltageSpiner.getValue().toString());
                    voltageSpiner.setDisable(true);
                    pk2cmd.powerOn(voltageSpiner.getValue().toString());
                }else{
                    System.out.println("Voltage OFF");
                    voltageSpiner.setDisable(false);
                    pk2cmd.powerOFF();
                }
                break;
                
            
        }
        
    }
    
    @FXML
    private void onClickedButton(ActionEvent event){
        //TODO ボタンごとに作ったほうが良いかも
        Button btn = (Button)event.getSource();
        switch(btn.getId()){
            case "ccButton":
                pk2cmd.autoDetect();
                break;
            case "writeButton":
                pk2cmd.writeHex(hexPath);
                //TODO pk2cmd ---
                break;
                
            case "eraseButton":
                pk2cmd.EraceHex();
                break;
                
            case "verifyButton":
                break;
                
            case "readButton":
                //pk2cmd.readHex();
                pk2cmd.readEEPROM();;
                break;
                
            
        }
        System.out.println("Pushed:" + btn.getId());
        
    }
    
}
