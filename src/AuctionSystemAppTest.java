import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuctionSystemAppTest {
    private List<User> users;
    private List<AuctionItem> auctionItems;

    @BeforeEach
    void setUp() {
        // Initialize the user list and auction item list
        users = List.of(
                new User(1, "Alice"),
                new User(2, "Bob")
        );

        auctionItems = new ArrayList<>();
    }

    @Test
    void testLogin() {
        String username = "Alice";

        User loggedInUser = null;
        for (User user : users) {
            if (user.getUserName().equalsIgnoreCase(username)) {
                loggedInUser = user;
                break;
            }
        }

        assertNotNull(loggedInUser);
        assertEquals("Alice", loggedInUser.getUserName());
    }

    @Test
    void testAddMotorVehicleItem() {
        User loggedInUser = new User(1, "Alice");

        MotorVehicleItem vehicle = new MotorVehicleItem(
                auctionItems.size() + 1,
                "Sports Car",
                "A sleek red sports car",
                50000,
                LocalDateTime.now().plusDays(7),
                loggedInUser,
                80000,
                50000,
                LocalDateTime.of(2015, 1, 1, 0, 0)
        );

        auctionItems.add(vehicle);

        assertEquals(1, auctionItems.size());
        assertEquals("Sports Car", auctionItems.get(0).getItemName());
        assertEquals(loggedInUser, auctionItems.get(0).getSeller());
    }

    @Test
    void testPlaceBid() {
        User seller = new User(1, "Alice");
        User bidder = new User(2, "Bob");

        MotorVehicleItem vehicle = new MotorVehicleItem(
                1,
                "Sports Car",
                "A sleek red sports car",
                50000,
                LocalDateTime.now().plusDays(7),
                seller,
                80000,
                50000,
                LocalDateTime.of(2015, 1, 1, 0, 0)
        );

        auctionItems.add(vehicle);

        // Create a valid bid
        Bid bid = new Bid(1, 60000, bidder, vehicle);
        bid.placeBid();

        // Verify the highest bid
//        assertEquals(60000, vehicle.getHighestBid());
    }
}