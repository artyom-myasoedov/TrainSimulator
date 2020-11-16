package myasoedov.cs.trains;

import myasoedov.cs.factories.WagonFactory;
import org.junit.Before;
import org.junit.Test;
import myasoedov.cs.models.abstractWagons.FreightWagon;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class FreightTrainTest {
        public static FreightTrain<FreightWagon> train;



    @Before
    public void prepareTrain() {
        train = new FreightTrain<>(UUID.randomUUID());
        train.addHeadWagon(WagonFactory.createRefrigeratorWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(100), UUID.randomUUID()));
        train.addHeadWagon(WagonFactory.createCoveredWagon(BigDecimal.valueOf(12), BigDecimal.valueOf(80), UUID.randomUUID()));
        train.addHeadWagon(WagonFactory.createPlatformWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90), UUID.randomUUID()));
    }

    @Test
    public void testGetTotalMaxCarrying() {
        assertEquals(train.getTotalMaxCarrying(), 7500);
    }

    @Test
    public void testGetTotalCurrentCargoWeight() {
        train.getWagon(0).loadCargo(100);
        train.getWagon(1).loadCargo(1000);
        train.getWagon(2).loadCargo(1200);
        assertEquals(train.getTotalCurrentCargoWeight(), 2300);
    }

    @Test
    public void testGetTotalWeight() {
        train.getWagon(0).loadCargo(100);
        train.getWagon(1).loadCargo(1000);
        train.getWagon(2).loadCargo(1200);
        assertEquals(train.getTotalWeight(), 9300);

    }

    @Test
    public void testAddHeadWagon() {
        assertEquals(train.getWagonsSize(), 3);
        train.addHeadWagon(WagonFactory.createRefrigeratorWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(100), UUID.randomUUID()));
        assertEquals(train.getWagonsSize(), 4);
    }

    @Test
    public void testAddTailWagon() {
        assertEquals(train.getWagonsSize(), 3);
        train.addTailWagon(WagonFactory.createRefrigeratorWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(100), UUID.randomUUID()));
        assertEquals(train.getWagonsSize(), 4);
    }

    @Test
    public void testUnhookHeadWagon() {
        assertEquals(train.getWagonsSize(), 3);
        train.addHeadWagon(WagonFactory.createRefrigeratorWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(100), UUID.randomUUID()));
        assertEquals(train.getWagonsSize(), 4);
        train.unhookHeadWagon();
        assertEquals(train.getWagonsSize(), 3);

    }

    @Test
    public void testUnhookTailWagon() {
        assertEquals(train.getWagonsSize(), 3);
        train.addTailWagon(WagonFactory.createRefrigeratorWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(100), UUID.randomUUID()));
        assertEquals(train.getWagonsSize(), 4);
        train.unhookTailWagon();
        assertEquals(train.getWagonsSize(), 3);
    }

}