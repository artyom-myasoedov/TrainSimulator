package wagons.locomotives;

import factories.WagonFactory;
import junit.framework.TestCase;
import wagons.Conditions;

import java.math.BigDecimal;

public class ElectricLocomotiveTest extends TestCase {

    public void testSetPowerGridConnection() {
        ElectricLocomotive locomotive = WagonFactory.createElectricLocomotive(BigDecimal.valueOf(1), BigDecimal.valueOf(70), Conditions.DISABLED);
        assertFalse(locomotive.isPowerGridConnect());
        locomotive.setPowerGridConnection(Conditions.ENABLED);
        assertTrue(locomotive.isPowerGridConnect());
    }

    public void testStartEngine() {
        ElectricLocomotive locomotive = WagonFactory.createElectricLocomotive(BigDecimal.valueOf(1), BigDecimal.valueOf(70), Conditions.ENABLED);
        locomotive.startEngine();
    }

    public void testStopEngine() {
        ElectricLocomotive locomotive = WagonFactory.createElectricLocomotive(BigDecimal.valueOf(1), BigDecimal.valueOf(70), Conditions.DISABLED);
        locomotive.stopEngine();
    }
}