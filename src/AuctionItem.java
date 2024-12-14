import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an item being auctioned.
 * Complies with Single Responsibility Principle as it solely manages auction item operations.
 */
public class AuctionItem {
    private int itemId;                     // Unique identifier
    private String itemName;                // Name of the item
    private String description;             // Description of the item
    private double startingBid;             // Minimum bid amount to start auction
    private LocalDateTime endTime;          // Auction end time
    private User seller;                    // Seller of the item (composition: User class)
    private List<Bid> bids;                 // List of placed bids (supports encapsulation)

    /**
     * Constructor adhering to Encapsulation.
     */
    public AuctionItem(int itemId, String itemName, String description,
                       double startingBid, LocalDateTime endTime, User seller) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.description = description;
        this.startingBid = startingBid;
        this.endTime = endTime;
        this.seller = seller;
        this.bids = new ArrayList<>();      // Initialize bids to maintain SRP and avoid null references
    }

    // --- Methods demonstrating OOP & SOLID principles ---

    /**
     * Static factory method to create a new Auction Item (SRP: Item creation only).
     */
    public static AuctionItem createAuctionItem(int itemId, String itemName, String description,
                                                double startingBid, LocalDateTime endTime, User seller) {
        return new AuctionItem(itemId, itemName, description, startingBid, endTime, seller);
    }

    /**
     * Updates item details, ensuring encapsulated changes (SRP: Update operation only).
     */
    public void updateItem(String itemName, String description, double startingBid, LocalDateTime endTime) {
        this.itemName = itemName;
        this.description = description;
        this.startingBid = startingBid;
        this.endTime = endTime;
    }

    /**
     * Deletes the auction item (SRP: Specific to deletion).
     * Adheres to Encapsulation and Abstraction by separating user validation concerns.
     */
    public void deleteItem(User requestUser) {
        if (!this.seller.equals(requestUser)) {
            throw new IllegalArgumentException("Only the seller can delete this item.");
        }
        // Deletion logic (e.g., archive or remove from a collection)
        System.out.println("Item " + this.itemId + " has been deleted.");
    }

    /**
     * Returns all bids made on this item (Encapsulation: Maintains private bid list).
     */
    public List<Bid> getBids() {
        return new ArrayList<>(bids); // Encapsulation: Return a copy to avoid external modifications
    }

    /**
     * Adds a new bid to the item (Integration with Bid class: DIP).
     */
    public void addBid(Bid bid) {
        if (bid.getAmount() < this.startingBid) {
            throw new IllegalArgumentException("Bid amount must be greater than or equal to the starting bid.");
        }
        this.bids.add(bid);
    }

    // --- Getters and Setters (Encapsulation: Controlled data access) ---
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getStartingBid() {
        return startingBid;
    }

    public void setStartingBid(double startingBid) {
        this.startingBid = startingBid;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}