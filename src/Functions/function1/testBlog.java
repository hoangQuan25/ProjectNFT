package Functions.function1;

import java.io.IOException;

import util.path.JsonURL;

public class testBlog {
	
	 public static void main(String[] args) {
	    String blog1JsonString = null;
    	String blog2JsonString = null;
    	String blog3JsonString = null;
    	String blog4JsonString = null;
    	
		try {
			blog1JsonString = JsonProcessor.readJsonFile(JsonURL.AIRNFT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			blog2JsonString = JsonProcessor.readJsonFile(JsonURL.NFTIFICALLY);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			blog3JsonString = JsonProcessor.readJsonFile(JsonURL.NFTNEWS);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			blog4JsonString = JsonProcessor.readJsonFile(JsonURL.NONFUNGIBLE);
			
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