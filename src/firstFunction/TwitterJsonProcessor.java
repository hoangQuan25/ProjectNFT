package firstFunction;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class TwitterJsonProcessor extends JsonProcessor {
    @Override
    boolean containsTag(JsonArray hashtagArray, String targetHashtag) {
        // Implementation specific to Twitter posts
        for (JsonElement hashtagElement : hashtagArray) {
            if (hashtagElement.getAsString().equalsIgnoreCase(targetHashtag)) {
                return true;
            }
        }
        return false;
    }

    @Override
    boolean containsKeyword(JsonArray keywordsArray, String targetKeyword) {
        // Twitter posts do not have keywords, so the method is not implemented
        return false;
    }

    @Override
    void displayUniqueContent(JsonObject jsonObject) {
        System.out.println("Twitter Content: " + jsonObject.get("content").getAsString());
        System.out.println("Author: " + jsonObject.get("author").getAsString());
        System.out.println("Time: " + jsonObject.get("time").getAsString());
        System.out.println("Hashtags: " + jsonObject.getAsJsonArray("hashtags"));
        System.out.println("\n");
    }

    void displayUniqueHashtags(String jsonString) {
        TreeSet<String> uniqueHashtags = new TreeSet<>();
        Pattern pattern = Pattern.compile("#\\w+");
        Matcher matcher = pattern.matcher(jsonString);

        while (matcher.find()) {
            uniqueHashtags.add(matcher.group());
        }

        System.out.print("Sorted Unique Hashtags: \n");
        uniqueHashtags.forEach(hashtag -> System.out.print(hashtag + "\n"));
        System.out.println();
    }
}
