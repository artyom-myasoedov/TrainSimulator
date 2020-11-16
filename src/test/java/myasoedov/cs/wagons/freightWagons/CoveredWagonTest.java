package myasoedov.cs.wagons.freightWagons;

import myasoedov.cs.factories.WagonFactory;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.*;

public class CoveredWagonTest {

    static CoveredWagon wagon;

    @Before
    public  void createWagon() {
        wagon = WagonFactory.createCoveredWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(70), UUID.randomUUID());
    }

    @Test
    public void testGetIsOpened() {
        assertFalse(wagon.getIsOpened());
        wagon.openWagon();
        assertTrue(wagon.getIsOpened());
        wagon.closeWagon();
        assertFalse(wagon.getIsOpened());
    }

    @Test
    public void testGetTotalWeight() {
        assertEquals(wagon.getTotalWeight(), 2500);
    }

    @Test
    public void testSetAge() {
        wagon.setAge(BigDecimal.valueOf(22));
        assertEquals(wagon.getAge(), BigDecimal.valueOf(22));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetAgeException() {
        wagon.setAge(BigDecimal.valueOf(-1));
    }

    @Test
    public void testRepair() {
        wagon.repair(100);
        assertEquals(wagon.getCondition(), BigDecimal.valueOf(100));
    }

    @Test
    public void testFullRepair() {
        wagon.fullRepair();
        assertEquals(wagon.getCondition(), BigDecimal.valueOf(100));
    }

    @Test
    public void testLoadCargo() {
        wagon.loadCargo(100);
        assertEquals(wagon.getCurrentCargoWeight(), 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLoadCargoException() {
        wagon.loadCargo(10000);
    }

    @Test
    public void testUnloadCargo() {
        wagon.loadCargo(100);
        wagon.unloadCargo();
        assertEquals(wagon.getCurrentCargoWeight(), 0);
    }
}