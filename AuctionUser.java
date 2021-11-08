public class AuctionUser {
    private String walletAddress;
    private double walletBalance;
    private String name;
    public AuctionUser (String name, String walletAddress) {
        this.setName(name);
        this.setWalletAddress(walletAddress);
        //setWalletBalance

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void addFunds (double amount){
        walletBalance+=amount;
    }

    public boolean withdraw (double amount){
        if (amount > walletBalance){
            return false;
        }
        else{
            walletBalance-=amount;
            return true;
        }
            
    }


    public double getWalletBalance() {
        return walletBalance;
    }
    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }
    public String getWalletAddress() {
        return walletAddress;
    }
    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }
    
}