package myasoedov.cs.wagons.passengerWagons;

import myasoedov.cs.factories.WagonFactory;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CoupeWagonTest {

    static CoupeWagon wagon;

    @Before
    public void createWagon() {
        wagon = WagonFactory.createCoupeWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(100), 100L);
    }

    @Test
    public void testGetNumberOfSeats() {
        assertEquals(wagon.getNumberOfSeats(), 40);
    }

    @Test
    public void testGetNumberOfPassengers() {
        wagon.addPassengers(10);
        assertEquals(wagon.getNumberOfPassengers(), 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddPassengersException() {
        wagon.addPassengers(120);
    }

    @Test
    public void testRemovePassengers() {
        wagon.addPassengers(10);
        wagon.removePassengers(3);
        assertEquals(wagon.getNumberOfPassengers(), 7);
    }
}