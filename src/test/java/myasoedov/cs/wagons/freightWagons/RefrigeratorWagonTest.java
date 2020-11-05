package myasoedov.cs.wagons.freightWagons;

import myasoedov.cs.factories.WagonFactory;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class RefrigeratorWagonTest {

    @Test
    public void testSetCurrentTemperature() {
        RefrigeratorWagon wagon = WagonFactory.createRefrigeratorWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(50), 100L);
        wagon.setCurrentTemperature(BigDecimal.valueOf(20));
        assertEquals(wagon.getCurrentTemperature(), BigDecimal.valueOf(20));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCurrentTemperatureException() {
        RefrigeratorWagon wagon = WagonFactory.createRefrigeratorWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(50), 100L);
        wagon.setCurrentTemperature(BigDecimal.valueOf(50));
    }
}