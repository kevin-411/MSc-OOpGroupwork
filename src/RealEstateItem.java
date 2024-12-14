import java.time.LocalDateTime;

/**
 * Represents a real estate item being auctioned.
 * Implements Valuable to calculate appreciation.
 */
public class RealEstateItem extends AuctionItem implements Valuable {
    private double originalValue; // Original value of the property
    private double currentMarketValue; // Current market value of the property

    public RealEstateItem(int itemId, String itemName, String description,
                          double startingBid, LocalDateTime endTime, User seller,
                          double originalValue, double currentMarketValue) {
        super(itemId, itemName, description, startingBid, endTime, seller);
        this.originalValue = originalValue;
        this.currentMarketValue = currentMarketValue;
    }

    @Override
    public double calculateValueChange() {
        if (originalValue <= 0) {
            throw new IllegalArgumentException("Original value must be greater than 0.");
        }
        return ((currentMarketValue - originalValue) / originalValue) * 100; // Appreciation rate
    }

    // Getters and Setters for additional properties
    public double getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(double originalValue) {
        this.originalValue = originalValue;
    }

    public double getCurrentMarketValue() {
        return currentMarketValue;
    }

    public void setCurrentMarketValue(double currentMarketValue) {
        this.currentMarketValue = currentMarketValue;
    }
}