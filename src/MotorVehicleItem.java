import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Represents a motor vehicle item being auctioned.
 * Implements Valuable to calculate depreciation.
 */
public class MotorVehicleItem extends AuctionItem implements Valuable {
    private double originalValue; // Original price of the vehicle
    private double currentValue;  // Current value of the vehicle
    private LocalDateTime purchaseDate; // Purchase date of the vehicle

    public MotorVehicleItem(int itemId, String itemName, String description,
                            double startingBid, LocalDateTime endTime, User seller,
                            double originalValue, double currentValue, LocalDateTime purchaseDate) {
        super(itemId, itemName, description, startingBid, endTime, seller);
        this.originalValue = originalValue;
        this.currentValue = currentValue;
        this.purchaseDate = purchaseDate;
    }

    @Override
    public double calculateValueChange() {
        if (originalValue <= 0) {
            throw new IllegalArgumentException("Original value must be greater than 0.");
        }

        // Calculate the total number of years since the purchase date
        long yearsElapsed = ChronoUnit.YEARS.between(purchaseDate, LocalDateTime.now());

        if (yearsElapsed <= 0) {
            throw new IllegalArgumentException("Purchase date must be in the past.");
        }

        return -1 * ((originalValue - currentValue) / originalValue) / yearsElapsed * 100; // Depreciation rate
    }

    // Getters and Setters for additional properties
    public double getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(double originalValue) {
        this.originalValue = originalValue;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}