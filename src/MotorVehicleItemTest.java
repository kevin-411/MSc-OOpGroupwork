import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MotorVehicleItemTest {

    @Test
    void testCalculateDepreciationRate() {
        // Set up the motor vehicle item with values
        MotorVehicleItem vehicle = new MotorVehicleItem(
                1,
                "Sports Car",
                "A sleek red sports car",
                50000,
                LocalDateTime.now().plusDays(7),
                new User(1, "Test Seller"),
                80000,
                50000,
                LocalDateTime.of(2018, 1, 1, 0, 0) // Purchase date
        );

        // Calculate expected depreciation rate
        double expectedDepreciationRate = -12.5; // 12.5% per year (depreciation)

        // Verify the result
        assertEquals(expectedDepreciationRate, vehicle.calculateValueChange(), 0.1);
    }

    @Test
    void testDepreciationWithZeroOriginalValue() {
        // Set up the motor vehicle item with invalid original value
        MotorVehicleItem vehicle = new MotorVehicleItem(
                1,
                "Sports Car",
                "A sleek red sports car",
                50000,
                LocalDateTime.now().plusDays(7),
                new User(1, "Test Seller"),
                0, // Invalid value
                50000,
                LocalDateTime.of(2018, 1, 1, 0, 0)
        );

        // Assert that an exception is thrown
        assertThrows(IllegalArgumentException.class, vehicle::calculateValueChange);
    }
}