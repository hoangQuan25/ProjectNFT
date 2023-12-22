package Functions.function1;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

abstract class JsonProcessor {
    abstract boolean containsTag(JsonArray hashtagArray, String targetHashtag);
    abstract boolean containsKeyword(JsonArray keywordsArray, String targetKeyword);
    abstract void displayUniqueContent(JsonObject jsonObject);

    public static String readJsonFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return new String(Files.readAllBytes(path));
    }

    public void displayJsonArray(String jsonArrayString) {
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(jsonArrayString, JsonArray.class);

        if (jsonArray == null) {
            System.out.println("Invalid JSON array format.");
            return;
        }

        for (JsonElement element : jsonArray) {
            if (element.isJsonObject()) {
                displayUniqueContent(element.getAsJsonObject());
            }
        }
    }

    public void filterByHashtag(String jsonString, String targetHashtag) {
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(jsonString, JsonArray.class);

        if (jsonArray == null) {
            System.out.println("Invalid JSON array format.");
            return;
        }

        boolean found = false;

        for (JsonElement element : jsonArray) {
            if (element.isJsonObject()) {
                JsonObject jsonObject = element.getAsJsonObject();
                JsonArray hashtagArray = jsonObject.getAsJsonArray("hashtags");

                if (containsTag(hashtagArray, targetHashtag)) {
                    displayUniqueContent(jsonObject);
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No posts found for hashtag: " + targetHashtag);
        }
    }

    public void filterByKeyword(String jsonString, String targetKeyword) {
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(jsonString, JsonArray.class);

        if (jsonArray == null) {
            System.out.println("Invalid JSON array format.");
            return;
        }

        boolean found = false;

        for (JsonElement element : jsonArray) {
            if (element.isJsonObject()) {
                JsonObject jsonObject = element.getAsJsonObject();
                JsonArray keywordsArray = jsonObject.getAsJsonArray("keywords");

                if (containsKeyword(keywordsArray, targetKeyword)) {
                    displayUniqueContent(jsonObject);
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No posts found for keyword: " + targetKeyword);
        }
    }
    
    // Additional fields to keep track of hashtag and keyword frequencies
    private Map<String, Integer> hashtagFrequencyMap = new HashMap<>();
    private Map<String, Integer> keywordFrequencyMap = new HashMap<>();

    // Method to update hashtag and keyword frequencies
    private void updateFrequencies(JsonObject jsonObject) {
        JsonArray hashtagArray = jsonObject.getAsJsonArray("hashtags");
        JsonArray keywordsArray = jsonObject.getAsJsonArray("keywords");

        if (hashtagArray != null) {
            for (JsonElement hashtagElement : hashtagArray) {
                String hashtag = hashtagElement.getAsString();
                hashtagFrequencyMap.put(hashtag, hashtagFrequencyMap.getOrDefault(hashtag, 0) + 1);
            }
        }

        if (keywordsArray != null) {
            for (JsonElement keywordElement : keywordsArray) {
                String keyword = keywordElement.getAsString();
                keywordFrequencyMap.put(keyword, keywordFrequencyMap.getOrDefault(keyword, 0) + 1);
            }
        }
    }

    // Method to display the hottest hashtag within a specified time range
    public void displayHottestHashtag(String jsonString, String timeRange) {
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(jsonString, JsonArray.class);

        if (jsonArray == null) {
            System.out.println("Invalid JSON array format.");
            return;
        }

        hashtagFrequencyMap.clear(); // Reset the frequency map

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (JsonElement element : jsonArray) {
            if (element.isJsonObject()) {
                JsonObject jsonObject = element.getAsJsonObject();
                LocalDateTime postTime = LocalDateTime.parse(jsonObject.get("time").getAsString(), formatter);

                if (isWithinTimeRange(postTime, currentTime, timeRange)) {
                    updateFrequencies(jsonObject);
                }
            }
        }

        String hottestHashtag = findHottest(hashtagFrequencyMap);
        System.out.println("Hottest Hashtag in the last " + timeRange + ": " + hottestHashtag);
    }

    // Method to display the hottest keyword within a specified time range
    public void displayHottestKeyword(String jsonString, String timeRange) {
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(jsonString, JsonArray.class);

        if (jsonArray == null) {
            System.out.println("Invalid JSON array format.");
            return;
        }

        keywordFrequencyMap.clear(); // Reset the frequency map

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (JsonElement element : jsonArray) {
            if (element.isJsonObject()) {
                JsonObject jsonObject = element.getAsJsonObject();
                LocalDateTime postTime = LocalDateTime.parse(jsonObject.get("time").getAsString(), formatter);

                if (isWithinTimeRange(postTime, currentTime, timeRange)) {
                    updateFrequencies(jsonObject);
                }
            }
        }

        String hottestKeyword = findHottest(keywordFrequencyMap);
        System.out.println("Hottest Keyword in the last " + timeRange + ": " + hottestKeyword);
    }

    // Helper method to check if a post is within the specified time range
    private boolean isWithinTimeRange(LocalDateTime postTime, LocalDateTime currentTime, String timeRange) {
        switch (timeRange.toLowerCase()) {
            case "day":
                return postTime.isAfter(currentTime.minusDays(1));
            case "week":
                return postTime.isAfter(currentTime.minusWeeks(1));
            case "month":
                return postTime.isAfter(currentTime.minusMonths(1));
            default:
                return false;
        }
    }

    // Helper method to find the item with the highest frequency in a frequency map
    private String findHottest(Map<String, Integer> frequencyMap) {
        return frequencyMap.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No data");
    }
}
