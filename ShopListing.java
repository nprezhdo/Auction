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

    public boolean purchase (AuctionUser buyer){
        if (buyer.getWalletBalance()<shopPrice){
            return false;
        }
        seller.addFunds(shopPrice);
        buyer.withdraw(shopPrice);
        //transfer money between wallets
        //transfer item to seller
        return true;
    }
}