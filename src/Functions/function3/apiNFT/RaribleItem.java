package Functions.function3.apiNFT;

public class RaribleItem {
	private String id;
    private Volume volumeUsd;
    private Volume volumeNative;
    private int itemsBought;
    private FloorPrice floorPrice;
    private int listed;
    private int totalItemSupply;
    private int ownersCount;
    
    public double getFloorPriceETH() {
    	if (floorPrice != null) {
            return floorPrice.getValueFloorPrice();
        } else {
            // Xử lý trường hợp khi floorPrice là null
            return 0.0; // hoặc giá trị mặc định phù hợp với logic của bạn
        }
    }
    
    public double getTotalItemSupply() {
    	return (double) totalItemSupply;
    }
}
