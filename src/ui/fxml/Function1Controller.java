package ui.fxml;

import java.io.IOException;
import java.util.List;

import Functions.function1.JsonProcessor;
import Functions.function1.blogNews;
import Functions.function1.twitterNews;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Function1Controller implements HandleEvent{
	private Functions.function1.detailTwitter detailTwitter;
	private Functions.function1.detailBlog detailBlog;
	//private twitterViewController twitterView;
	//private blogViewController blogView;
	
	public Function1Controller() {
		super();
		this.detailTwitter = new Functions.function1.detailTwitter();
		this.detailBlog = new Functions.function1.detailBlog();
		//this.twitterView = new twitterViewController();
		//this.blogView = new blogViewController();
	}

	@FXML
    private TableColumn<blogNews, String> colTime1;

    @FXML
    private TableColumn<twitterNews, String> colTime;

    @FXML
    private TableColumn<twitterNews, List<String>> colHashtags;

    @FXML
    private TextArea tfSearchHashtag;

    @FXML
    private ToggleGroup choiceSearch;

    @FXML
    private TableColumn<twitterNews, String> colAuthor;

    @FXML
    private TableColumn<blogNews, String> colAuthor1;

    @FXML
    private TableView<blogNews> tblViewBlogResult;

    @FXML
    private RadioButton radiobtnBlog;

    @FXML
    private TableColumn<blogNews, String> colContent1;

    @FXML
    private MenuItem btnAbout;

    @FXML
    private TableView<twitterNews> tblViewTwitterResult;

    @FXML
    private TableColumn<blogNews, String> colUrl1;

    @FXML
    private TableColumn<twitterNews, String> colUrl;

    @FXML
    private TableColumn<twitterNews, String> colContent;

    @FXML
    private Button btnGetUpdate;

    @FXML
    private Button btnFunction2;

    @FXML
    private RadioButton radiobtnTwitter;

    @FXML
    private TableColumn<blogNews, List<String>> colKeywords;

    @FXML
    private Button btnFunction3;

    @FXML
    private Button btnFunction1;


    public void initialize() {
    	 choiceSearch.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
             if (newValue != null) {
                 RadioButton selectedRadioButton = (RadioButton) newValue;

                 if (selectedRadioButton == radiobtnBlog) {
                     // Update the TableView for BlogNews
                	 tblViewTwitterResult.setVisible(false);
                	 tblViewBlogResult.setVisible(true);
                	 colAuthor1.setCellValueFactory(
                     		new PropertyValueFactory<blogNews,String>("title"));
                     colTime1.setCellValueFactory(
                     		new PropertyValueFactory<blogNews,String>("time"));
                     colKeywords.setCellValueFactory(
                     		new PropertyValueFactory<>("keywords"));
                     colContent1.setCellValueFactory(
                     		new PropertyValueFactory<blogNews,String>("content"));
                     colUrl1.setCellValueFactory(
                     		new PropertyValueFactory<blogNews,String>("url"));
                     tblViewBlogResult.setItems(this.detailBlog.getListBlog());
                     
                     tblViewBlogResult.getSelectionModel().selectedItemProperty().addListener(
                 			new ChangeListener<blogNews>() {
                 				@Override
                 				public void changed(ObservableValue<? extends blogNews> observable, blogNews oldValue,
                 						blogNews newValue) {
                 					if(newValue != null) {
                 					}
                 				}
                 			});
                     
                     FilteredList<blogNews> filteredList = new FilteredList<>(detailBlog.getListBlog(), p -> true);
                     tblViewBlogResult.setItems(filteredList);
                 	tfSearchHashtag.textProperty().addListener(new ChangeListener<String>() {
                 		@Override
                 		public void changed(ObservableValue<? extends String> 
                 		observable, String oldValue, String newValue) {
                 			filteredList.setPredicate(news -> {
                                 if (newValue == null || newValue.trim().isEmpty()) {
                                     return true;
                                 }
                                     String lowerCaseFilter = newValue.toLowerCase();
                                     return news.getKeywords().stream().anyMatch(tag -> tag.contains(lowerCaseFilter));
                                     
                 			});
                 		}
                 	});
                 	
                 	tblViewBlogResult.setRowFactory(tableView -> {
                        TableRow<blogNews> row = new TableRow<>();
                        row.setOnMouseClicked(event -> {
                            if (event.getClickCount() == 2 && (!row.isEmpty())) {
                                blogNews entity = row.getItem();
                                try {
									createBlogView(entity);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
                            }
                        });
                        return row;
                    });
                 }
                 else if (selectedRadioButton == radiobtnTwitter) {
                     // Update the TableView for TwitterNews
                	 tblViewBlogResult.setVisible(false);
                	 tblViewTwitterResult.setVisible(true);
                	 colAuthor.setCellValueFactory(
                     		new PropertyValueFactory<twitterNews,String>("author"));
                     colTime.setCellValueFactory(
                     		new PropertyValueFactory<twitterNews,String>("time"));
                     colHashtags.setCellValueFactory(
                     		new PropertyValueFactory<>("hashtags"));
                     colContent.setCellValueFactory(
                     		new PropertyValueFactory<twitterNews,String>("content"));
                     tblViewTwitterResult.setItems(this.detailTwitter.getListTwitter());
                     
                     tblViewTwitterResult.getSelectionModel().selectedItemProperty().addListener(
                 			new ChangeListener<twitterNews>() {
                 				@Override
                 				public void changed(ObservableValue<? extends twitterNews> observable, twitterNews oldValue,
                 						twitterNews newValue) {
                 					if(newValue != null) {
                 					}
                 				}
                 			});
                     
                     FilteredList<twitterNews> filteredList = new FilteredList<>(detailTwitter.getListTwitter(), p -> true);
                     tblViewTwitterResult.setItems(filteredList);
                 	tfSearchHashtag.textProperty().addListener(new ChangeListener<String>() {
                 		@Override
                 		public void changed(ObservableValue<? extends String> 
                 		observable, String oldValue, String newValue) {
                 			filteredList.setPredicate(news -> {
                                 if (newValue == null || newValue.trim().isEmpty()) {
                                     return true;
                                 }
                                     String lowerCaseFilter = newValue.toLowerCase();
                                     return news.getHashtags().stream().anyMatch(tag -> tag.contains(lowerCaseFilter));
                                     });
                 			}
                 		});
                 }
             }
         });
    	 tblViewTwitterResult.setRowFactory(tableView -> {
             TableRow<twitterNews> row = new TableRow<>();
             row.setOnMouseClicked(event -> {
                 if (event.getClickCount() == 2 && (!row.isEmpty())) {
                     twitterNews obj = row.getItem();
                     //System.out.println(obj.getAuthor() +"\n"+ obj.getTime()+"\n" +obj.getContent()+"\n" + obj.getHashtags());
                     createTwitterView(obj);
                 }
             });
             return row;
         });
    		
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
		String twitterFile = "src/data/outputData/TwitterData/twitter.json";
    	String blogFile = "src/data/outputData/blogData/nftically.json";
    	String blog2File = "src/data/outputData/blogData/nonFungible.json";
    	String blog3File = "src/data/outputData/blogData/nftnewstoday.json";
    	String blog4File = "src/data/outputData/blogData/airnft.json";
    	
    	String twitterJsonString = null;
    	String blogJsonString = null;
    	String blog2JsonString = null;
    	String blog3JsonString = null;
    	String blog4JsonString = null;

		try {
			twitterJsonString = JsonProcessor.readJsonFile(twitterFile);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		try {
			blogJsonString = JsonProcessor.readJsonFile(blogFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			blog2JsonString = JsonProcessor.readJsonFile(blog2File);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			blog3JsonString = JsonProcessor.readJsonFile(blog3File);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			blog4JsonString = JsonProcessor.readJsonFile(blog4File);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		if (this.detailTwitter != null) {
			this.detailTwitter.loadData(twitterJsonString);
		}
		else {
            System.err.println("Error: detailTwitter is null");
        }
		if (this.detailBlog != null) {
			this.detailBlog.loadData(blogJsonString);
			this.detailBlog.loadData(blog2JsonString);
			this.detailBlog.loadData(blog3JsonString);
			this.detailBlog.loadData(blog4JsonString);
			
		}
		else {
            System.err.println("Error: detailBlog is null");
        }
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
	}

	@Override
	public void runFunction2(ActionEvent event) {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/fxml/Function2.fxml"));
    	createStage(loader);
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
	
	private void createBlogView(blogNews entity) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/fxml/blogView.fxml"));
		blogViewController controller = new blogViewController(entity);
		controller = loader.getController();
		createStage(loader);
	}
	
	private void createTwitterView(twitterNews entity) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/fxml/twitterView.fxml"));
		twitterViewController controller = new twitterViewController(entity);
		controller = loader.getController();
		controller.setTwitterNews(entity);
		createStage(loader);
		
		
	}
}