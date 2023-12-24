package ui.fxml;

import Functions.function1.blogNews;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class blogViewController {
	private blogNews entity;
	
	public blogViewController(blogNews entity) {
		this.entity = entity;
	}
    @FXML
    private Text textTitle;

    @FXML
    private VBox claimContainer;

    @FXML
    private Text textKeywords;

    @FXML
    private Text textTime;

    @FXML
    private ScrollPane scroll;

    @FXML
    private Text textContent;

    @FXML
    private Text textURL;

    public void initialize() {
    	setBlogNews(entity);
    }
    
    public void setBlogNews(blogNews entity) {
        if (entity != null) {
            textTitle.setText(entity.getTitle());
            textTime.setText(entity.getTime());
            textKeywords.setText(String.join(", ", entity.getKeywords()));
            textContent.setText(entity.getContent());
            textURL.setText(entity.getUrl());
        } else {
            // Handle the case where entity is null (optional, depending on your requirements)
            // For example, you might want to clear the text fields or set default values.
            textTitle.setText("");
            textTime.setText("");
            textKeywords.setText("");
            textContent.setText("");
            textURL.setText("");
        }
    }

}