package wagons.locomotives;

import factories.WagonFactory;
import junit.framework.TestCase;

import java.math.BigDecimal;

public class DieselLocomotiveTest extends TestCase {

    public void testIsEngineWork() {
        DieselLocomotive locomotive = WagonFactory.createDieselLocomotive(BigDecimal.valueOf(10), BigDecimal.valueOf(90), BigDecimal.valueOf(80));
        assertFalse(locomotive.isEngineWork());
        locomotive.startEngine();
        assertTrue(locomotive.isEngineWork());
    }

    public void testMoveForward() {
        DieselLocomotive locomotive = WagonFactory.createDieselLocomotive(BigDecimal.valueOf(10), BigDecimal.valueOf(90), BigDecimal.valueOf(80));
        locomotive.moveForward();
    }

    public void testMoveBehind() {
        DieselLocomotive locomotive = WagonFactory.createDieselLocomotive(BigDecimal.valueOf(10), BigDecimal.valueOf(90), BigDecimal.valueOf(80));
        locomotive.moveBehind();
    }

    public void testStopMoving() {
        DieselLocomotive locomotive = WagonFactory.createDieselLocomotive(BigDecimal.valueOf(10), BigDecimal.valueOf(90), BigDecimal.valueOf(80));
        locomotive.stopMoving();
    }

    public void testSetVolumeFuel() {
        DieselLocomotive locomotive = WagonFactory.createDieselLocomotive(BigDecimal.valueOf(10), BigDecimal.valueOf(90), BigDecimal.valueOf(80));
        locomotive.setVolumeFuel(BigDecimal.valueOf(10));
        locomotive.setVolumeFuel(BigDecimal.valueOf(1000));
    }

    public void testStartEngine() {
        DieselLocomotive locomotive = WagonFactory.createDieselLocomotive(BigDecimal.valueOf(10), BigDecimal.valueOf(90), BigDecimal.valueOf(80));
        locomotive.startEngine();
    }

    public void testStopEngine() {
        DieselLocomotive locomotive = WagonFactory.createDieselLocomotive(BigDecimal.valueOf(10), BigDecimal.valueOf(90), BigDecimal.valueOf(80));
        locomotive.stopEngine();
    }
}