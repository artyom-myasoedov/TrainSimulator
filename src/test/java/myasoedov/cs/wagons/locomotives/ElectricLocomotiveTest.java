package myasoedov.cs.wagons.locomotives;

import myasoedov.cs.factories.WagonFactory;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ElectricLocomotiveTest {

    static ElectricLocomotive locomotive;

    @Before
    public void createLocomotive() {
        locomotive = WagonFactory.createElectricLocomotive(BigDecimal.valueOf(1), BigDecimal.valueOf(70), LocomotiveEngineConditions.DISABLED, UUID.randomUUID());
    }

    @Test
    public void testSetPowerGridConnection() {
        assertFalse(locomotive.isPowerGridConnect());
        locomotive.setPowerGridConnection(LocomotiveEngineConditions.ENABLED);
        assertTrue(locomotive.isPowerGridConnect());
    }

    @Test
    public void testStartEngine() {
        locomotive.setPowerGridConnection(LocomotiveEngineConditions.ENABLED);
        locomotive.startEngine();
        assertTrue(locomotive.isEngineWork());
    }

    @Test(expected = IllegalStateException.class)
    public void testStartEngineException() {
        locomotive.startEngine();
    }

    @Test
    public void testStopEngine() {
        locomotive.stopEngine();
        assertFalse(locomotive.isEngineWork());
    }
}