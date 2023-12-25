package ui.fxml;

import java.awt.Dimension;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Functions.function1.JsonProcessor;
import Functions.function1.blogNews;
import Functions.function1.twitterNews;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Function2Controller implements HandleEvent {
	private Functions.function1.detailTwitter detailTwitter;
	private Functions.function1.detailBlog detailBlog;

	public Function2Controller() {
		this.detailTwitter = new Functions.function1.detailTwitter();
		this.detailBlog = new Functions.function1.detailBlog();
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
    private TableColumn<blogNews, String> colTitle;

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
    	choiceSearch.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                RadioButton selectedRadioButton = (RadioButton) newValue;

                if (selectedRadioButton == radiobtnBlog) {
                    // Update the TableView for BlogNews
               	 tblViewTwitterResult.setVisible(false);
               	 tblViewBlogResult.setVisible(true);
               	 colTitle.setCellValueFactory(
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
                }
            }
        });
   	 tblViewTwitterResult.setRowFactory(tableView -> {
            TableRow<twitterNews> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    twitterNews obj = row.getItem();
                    //System.out.println(obj.getAuthor() +"\n"+ obj.getTime()+"\n" +obj.getContent()+"\n" + obj.getHashtags());
                    try {
						createTwitterView(obj);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
	
	@FXML
    void btnDayPressed(ActionEvent event) {
		ObservableList<blogNews> listBlog = FXCollections.observableArrayList();
		listBlog = detailBlog.getPostsForDay(detailBlog.getListBlog(), "2023-12-09");
		ObservableList<twitterNews> listTwitter =  FXCollections.observableArrayList();
		listTwitter = detailTwitter.getPostsForDay(detailTwitter.getListTwitter(),"2023-12-20");
		
		FilteredList<blogNews> filteredListBlog = new FilteredList<>(listBlog, p -> true);
        tblViewBlogResult.setItems(filteredListBlog);
        FilteredList<twitterNews> filteredListTwitter = new FilteredList<>(listTwitter, p -> true);
        tblViewTwitterResult.setItems(filteredListTwitter);
        	
    }

    @FXML
    void btnWeekPressed(ActionEvent event) {
    	ObservableList<blogNews> listBlog = FXCollections.observableArrayList();
		listBlog = detailBlog.getPostsForWeek(detailBlog.getListBlog(), "2023-10-01");
		ObservableList<twitterNews> listTwitter =  FXCollections.observableArrayList();
		listTwitter = detailTwitter.getPostsForWeek(detailTwitter.getListTwitter(),"2023-12-15");
		FilteredList<blogNews> filteredListBlog = new FilteredList<>(listBlog, p -> true);
        tblViewBlogResult.setItems(filteredListBlog);
        FilteredList<twitterNews> filteredListTwitter = new FilteredList<>(listTwitter, p -> true);
        tblViewTwitterResult.setItems(filteredListTwitter);
    }

    @FXML
    void btnMonthPressed(ActionEvent event) {
    	ObservableList<blogNews> listBlog = FXCollections.observableArrayList();
		listBlog = detailBlog.getPostsForWeek(detailBlog.getListBlog(), "2023-05-05");
		ObservableList<twitterNews> listTwitter =  FXCollections.observableArrayList();
		listTwitter = detailTwitter.getPostsForWeek(detailTwitter.getListTwitter(),"2023-12-01");
		FilteredList<blogNews> filteredListBlog = new FilteredList<>(listBlog, p -> true);
        tblViewBlogResult.setItems(filteredListBlog);
        FilteredList<twitterNews> filteredListTwitter = new FilteredList<>(listTwitter, p -> true);
        tblViewTwitterResult.setItems(filteredListTwitter);
    }
	
	private void createBlogView(blogNews entity) throws IOException {
		JPanel blogPanel = new JPanel();
        blogPanel.setLayout(new BoxLayout(blogPanel, BoxLayout.Y_AXIS));
        blogPanel.setPreferredSize(new Dimension(600, 600)); // Set a fixed size for the JPanel
        // Add components to the blog view panel
        JLabel titleLabel = new JLabel("Title: " + entity.getTitle());
        JLabel timeLabel = new JLabel("Time: " + entity.getTime());
        JLabel keywordsLabel = new JLabel("Keywords: " + String.join(", ", entity.getKeywords()));
        
        // Create a JTextArea for the content
        JTextArea contentArea = new JTextArea(entity.getContent());
        contentArea.setEditable(false);
        contentArea.setLineWrap(true); // Enable text wrapping
        contentArea.setWrapStyleWord(true); // Wrap at word boundaries

        // Wrap the content area in a JScrollPane for scrolling
        JScrollPane contentScrollPane = new JScrollPane(contentArea);

        JLabel urlLabel = new JLabel("URL: " + entity.getUrl());

        blogPanel.add(titleLabel);
        blogPanel.add(timeLabel);
        blogPanel.add(keywordsLabel);
        blogPanel.add(contentScrollPane); // Add the JScrollPane instead of the JTextArea directly
        blogPanel.add(urlLabel);

        // Show the JOptionPane with the custom blog view panel
        JOptionPane.showMessageDialog(null, blogPanel, "Blog View", JOptionPane.PLAIN_MESSAGE);
	}
	
	private void createTwitterView(twitterNews entity) throws IOException{
		 JPanel twitterPanel = new JPanel();
	        twitterPanel.setLayout(new BoxLayout(twitterPanel, BoxLayout.Y_AXIS));
	        twitterPanel.setPreferredSize(new Dimension(500, 300));

	        JLabel authorLabel = new JLabel("Author: " + entity.getAuthor());
	        JLabel hashtagsLabel = new JLabel("Hashtags: " + String.join(", ", entity.getHashtags()));
	        JTextArea contentArea = new JTextArea(entity.getContent());
	        contentArea.setEditable(false);
	        contentArea.setLineWrap(true);
	        contentArea.setWrapStyleWord(true);
	        contentArea.setPreferredSize(new Dimension(400, 150));

	        JLabel timeLabel = new JLabel("Time: " + entity.getTime().formatted(DateTimeFormatter.ISO_DATE_TIME));

	        twitterPanel.add(authorLabel);
	        twitterPanel.add(hashtagsLabel);
	        twitterPanel.add(contentArea);
	        twitterPanel.add(timeLabel);

	        JOptionPane.showMessageDialog(null, twitterPanel, "Twitter View", JOptionPane.PLAIN_MESSAGE);
	}
}