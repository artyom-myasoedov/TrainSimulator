package wagons.locomotives;

import factories.WagonFactory;
import junit.framework.TestCase;

import java.math.BigDecimal;

public class SteamLocomotiveTest extends TestCase {

    public void testStartEngine() {
        SteamLocomotive locomotive = WagonFactory.createSteamLocomotive(BigDecimal.valueOf(10), BigDecimal.valueOf(100), BigDecimal.valueOf(0));
        locomotive.startEngine();
    }

    public void testStopEngine() {
        SteamLocomotive locomotive = WagonFactory.createSteamLocomotive(BigDecimal.valueOf(10), BigDecimal.valueOf(100), BigDecimal.valueOf(70));
        locomotive.stopEngine();
    }
}