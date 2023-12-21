package Functions;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Test {

    public static void main(String[] args) {
        // Example usage
    	String twitterFile = "src/data/outputData/TwitterData/twitter.json";
    	String blogFile = "src/data/outputData/blogData/nftically.json";
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

        TwitterJsonProcessor twitterProcessor = new TwitterJsonProcessor();
        BlogJsonProcessor blogProcessor = new BlogJsonProcessor();

        // Filter Twitter posts by hashtag
        twitterProcessor.displayUniqueHashtags(twitterJsonString);
        twitterProcessor.filterByHashtag(twitterJsonString, "#NFT");

        // Filter Blog posts by keyword
        blogProcessor.displayUniqueKeywords(blogJsonString);
        blogProcessor.filterByKeyword(blogJsonString, "");
    }
}
