package ui.fxml;

import Functions.function1.blogNews;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class blogViewController {
	private blogNews entity;
	public blogViewController() {
		
	}
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
        textTitle.setText(entity.getTitle());
        textTime.setText(entity.getTime());
        textKeywords.setText(String.join(", ", entity.getKeywords()));
        textContent.setText(entity.getContent());
        textURL.setText(entity.getUrl());
    }
}