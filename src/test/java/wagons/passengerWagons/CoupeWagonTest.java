package wagons.passengerWagons;

import factories.WagonFactory;
import junit.framework.TestCase;
import org.junit.Before;

import java.math.BigDecimal;

public class CoupeWagonTest extends TestCase {

    public void testGetNumberOfSeats() {
        CoupeWagon wagon = WagonFactory.createCoupeWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(100));
        assertEquals(wagon.getNumberOfSeats(), 40);
    }

    public void testGetNumberOfPassengers() {
        CoupeWagon wagon = WagonFactory.createCoupeWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(100));
        wagon.addPassengers(10);
        assertEquals(wagon.getNumberOfPassengers(), 10);
    }

    public void testRemovePassengers() {
        CoupeWagon wagon = WagonFactory.createCoupeWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(100));
        wagon.addPassengers(10);
        wagon.removePassengers(3);
        assertEquals(wagon.getNumberOfPassengers(), 7);
    }
}