package ui.fxml;

import java.io.IOException;
import java.util.List;

import Functions.function1.blogNews;
import Functions.function1.twitterNews;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Function2Controller implements HandleEvent{
	
	public Function2Controller() {
	
	}

    @FXML
    private MenuItem menuItemMonth;

    @FXML
    private ToggleGroup choiceSearch;

    @FXML
    private RadioButton radiobtnBlog;

    @FXML
    private MenuItem btnAbout;

    @FXML
    private MenuItem menuItemDay;

    @FXML
    private MenuItem menuItemWeek;

    @FXML
    private Button btnGetUpdate;

    @FXML
    private Button btnFunction2;

    @FXML
    private RadioButton radiobtnTwitter;

    @FXML
    private Button btnFunction3;

    @FXML
    private Button btnFunction1;
    
    @FXML
    private TableColumn<blogNews, String> colTime1;

    @FXML
    private TableColumn<twitterNews, String> colTime;

    @FXML
    private TableColumn<twitterNews, List<String>> colHashtags;
    
    @FXML
    private TableColumn<twitterNews, String> colAuthor;

    @FXML
    private TableColumn<blogNews, String> colAuthor1;

    @FXML
    private TableView<blogNews> tblViewBlogResult;
    
    @FXML
    private TableColumn<blogNews, String> colContent1;

    @FXML
    private TableView<twitterNews> tblViewTwitterResult;

    @FXML
    private TableColumn<blogNews, String> colUrl1;

    @FXML
    private TableColumn<twitterNews, String> colUrl;

    @FXML
    private TableColumn<twitterNews, String> colContent;
    
    @FXML
    private TableColumn<blogNews, List<String>> colKeywords;

    
    public void initialize() {
        // Initialization logic goes here
    }

	@Override
	public void btnAboutPressed(ActionEvent event) {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/fxml/about.fxml"));
    	createStage(loader);
	}

	@Override
	public void btnGetUpdatePressed(ActionEvent event) {
		// TODO Auto-generated method stub
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Notice");
        alert.setHeaderText(null);
        alert.setContentText("The news is updated successfully!");

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.showAndWait();
	}

	@Override
	public void runFunction1(ActionEvent event) {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/fxml/Function1.fxml"));
    	createStage(loader);
	}

	@Override
	public void runFunction2(ActionEvent event) {
		// TODO Auto-generated method stub
		
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