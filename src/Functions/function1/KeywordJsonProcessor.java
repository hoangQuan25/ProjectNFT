package Functions.function1;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




class KeywordJsonProcessor extends JsonProcessor {
	
    @Override
    boolean containsTag(JsonArray hashtagArray, String targetHashtag) {
        
        return false;
    }

    @Override
    boolean containsKeyword(JsonArray keywordsArray, String targetKeyword) {
        // Implementation specific to Blog posts
        for (JsonElement keywordElement : keywordsArray) {
            if (keywordElement.getAsString().equals(targetKeyword)) {
                return true;
            }
        }
        return false;
    }

    @Override
    void displayUniqueContent(JsonObject jsonObject) {
        System.out.println("Blog Content: " + jsonObject.get("content").getAsString());
        System.out.println("Time: " + jsonObject.get("time").getAsString());
        if (jsonObject.has("hashtags")) {
            System.out.println("Hashtags: " + jsonObject.getAsJsonArray("hashtags"));
        } else if (jsonObject.has("url") && jsonObject.has("title")) {
            System.out.println("URL: " + jsonObject.get("url").getAsString());
            System.out.println("Title: " + jsonObject.get("title").getAsString());
        }
        System.out.println("\n");
    }

    void displayUniqueKeywords(String jsonString) {
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(jsonString, JsonArray.class);

        if (jsonArray == null) {
            System.out.println("Invalid JSON array format.");
            return;
        }

        Set<String> uniqueKeywords = new TreeSet<>(); // Using TreeSet for automatic sorting

        for (JsonElement element : jsonArray) {
            if (element.isJsonObject()) {
                JsonObject jsonObject = element.getAsJsonObject();
                JsonArray keywordsArray = jsonObject.getAsJsonArray("keywords");

                for (JsonElement keywordElement : keywordsArray) {
                    uniqueKeywords.add(keywordElement.getAsString());
                }
            }
        }

        System.out.println("Sorted Unique Keywords:");
        uniqueKeywords.forEach(keyword -> System.out.println(keyword));
    }
    
    public String findMostPopularKeyword(List<JsonObject> posts) {
        // Create a map to store the count of each keyword
        Map<String, Integer> keywordCountMap = new HashMap<>();

        // Iterate through each post and count the keywords
        for (JsonObject post : posts) {
            JsonArray keywords = post.getAsJsonArray("keywords");
            if (keywords != null) {
                for (int i = 0; i < keywords.size(); i++) {
                    String keyword = keywords.get(i).getAsString();
                    keywordCountMap.put(keyword, keywordCountMap.getOrDefault(keyword, 0) + 1);
                }
            }
        }

        // Find the most popular keyword
        String mostPopularKeyword = null;
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : keywordCountMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostPopularKeyword = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        return mostPopularKeyword;
    }
    
}