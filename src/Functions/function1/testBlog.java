package Functions.function1;

import java.io.IOException;

public class testBlog {
	
	 public static void main(String[] args) {
		 
		String blog1File = "src/data/outputData/blogData/nftically.json";
	    String blog2File = "src/data/outputData/blogData/nonFungible.json";
	    String blog3File = "src/data/outputData/blogData/nftnewstoday.json";
	    String blog4File = "src/data/outputData/blogData/airnft.json";
	    
	    String blog1JsonString = null;
    	String blog2JsonString = null;
    	String blog3JsonString = null;
    	String blog4JsonString = null;
    	
		try {
			blog1JsonString = JsonProcessor.readJsonFile(blog1File);
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
	 	detailBlog detail =new detailBlog();
	 	detail.loadData(blog3JsonString);
	 	detail.displayJsonArray(detail.getListBlog());
	 	System.out.print("\n");
	 	System.out.print("\n");
	 	System.out.print("\n");
	 	System.out.print("\n");
	 	System.out.print("\n");
	 	detail.filterByKeyword(detail.getListBlog(), "collectibles");
	 	System.out.println("Most Popular Hashtag: " + detail.findMostPopularHashtag(detail.getPostsForDay(detail.getListBlog(), "2023-12-18")));
		 }
}