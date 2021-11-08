public class AuctionListing {

    private String auctionAddress;
    private AuctionUser seller;
    private Object imageOrFile;
    private String title;
    private double shopPrice;
    private String description;
    private double priceIncrement;
    private AuctionUser currentBidder;
    private double currentPrice;
    private double endBlock;
    private boolean isActive;

    public AuctionListing(AuctionUser seller, double initialPrice, double priceIncrement, int auctionTime, double shopPrice) {
        isActive = true;
        currentBidder = null;
        this.seller = seller;


        if (initialPrice >= 0) {
            this.currentPrice = initialPrice;
        } else {
            this.currentPrice = 1;
        }

        if (priceIncrement > 0) {
            this.priceIncrement = priceIncrement;
        } else {
            this.priceIncrement = 0.5;
        }

        int remainingTime = 0; // remaining time in
        if (auctionTime >= 4) {
            remainingTime = auctionTime;
        } else {
            remainingTime = 2880;
        }
        // set startBlock. temporariliy set to 0 for testing
        int startBlock = 0; // SET TO ZERO FOR TESTING <--------------
        endBlock = startBlock + remainingTime;
    }

    public boolean endAuction(){
        if (isActive == true){
            isActive = false;
            seller.addFunds(currentPrice);
            System.out.println ("item has been given to " + currentBidder.getWalletAddress());
            return true;

        }
        return false;
    }

    public AuctionListing(AuctionUser seller, Object imageOrFile, String title, String description, double initialPrice,
            double priceIncrement, int auctionTime, double shopPrice) {
        isActive = true;
        currentBidder = null;
        this.seller = seller;
        this.description = description;
        this.title = title;
        this.shopPrice = shopPrice;
        this.imageOrFile = imageOrFile;

        if (initialPrice >= 0) {
            this.currentPrice = initialPrice;
        } else {
            this.currentPrice = 1;
        }

        if (priceIncrement > 0) {
            this.priceIncrement = priceIncrement;
        } else {
            this.priceIncrement = 0.5;
        }

        int remainingTime = 0; // remaining time in
        if (auctionTime >= 4) {
            remainingTime = auctionTime;
        } else {
            remainingTime = 2880;
        }
        // set startBlock. temporariliy set to 0 for testing
        int startBlock = 0; // SET TO ZERO FOR TESTING <--------------
        endBlock = startBlock + remainingTime;
    }

    public boolean bid(double bidPrice, AuctionUser bidder, String comment) {
        if (bidPrice < currentPrice + priceIncrement) {
            System.out.println("bid of " + bidPrice + " is insufficient!");
            return false;
        } else if (bidder.getWalletBalance() < bidPrice) {
            System.out.println("bidder has insufficient funds!");
            return false;
        }
        if (comment.length()> 100){
            comment = comment.substring(0, 100);
        }
        double oldPrice = currentPrice;
        currentPrice = bidPrice;
        if (currentBidder != null) {
            currentBidder.addFunds(oldPrice);
        }
        bidder.withdraw(bidPrice);
        currentBidder = bidder;

        System.out.println("bid of " + bidPrice + " has been placed to auction " + this.auctionAddress + ".");
        return true;

    }

    public boolean isActive() {
        return isActive;
    }

    public void loop() {
        // set current block
        int currentBlock = 20; // set to 20 ONLY FOR TESTING
        if (currentBlock > endBlock) {
            if (currentBidder==null){
                transferToShopListing();
            }
            else{
                //give item to currentBidder
                seller.addFunds(currentPrice);
                System.out.println ("item has been given to " + currentBidder.getWalletAddress());
            }
            isActive = false;
        }
    }

    private ShopListing transferToShopListing() {
        return new ShopListing(seller, imageOrFile, title, description, shopPrice);
    }

    public String createPoolBid() {
        // can't implement yet
        return "will be the address of created wallet";

    }

    public void transfer(String address) {
        System.out.println("The item of auction " + auctionAddress + " has been given to " + address + ".");
    }

}
