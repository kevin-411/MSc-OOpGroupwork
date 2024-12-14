import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BidTest {

    @Test
    void testPlaceBid() {
        User bidder = new User(2, "Test Bidder");

        // Set up an auction item
        MotorVehicleItem vehicle = new MotorVehicleItem(
                1,
                "Sports Car",
                "A sleek red sports car",
                50000,
                LocalDateTime.now().plusDays(7),
                new User(1, "Test Seller"),
                80000,
                50000,
                LocalDateTime.of(2018, 1, 1, 0, 0)
        );

        // Create a bid
        Bid bid = new Bid(1, 60000, bidder, vehicle);

        // Place the bid
        bid.placeBid();

        // Verify that the highest bid was updated
        assertEquals(60000, vehicle.getHighestBid());
    }

    @Test
    void testPlaceBidBelowStartingBid() {
        User bidder = new User(2, "Test Bidder");

        // Set up an auction item
        MotorVehicleItem vehicle = new MotorVehicleItem(
                1,
                "Sports Car",
                "A sleek red sports car",
                50000, // Starting bid
                LocalDateTime.now().plusDays(7),
                new User(1, "Test Seller"),
                80000,
                50000,
                LocalDateTime.of(2018, 1, 1, 0, 0)
        );

        // Create an invalid bid below the starting bid
        Bid bid = new Bid(1, 40000, bidder, vehicle);

        // Assert exception is thrown
        assertThrows(IllegalArgumentException.class, bid::placeBid);
    }
}