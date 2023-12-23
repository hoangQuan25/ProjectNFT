package ui.screen;

import Functions.Function1;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Function1Screen extends Application{
		private Function1 fn1;
	@Override
	public void start(Stage stage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("/ui/fxml/Function1.fxml"));
		
		Scene scene = new Scene(root);
		stage.setTitle("Java Application");
		stage.setScene(scene);
		stage.show();
		
	}
	
	public static void main(String[] args)  {
		launch(args);
	}
}
