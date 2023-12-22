package ui.fxml;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MainScreenController implements HandleEvent{
	
	public MainScreenController() {
		
	}
	
	  @FXML
	    private Button btnGetUpdate;

	    @FXML
	    private Button btnFunction2;

	    @FXML
	    private Button btnFunction3;

	    @FXML
	    private Label lblMain;
	    
	    @FXML
	    private MenuItem menuAbout;

	    @FXML
	    private Button btnFunction1;

	    @FXML
	    public void btnGetUpdatePressed(ActionEvent event) {
	    	Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Notice");
	        alert.setHeaderText(null);
	        alert.setContentText("The news is updated successfully!");

	        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
	        stage.showAndWait();
	    }

	    @FXML
	    public void runFunction1(ActionEvent event) {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/fxml/Function1.fxml"));
	    	createStage(loader);
	    }

	    @FXML
	    public void runFunction2(ActionEvent event) {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/fxml/Function2.fxml"));
	    	//Function2Controller function2Controller = loader.getController();
	    	createStage(loader);
	    }
	    
	    @FXML
	    public void btnAboutPressed(ActionEvent event) {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/fxml/about.fxml"));
	    	createStage(loader);
	    }
	    
	    private void initialize() {
	        // Initialization logic goes here
	    }
	    
	    private void createStage(FXMLLoader loader) {
	    	Scene scene = null;
    		try {
    			scene = new Scene(loader.load()); 
    		} catch (IOException e) {
    			e.printStackTrace();
    	}
    	Stage stage = new Stage();
    	stage.setScene(scene);
    	stage.setTitle("Project OOP");
    	stage.show();
    	}
	    
}

