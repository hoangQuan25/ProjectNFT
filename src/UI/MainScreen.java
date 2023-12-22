package UI;

//import java.io.IOException;

//import javax.swing.JFrame;

import Functions.Function1;
import javafx.application.Application;
//import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainScreen extends Application{
	private Function1 fn1;
	
	@Override
	public void start(Stage stage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("/UI/MainScreen.fxml"));
		
		Scene scene = new Scene(root);
		stage.setTitle("Java Application");
		stage.setScene(scene);
		stage.show();
		
	}
	
	public static void main(String[] args)  {
		launch(args);
	}
}
