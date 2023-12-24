package ui.fxml;


import Functions.function1.twitterNews;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class twitterViewController {

	private twitterNews entity ;
	
	public twitterViewController() {
		
	}
	public twitterViewController(twitterNews entity) {
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
    
    public void initialize() {
    	setTwitterNews(entity);
    }
    
    public void setTwitterNews(twitterNews entity) {
    	if (entity != null) {
            textTitle.setText(entity.getAuthor());
            textTime.setText(entity.getTime());
            textKeywords.setText(String.join(", ", entity.getHashtags()));
            textContent.setText(entity.getContent());
        } else {
            // Handle the case when entity is null
        }
    }
}
