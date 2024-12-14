import java.time.LocalDateTime;

/**
 * Represents a user's bid on an auction item.
 * Follows SRP by managing bid-specific properties and behavior.
 */
public class Bid {
    private int bidId;                  // Unique identifier for the bid
    private double amount;              // Bid amount
    private User bidder;                // Bidder (type: User)
    private AuctionItem auctionItem;    // Auction item the bid belongs to
    private LocalDateTime timestamp;    // Timestamp of the bid

    /**
     * Constructor to initialize a bid.
     */
    public Bid(int bidId, double amount, User bidder, AuctionItem auctionItem) {
        this.bidId = bidId;
        this.amount = amount;
        this.bidder = bidder;
        this.auctionItem = auctionItem;
        this.timestamp = LocalDateTime.now(); // Automatically sets the current time for the bid
    }

    // --- Methods Demonstrating Encapsulation and Single Responsibility ---

    /**
     * Places a bid on the given auction item.
     * Enforces rules like ensuring bid amount exceeds the auction's starting bid.
     */
    public void placeBid() {
        if (this.amount < auctionItem.getStartingBid()) {
            throw new IllegalArgumentException("Bid amount must be equal to or greater than the starting bid.");
        }
        // Add this bid to the AuctionItem's bids
        auctionItem.addBid(this); // Dependency Inversion Principle: AuctionItem manages its own state
        System.out.println("Bid placed successfully by " + bidder.getUserName()
                + " on item '" + auctionItem.getItemName() + "' with amount $" + amount);
    }

    /**
     * Retrieves the details of the bid (Encapsulation ensures safe access).
     * @return String representation of the bid details.
     */
    public String getBidDetails() {
        return "Bid ID: " + bidId
                + ", Amount: $" + amount
                + ", Bidder: " + bidder.getUserName()
                + ", Auction Item: " + auctionItem.getItemName()
                + ", Timestamp: " + timestamp;
    }

    // --- Getters and Setters (Encapsulation) ---

    public int getBidId() {
        return bidId;
    }

    public void setBidId(int bidId) {
        this.bidId = bidId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getBidder() {
        return bidder;
    }

    public void setBidder(User bidder) {
        this.bidder = bidder;
    }

    public AuctionItem getAuctionItem() {
        return auctionItem;
    }

    public void setAuctionItem(AuctionItem auctionItem) {
        this.auctionItem = auctionItem;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}