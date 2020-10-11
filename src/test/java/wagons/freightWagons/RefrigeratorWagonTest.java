package wagons.freightWagons;

import factories.WagonFactory;
import junit.framework.TestCase;

import java.math.BigDecimal;

public class RefrigeratorWagonTest extends TestCase {

    public void testSetCurrentTemperature() {
        RefrigeratorWagon wagon = WagonFactory.createRefrigeratorWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(50));
        wagon.setCurrentTemperature(BigDecimal.valueOf(30));
    }
}