package myasoedov.cs.wagons.locomotives;

import myasoedov.cs.factories.WagonFactory;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SteamLocomotiveTest {

    static SteamLocomotive locomotive;

    @Before
    public void createLocomotive() {
        locomotive = WagonFactory.createSteamLocomotive(BigDecimal.valueOf(10), BigDecimal.valueOf(100), BigDecimal.valueOf(0), UUID.randomUUID());
    }

    @Test(expected = IllegalStateException.class)
    public void testStartEngine() {
        locomotive.startEngine();
        assertTrue(locomotive.isEngineWork());
    }

    @Test
    public void testStopEngine() {
        locomotive.stopEngine();
        assertFalse(locomotive.isEngineWork());
    }
}
