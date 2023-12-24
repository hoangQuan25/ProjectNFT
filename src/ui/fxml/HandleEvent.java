package ui.fxml;

import javafx.event.ActionEvent;

public interface HandleEvent {
	
	/*public static final Functions.function1.detailTwitter detailTwitter = 
			new Functions.function1.detailTwitter();
	public static final Functions.function1.detailBlog detailBlog = 
			new Functions.function1.detailBlog();*/
	
	
	public void btnAboutPressed(ActionEvent event);
	
	public void btnGetUpdatePressed(ActionEvent event);
	
	public void runFunction1(ActionEvent event);
	
	public void runFunction2(ActionEvent event);
	
}
