public class ShopListing {
    private AuctionUser seller;
    private Object imageOrFile;
    private String title;
    private String description;
    private double shopPrice;

    public ShopListing (AuctionUser seller, Object imageOrFile, String title, String description, double shopPrice){
        this.seller = seller;
        this.imageOrFile = imageOrFile;
        this.title = title;
        this.description = description;
        this.shopPrice = shopPrice;

    }
}