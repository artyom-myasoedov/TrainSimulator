package trains;

import factories.WagonFactory;
import junit.framework.TestCase;
import org.junit.Before;
import wagons.abstractWagons.Wagon;

import java.math.BigDecimal;

public class FreightTrainTest extends TestCase {

    @Before
    public static void prepareTrain() {
        FreightTrain train = new FreightTrain<>();
        train.addHeadWagon(WagonFactory.createRefrigeratorWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(100)));
        train.addHeadWagon(WagonFactory.createCoveredWagon(BigDecimal.valueOf(12), BigDecimal.valueOf(80)));
        train.addHeadWagon(WagonFactory.createPlatformWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90)));
    }

    public void testGetTotalMaxCarrying() {
        FreightTrain train = new FreightTrain<>();
        train.addHeadWagon(WagonFactory.createRefrigeratorWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(100)));
        train.addHeadWagon(WagonFactory.createCoveredWagon(BigDecimal.valueOf(12), BigDecimal.valueOf(80)));
        train.addHeadWagon(WagonFactory.createPlatformWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90)));
        assertEquals(train.getTotalMaxCarrying(), 7500);
    }

    public void testGetTotalCurrentCargoWeight() {
        FreightTrain train = new FreightTrain<>();
        train.addHeadWagon(WagonFactory.createRefrigeratorWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(100)));
        train.addHeadWagon(WagonFactory.createCoveredWagon(BigDecimal.valueOf(12), BigDecimal.valueOf(80)));
        train.addHeadWagon(WagonFactory.createPlatformWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90)));
        train.getWagon(0).loadCargo(100);
        train.getWagon(1).loadCargo(1000);
        train.getWagon(2).loadCargo(1200);
        assertEquals(train.getTotalCurrentCargoWeight(), 2300);
    }

    public void testGetTotalWeight() {
        FreightTrain train = new FreightTrain<>();
        train.addHeadWagon(WagonFactory.createRefrigeratorWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(100)));
        train.addHeadWagon(WagonFactory.createCoveredWagon(BigDecimal.valueOf(12), BigDecimal.valueOf(80)));
        train.addHeadWagon(WagonFactory.createPlatformWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90)));
        train.getWagon(0).loadCargo(100);
        train.getWagon(1).loadCargo(1000);
        train.getWagon(2).loadCargo(1200);
        assertEquals(train.getTotalWeight(), 9300);

    }

    public void testAddHeadWagon() {
        FreightTrain train = new FreightTrain<>();
        assertEquals(train.getWagonsSize(), 0);
        train.addHeadWagon(WagonFactory.createRefrigeratorWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(100)));
        assertEquals(train.getWagonsSize(), 1);
    }

    public void testAddTailWagon() {
        FreightTrain train = new FreightTrain<>();
        assertEquals(train.getWagonsSize(), 0);
        train.addTailWagon(WagonFactory.createRefrigeratorWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(100)));
        assertEquals(train.getWagonsSize(), 1);
    }

    public void testUnhookHeadWagon() {
        FreightTrain train = new FreightTrain<>();
        assertEquals(train.getWagonsSize(), 0);
        train.addHeadWagon(WagonFactory.createRefrigeratorWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(100)));
        assertEquals(train.getWagonsSize(), 1);
        train.unhookHeadWagon();
        assertEquals(train.getWagonsSize(), 0);

    }

    public void testUnhookTailWagon() {
        FreightTrain train = new FreightTrain<>();
        assertEquals(train.getWagonsSize(), 0);
        train.addTailWagon(WagonFactory.createRefrigeratorWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(100)));
        assertEquals(train.getWagonsSize(), 1);
        train.unhookTailWagon();
        assertEquals(train.getWagonsSize(), 0);
    }

}