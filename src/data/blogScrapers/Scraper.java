package data.blogScrapers;

public class Scraper {

    public static void main(String[] args) {
        AirnftScraper airnft = new AirnftScraper();
        NfticallyScraper nftically = new NfticallyScraper();
        NFTNewsTodayScraper nftnews = new NFTNewsTodayScraper();
        NonFungibleScraper nonfungible = new NonFungibleScraper();

        try {
            System.out.println("Scraping Airnft...");
            airnft.scrape();

            System.out.println("Scraping Nftically...");
            nftically.scrape();

            System.out.println("Scraping NFTNewsToday...");
            nftnews.scrape();

            System.out.println("Scraping NonFungible...");
            nonfungible.scrape();

            System.out.println("Scraping complete!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Add resource cleanup logic if necessary
        }
    }
}
