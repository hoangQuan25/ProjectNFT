package Functions.function1;



import java.io.IOException;

import com.google.gson.JsonArray;



public class Test {

    public static void main(String[] args) {
        // Example usage
    	String twitterFile = "src/data/outputData/TwitterData/twitter.json";
    	String blogFile = "src/data/outputData/blogData/nftically.json";
    	String blog2File = "src/data/outputData/blogData/nonFungible.json";
    	String twitterJsonString = null;
		try {
			twitterJsonString = JsonProcessor.readJsonFile(twitterFile);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String blogJsonString = null;
		try {
			blogJsonString = JsonProcessor.readJsonFile(blogFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String blog2JsonString = null;
		try {
			blog2JsonString = JsonProcessor.readJsonFile(blog2File);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		JsonArray hashtagArray = new JsonArray();
        HashtagJsonProcessor twitterProcessor = new HashtagJsonProcessor(hashtagArray);
        KeywordJsonProcessor blogProcessor = new KeywordJsonProcessor();

        // Filter Twitter posts by hashtag
        twitterProcessor.displayUniqueHashtags(twitterJsonString);
        twitterProcessor.filterByHashtag(twitterJsonString, "#NFT");

        // Filter Blog posts by keyword
        blogProcessor.displayUniqueKeywords(blogJsonString);
        blogProcessor.filterByKeyword(blogJsonString, "");
        
        //Filter Blog posts by hashtag
        twitterProcessor.displayUniqueHashtags(blog2JsonString);
        twitterProcessor.filterByHashtag(blog2JsonString, "#NFT");
        // Display the hottest hashtag in the last day
//        postProcessor.displayHottestHashtag(twitterJsonString, "day");
//
//        // Display the hottest keyword in the last week
//        postProcessor.displayHottestKeyword(blogJsonString, "week");
    }
}
