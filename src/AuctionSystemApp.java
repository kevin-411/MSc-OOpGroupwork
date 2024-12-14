import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuctionSystemApp {
    // Mock database for users and auction items
    private static List<User> users = new ArrayList<>();
    private static List<AuctionItem> auctionItems = new ArrayList<>();
    private static User loggedInUser = null;

    public static void main(String[] args) {
        // Initializing the system with sample users
        initializeUsers();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Auction System!");
        boolean exit = false;

        while (!exit) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Log In");
            System.out.println("2. Add a Motor Vehicle Item");
            System.out.println("3. View Auction Items");
            System.out.println("4. Place a Bid on an Item");
            System.out.println("5. Exit");

            System.out.print("Choose an option (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline character

            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    addMotorVehicle(scanner);
                    break;
                case 3:
                    viewAuctionItems();
                    break;
                case 4:
                    placeBid(scanner);
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    /**
     * Initializes the system with sample users.
     */
    private static void initializeUsers() {
        users.add(new User(1, "Alice"));
        users.add(new User(2, "Bob"));
        users.add(new User(3, "Charlie"));
    }

    /**
     * Handles user login.
     */
    private static void login(Scanner scanner) {
        System.out.println("\n-- Log In --");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        for (User user : users) {
            if (user.getUserName().equalsIgnoreCase(username)) {
                loggedInUser = user;
                System.out.println("Login successful! Welcome, " + loggedInUser.getUserName() + "!");
                return;
            }
        }

        System.out.println("User not found. Please try again.");
    }

    /**
     * Allows adding a motor vehicle item to the auction.
     */
    private static void addMotorVehicle(Scanner scanner) {
        if (!isLoggedIn()) {
            System.out.println("You must log in first to add an auction item.");
            return;
        }

        System.out.println("\n-- Add a Motor Vehicle Item --");

        System.out.print("Enter the vehicle name: ");
        String itemName = scanner.nextLine();

        System.out.print("Enter the description: ");
        String description = scanner.nextLine();

        System.out.print("Enter the starting bid: ");
        double startingBid = scanner.nextDouble();

        System.out.print("Enter the original value: ");
        double originalValue = scanner.nextDouble();

        System.out.print("Enter the current value: ");
        double currentValue = scanner.nextDouble();
        scanner.nextLine(); // consume newline character

        System.out.print("Enter the purchase year (e.g., 2015): ");
        int year = scanner.nextInt();

        System.out.print("Enter the purchase month (e.g., 7 for July): ");
        int month = scanner.nextInt();

        System.out.print("Enter the purchase day (e.g., 15): ");
        int day = scanner.nextInt();
        scanner.nextLine(); // consume newline character

        LocalDateTime purchaseDate = LocalDateTime.of(year, month, day, 0, 0);

        // Create a MotorVehicleItem object
        MotorVehicleItem motorVehicleItem = new MotorVehicleItem(
                auctionItems.size() + 1, // Auto-generate item ID
                itemName,
                description,
                startingBid,
                LocalDateTime.now().plusDays(7), // Auction ends in 7 days
                loggedInUser, // Current user is the seller
                originalValue,
                currentValue,
                purchaseDate
        );

        auctionItems.add(motorVehicleItem);

        System.out.println("Motor Vehicle Item added successfully with ID " + motorVehicleItem.getItemId());
    }

    /**
     * Displays all auction items.
     */
    private static void viewAuctionItems() {
        System.out.println("\n-- Auction Items --");

        if (auctionItems.isEmpty()) {
            System.out.println("No items are currently listed in the auction.");
            return;
        }

        for (AuctionItem item : auctionItems) {
            System.out.println("Item ID: " + item.getItemId() +
                    ", Name: " + item.getItemName() +
                    ", Description: " + item.getDescription() +
                    ", Starting Bid: $" + item.getStartingBid() +
                    ", Seller: " + item.getSeller().getUserName());
        }
    }

    /**
     * Allows placing a bid on an auction item.
     */
    private static void placeBid(Scanner scanner) {
        if (!isLoggedIn()) {
            System.out.println("You must log in first to place a bid.");
            return;
        }

        System.out.println("\n-- Place a Bid --");

        if (auctionItems.isEmpty()) {
            System.out.println("No items are currently listed in the auction.");
            return;
        }

        System.out.print("Enter the Item ID you want to bid on: ");
        int itemId = scanner.nextInt();

        AuctionItem selectedItem = null;

        for (AuctionItem item : auctionItems) {
            if (item.getItemId() == itemId) {
                selectedItem = item;
                break;
            }
        }

        if (selectedItem == null) {
            System.out.println("Item with the specified ID not found.");
            return;
        }

        System.out.print("Enter your bid amount: ");
        double bidAmount = scanner.nextDouble();

        if (bidAmount < selectedItem.getStartingBid()) {
            System.out.println("Bid amount must be equal to or greater than the starting bid.");
            return;
        }

        // Create and place the bid
        Bid bid = new Bid(1, bidAmount, loggedInUser, selectedItem);
        bid.placeBid();

        System.out.println("Bid placed successfully on item: " + selectedItem.getItemName());
    }

    /**
     * Checks if a user is logged in.
     */
    private static boolean isLoggedIn() {
        return loggedInUser != null;
    }
}