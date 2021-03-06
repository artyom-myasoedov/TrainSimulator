package myasoedov.cs.factories;

import org.junit.Test;
import myasoedov.cs.wagons.locomotives.LocomotiveEngineConditions;
import myasoedov.cs.models.abstractWagons.Locomotive;
import myasoedov.cs.wagons.freightWagons.CoveredWagon;
import myasoedov.cs.wagons.freightWagons.PlatformWagon;
import myasoedov.cs.wagons.freightWagons.RefrigeratorWagon;
import myasoedov.cs.wagons.freightWagons.TankWagon;
import myasoedov.cs.wagons.locomotives.DieselLocomotive;
import myasoedov.cs.wagons.locomotives.ElectricLocomotive;
import myasoedov.cs.wagons.locomotives.SteamLocomotive;
import myasoedov.cs.wagons.passengerWagons.CoupeWagon;
import myasoedov.cs.wagons.passengerWagons.RestaurantWagon;
import myasoedov.cs.wagons.passengerWagons.SeatWagon;
import myasoedov.cs.wagons.passengerWagons.SleepWagon;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class WagonFactoryTest {

    @Test
    public void testCreateDieselLocomotive() {
        DieselLocomotive locomotive1 = new DieselLocomotive(BigDecimal.valueOf(7.4), BigDecimal.valueOf(100), BigDecimal.valueOf(40), UUID.randomUUID());
        DieselLocomotive locomotive2 = WagonFactory.createDieselLocomotive(BigDecimal.valueOf(7.4), BigDecimal.valueOf(100), BigDecimal.valueOf(40), UUID.randomUUID());
        assertEquals(locomotive1.getAge(), locomotive2.getAge());
        assertEquals(locomotive1.getMaxSpeed(), locomotive2.getMaxSpeed());
        assertEquals(locomotive1.getPower(), locomotive2.getPower());
        assertEquals(locomotive1.getWeight(), locomotive2.getWeight());
        assertEquals(locomotive1.getCondition(), locomotive2.getCondition());
        assertEquals(locomotive1.getVolumeFuel(), locomotive2.getVolumeFuel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateFailedDieselLocomotive() {
        Locomotive locomotive1 = WagonFactory.createDieselLocomotive(BigDecimal.valueOf(-12), BigDecimal.valueOf(20), BigDecimal.valueOf(1), UUID.randomUUID());
        Locomotive locomotive2 = WagonFactory.createDieselLocomotive(BigDecimal.valueOf(12), BigDecimal.valueOf(120), BigDecimal.valueOf(1), UUID.randomUUID());
        Locomotive locomotive3 = WagonFactory.createDieselLocomotive(BigDecimal.valueOf(12), BigDecimal.valueOf(20), BigDecimal.valueOf(200), UUID.randomUUID());
    }

    @Test
    public void testCreateElectricLocomotive() {
        ElectricLocomotive locomotive1 = new ElectricLocomotive(BigDecimal.valueOf(7.4), BigDecimal.valueOf(100), LocomotiveEngineConditions.DISABLED, UUID.randomUUID());
        ElectricLocomotive locomotive2 = WagonFactory.createElectricLocomotive(BigDecimal.valueOf(7.4), BigDecimal.valueOf(100), LocomotiveEngineConditions.DISABLED, UUID.randomUUID());
        assertEquals(locomotive1.getAge(), locomotive2.getAge());
        assertEquals(locomotive1.getMaxSpeed(), locomotive2.getMaxSpeed());
        assertEquals(locomotive1.getPower(), locomotive2.getPower());
        assertEquals(locomotive1.getWeight(), locomotive2.getWeight());
        assertEquals(locomotive1.getCondition(), locomotive2.getCondition());
        assertFalse(locomotive2.isPowerGridConnect());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateFailedElectricLocomotive() {
        ElectricLocomotive electricLocomotive1 = WagonFactory.createElectricLocomotive(BigDecimal.valueOf(-9), BigDecimal.valueOf(100), LocomotiveEngineConditions.DISABLED, UUID.randomUUID());
        ElectricLocomotive electricLocomotive2 = WagonFactory.createElectricLocomotive(BigDecimal.valueOf(9), BigDecimal.valueOf(101), LocomotiveEngineConditions.DISABLED, UUID.randomUUID());
    }

    @Test
    public void testCreateSteamLocomotive() {
        SteamLocomotive locomotive1 = new SteamLocomotive(BigDecimal.valueOf(7.4), BigDecimal.valueOf(100), BigDecimal.valueOf(40), UUID.randomUUID());
        SteamLocomotive locomotive2 = WagonFactory.createSteamLocomotive(BigDecimal.valueOf(7.4), BigDecimal.valueOf(100), BigDecimal.valueOf(40), UUID.randomUUID());
        assertEquals(locomotive1.getAge(), locomotive2.getAge());
        assertEquals(locomotive1.getMaxSpeed(), locomotive2.getMaxSpeed());
        assertEquals(locomotive1.getPower(), locomotive2.getPower());
        assertEquals(locomotive1.getWeight(), locomotive2.getWeight());
        assertEquals(locomotive1.getCondition(), locomotive2.getCondition());
        assertEquals(locomotive1.getVolumeFuel(), locomotive2.getVolumeFuel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateFailedSteamLocomotive() {
        Locomotive locomotive1 = WagonFactory.createSteamLocomotive(BigDecimal.valueOf(-12), BigDecimal.valueOf(20), BigDecimal.valueOf(1), UUID.randomUUID());
        Locomotive locomotive2 = WagonFactory.createSteamLocomotive(BigDecimal.valueOf(12), BigDecimal.valueOf(120), BigDecimal.valueOf(1), UUID.randomUUID());
        Locomotive locomotive3 = WagonFactory.createSteamLocomotive(BigDecimal.valueOf(12), BigDecimal.valueOf(20), BigDecimal.valueOf(200), UUID.randomUUID());
    }

    @Test
    public void testCreateCoveredWagon() {
        CoveredWagon wagon1 = new CoveredWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(20), UUID.randomUUID());
        CoveredWagon wagon2 = WagonFactory.createCoveredWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(20), UUID.randomUUID());
        assertEquals(wagon1.getAge(), wagon2.getAge());
        assertEquals(wagon1.getCondition(), wagon2.getCondition());
        assertEquals(wagon1.getCurrentCargoWeight(), wagon2.getCurrentCargoWeight());
        assertEquals(wagon1.getMaxCarrying(), wagon2.getMaxCarrying());
        assertEquals(wagon1.getIsOpened(), wagon2.getIsOpened());
        assertEquals(wagon1.getTotalWeight(), wagon2.getTotalWeight());
        assertEquals(wagon1.getWeight(), wagon2.getWeight());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateFailedCoveredWagon() {
        CoveredWagon wagon1 = WagonFactory.createCoveredWagon(BigDecimal.valueOf(-10), BigDecimal.valueOf(20), UUID.randomUUID());
        CoveredWagon wagon2 = WagonFactory.createCoveredWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(230), UUID.randomUUID());
    }

    @Test
    public void testCreatePlatformWagon() {
        PlatformWagon wagon1 = new PlatformWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(20), UUID.randomUUID());
        PlatformWagon wagon2 = WagonFactory.createPlatformWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(20), UUID.randomUUID());
        assertEquals(wagon1.getAge(), wagon2.getAge());
        assertEquals(wagon1.getCondition(), wagon2.getCondition());
        assertEquals(wagon1.getCurrentCargoWeight(), wagon2.getCurrentCargoWeight());
        assertEquals(wagon1.getMaxCarrying(), wagon2.getMaxCarrying());
        assertEquals(wagon1.getTotalWeight(), wagon2.getTotalWeight());
        assertEquals(wagon1.getWeight(), wagon2.getWeight());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateFailedPlatformWagon() {
        PlatformWagon wagon1 = WagonFactory.createPlatformWagon(BigDecimal.valueOf(-10), BigDecimal.valueOf(20), UUID.randomUUID());
        PlatformWagon wagon2 = WagonFactory.createPlatformWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(230), UUID.randomUUID());
    }

    @Test
    public void testCreateRefrigeratorWagon() {
        RefrigeratorWagon wagon1 = new RefrigeratorWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(20), UUID.randomUUID());
        RefrigeratorWagon wagon2 = WagonFactory.createRefrigeratorWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(20), UUID.randomUUID());
        assertEquals(wagon1.getAge(), wagon2.getAge());
        assertEquals(wagon1.getCondition(), wagon2.getCondition());
        assertEquals(wagon1.getCurrentCargoWeight(), wagon2.getCurrentCargoWeight());
        assertEquals(wagon1.getMaxCarrying(), wagon2.getMaxCarrying());
        assertEquals(wagon1.getTotalWeight(), wagon2.getTotalWeight());
        assertEquals(wagon1.getWeight(), wagon2.getWeight());
        assertEquals(wagon1.getCurrentTemperature(), wagon2.getCurrentTemperature());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateFailedRefrigeratorWagon() {
        RefrigeratorWagon wagon1 = WagonFactory.createRefrigeratorWagon(BigDecimal.valueOf(-10), BigDecimal.valueOf(20), UUID.randomUUID());
        RefrigeratorWagon wagon2 = WagonFactory.createRefrigeratorWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(230), UUID.randomUUID());
    }

    @Test
    public void testCreateTankWagon() {
        TankWagon wagon1 = new TankWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(20), UUID.randomUUID());
        TankWagon wagon2 = WagonFactory.createTankWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(20), UUID.randomUUID());
        assertEquals(wagon1.getAge(), wagon2.getAge());
        assertEquals(wagon1.getCondition(), wagon2.getCondition());
        assertEquals(wagon1.getCurrentCargoWeight(), wagon2.getCurrentCargoWeight());
        assertEquals(wagon1.getMaxCarrying(), wagon2.getMaxCarrying());
        assertEquals(wagon1.getTotalWeight(), wagon2.getTotalWeight());
        assertEquals(wagon1.getWeight(), wagon2.getWeight());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateFailedTankWagon() {
        TankWagon wagon1 = WagonFactory.createTankWagon(BigDecimal.valueOf(-10), BigDecimal.valueOf(20), UUID.randomUUID());
        TankWagon wagon2 = WagonFactory.createTankWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(230), UUID.randomUUID());

    }

    @Test
    public void testCreateCoupeWagon() {
        CoupeWagon wagon1 = new CoupeWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(20), UUID.randomUUID());
        CoupeWagon wagon2 = WagonFactory.createCoupeWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(20), UUID.randomUUID());
        assertEquals(wagon1.getAge(), wagon2.getAge());
        assertEquals(wagon1.getCondition(), wagon2.getCondition());
        assertEquals(wagon1.getNumberOfPassengers(), wagon2.getNumberOfPassengers());
        assertEquals(wagon1.getNumberOfSeats(), wagon2.getNumberOfSeats());
        assertEquals(wagon1.getWeight(), wagon2.getWeight());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateFailedCoupeWagon() {
        CoupeWagon wagon1 = WagonFactory.createCoupeWagon(BigDecimal.valueOf(-10), BigDecimal.valueOf(20), UUID.randomUUID());
        CoupeWagon wagon2 = WagonFactory.createCoupeWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(230), UUID.randomUUID());
    }

    @Test
    public void testCreateRestaurantWagon() {
        RestaurantWagon wagon1 = new RestaurantWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(20), UUID.randomUUID());
        RestaurantWagon wagon2 = WagonFactory.createRestaurantWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(20), UUID.randomUUID());
        assertEquals(wagon1.getAge(), wagon2.getAge());
        assertEquals(wagon1.getCondition(), wagon2.getCondition());
        assertEquals(wagon1.getNumberOfPassengers(), wagon2.getNumberOfPassengers());
        assertEquals(wagon1.getNumberOfSeats(), wagon2.getNumberOfSeats());
        assertEquals(wagon1.getWeight(), wagon2.getWeight());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateFailedRestaurantWagon() {
        RestaurantWagon wagon1 = WagonFactory.createRestaurantWagon(BigDecimal.valueOf(-10), BigDecimal.valueOf(20), UUID.randomUUID());
        RestaurantWagon wagon2 = WagonFactory.createRestaurantWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(230), UUID.randomUUID());
    }

    @Test
    public void testCreateSeatWagon() {
        SeatWagon wagon1 = new SeatWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(20), UUID.randomUUID());
        SeatWagon wagon2 = WagonFactory.createSeatWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(20), UUID.randomUUID());
        assertEquals(wagon1.getAge(), wagon2.getAge());
        assertEquals(wagon1.getCondition(), wagon2.getCondition());
        assertEquals(wagon1.getNumberOfPassengers(), wagon2.getNumberOfPassengers());
        assertEquals(wagon1.getNumberOfSeats(), wagon2.getNumberOfSeats());
        assertEquals(wagon1.getWeight(), wagon2.getWeight());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateFailedSeatWagon() {
        SeatWagon wagon1 = WagonFactory.createSeatWagon(BigDecimal.valueOf(-10), BigDecimal.valueOf(20), UUID.randomUUID());
        SeatWagon wagon2 = WagonFactory.createSeatWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(230), UUID.randomUUID());
    }

    @Test
    public void testCreateSleepWagon() {
        SleepWagon wagon1 = new SleepWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(20), UUID.randomUUID());
        SleepWagon wagon2 = WagonFactory.createSleepWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(20), UUID.randomUUID());
        assertEquals(wagon1.getAge(), wagon2.getAge());
        assertEquals(wagon1.getCondition(), wagon2.getCondition());
        assertEquals(wagon1.getNumberOfPassengers(), wagon2.getNumberOfPassengers());
        assertEquals(wagon1.getNumberOfSeats(), wagon2.getNumberOfSeats());
        assertEquals(wagon1.getWeight(), wagon2.getWeight());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateFailedSleepWagon() {
        SleepWagon wagon1 = WagonFactory.createSleepWagon(BigDecimal.valueOf(-10), BigDecimal.valueOf(20), UUID.randomUUID());
        SleepWagon wagon2 = WagonFactory.createSleepWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(230), UUID.randomUUID());
    }
}