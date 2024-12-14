import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RealEstateItemTest {

    @Test
    void testCalculateAppreciationRate() {
        // Set up the real estate item with values
        RealEstateItem realEstate = new RealEstateItem(
                1,
                "Luxury Villa",
                "A beautiful villa with a pool",
                500000,
                LocalDateTime.now().plusDays(7),
                new User(1, "Test Seller"),
                400000,  // Original value
                600000   // Current market value
        );

        // Calculate expected appreciation rate
        double expectedAppreciationRate = 50.0; // 50%

        // Verify the result
        assertEquals(expectedAppreciationRate, realEstate.calculateValueChange(), 0.1);
    }

    @Test
    void testAppreciationWithZeroOriginalValue() {
        // Set up the real estate item with invalid original value
        RealEstateItem realEstate = new RealEstateItem(
                1,
                "Luxury Villa",
                "A beautiful villa with a pool",
                500000,
                LocalDateTime.now().plusDays(7),
                new User(1, "Test Seller"),
                0,       // Invalid original value
                600000
        );

        // Assert that an exception is thrown
        assertThrows(IllegalArgumentException.class, realEstate::calculateValueChange);
    }
}