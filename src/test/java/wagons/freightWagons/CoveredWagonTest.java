package wagons.freightWagons;

import factories.WagonFactory;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import wagons.abstractWagons.Wagon;

import java.math.BigDecimal;

public class CoveredWagonTest extends TestCase {


    @BeforeClass
    public static void createWagon() {

    }

    public void testGetIsOpened() {
        CoveredWagon wagon = WagonFactory.createCoveredWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(70));
        assertFalse(wagon.getIsOpened());
        wagon.openWagon();
        assertTrue(wagon.getIsOpened());
        wagon.closeWagon();
        assertFalse(wagon.getIsOpened());
    }


    public void testGetTotalWeight() {
        CoveredWagon wagon = WagonFactory.createCoveredWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(70));
        assertEquals(wagon.getTotalWeight(), 2500);
    }

    public void testSetAge() {
        CoveredWagon wagon = WagonFactory.createCoveredWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(70));
        wagon.setAge(BigDecimal.valueOf(22));
        assertEquals(wagon.getAge(), BigDecimal.valueOf(22));
    }

    public void testRepair() {
        CoveredWagon wagon = WagonFactory.createCoveredWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(70));
        wagon.repair(100);
        assertEquals(wagon.getCondition(), BigDecimal.valueOf(100));
    }

    public void testFullRepair() {
        CoveredWagon wagon = WagonFactory.createCoveredWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(70));
        wagon.fullRepair();
        assertEquals(wagon.getCondition(), BigDecimal.valueOf(100));
    }

    public void testLoadCargo() {
        CoveredWagon wagon = WagonFactory.createCoveredWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(70));
        wagon.loadCargo(100);
        assertEquals(wagon.getCurrentCargoWeight(), 100);
    }

    public void testUnloadCargo() {
        CoveredWagon wagon = WagonFactory.createCoveredWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(70));
        wagon.loadCargo(100);
        wagon.unloadCargo();
        assertEquals(wagon.getCurrentCargoWeight(), 0);
    }
}