package Functions;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;




class BlogJsonProcessor extends JsonProcessor {
    @Override
    boolean containsTag(JsonArray hashtagArray, String targetHashtag) {
        // Implementation specific to Blog posts
        for (JsonElement hashtagElement : hashtagArray) {
            if (hashtagElement.getAsString().equalsIgnoreCase(targetHashtag)) {
                return true;
            }
        }
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
}