public class Tester{
    public static void main (String [] args){
        AuctionUser chris = new AuctionUser("chris", "0001");
        chris.setWalletBalance(12);
        AuctionListing listing = new AuctionListing(chris, 10, 2, 100, 20);
        listing.bid(12, chris, "");
        listing.endAuction();
        listing.loop();
    }
}