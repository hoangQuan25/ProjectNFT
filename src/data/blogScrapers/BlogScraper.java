package data.blogScrapers;

import data.model.BlogDataModel;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface BlogScraper {

    void scrape();

    BlogDataModel extractDataFromUrl(String url);

    Set<String> extractKeywords(org.jsoup.nodes.Document document, String selector);

    String extractTime(org.jsoup.nodes.Element timeElement);

    String extractContent(org.jsoup.nodes.Document document);

    String blogDataModelListToJson(List<BlogDataModel> blogDataModelList);

    void writeJsonToFile(String json, String fileName) throws IOException;

	String BlogDataModelListToJson(Set<BlogDataModel> BlogDataModelList);

}
