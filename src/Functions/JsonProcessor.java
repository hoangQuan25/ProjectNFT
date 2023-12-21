package Functions;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


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
}
