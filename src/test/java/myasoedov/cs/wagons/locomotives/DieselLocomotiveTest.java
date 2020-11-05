package myasoedov.cs.wagons.locomotives;

import myasoedov.cs.factories.WagonFactory;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class DieselLocomotiveTest {

    static DieselLocomotive locomotive;

    @Before
    public void createLocomotive() {
         locomotive = WagonFactory.createDieselLocomotive(BigDecimal.valueOf(10), BigDecimal.valueOf(90), BigDecimal.valueOf(80), 100L);
    }

    @Test
    public void testIsEngineWork() {
        assertFalse(locomotive.isEngineWork());
        locomotive.startEngine();
        assertTrue(locomotive.isEngineWork());
    }

    @Test
    public void testMoveForward() {
        locomotive.moveForward();
    }

    @Test
    public void testMoveBehind() {
        locomotive.moveBehind();
    }

    @Test
    public void testStopMoving() {
        locomotive.stopMoving();
    }

    @Test
    public void testSetVolumeFuel() {
        locomotive.setVolumeFuel(BigDecimal.valueOf(10));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetVolumeFuelException() {
        locomotive.setVolumeFuel(BigDecimal.valueOf(910));
    }

    @Test
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