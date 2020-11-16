package myasoedov.cs.trains;

import myasoedov.cs.factories.WagonFactory;
import org.junit.Before;
import org.junit.Test;
import myasoedov.cs.wagons.locomotives.LocomotiveEngineConditions;
import myasoedov.cs.models.abstractWagons.PassengerWagon;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.*;

public class PassengerTrainTest {

    public static PassengerTrain<PassengerWagon> train;

    @Before
    public void prepareTrain() {
        train = new PassengerTrain<>(UUID.randomUUID());
        train.addHeadWagon(WagonFactory.createCoupeWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90), UUID.randomUUID()));
        train.addTailWagon(WagonFactory.createRestaurantWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90), UUID.randomUUID()));
        train.addHeadWagon(WagonFactory.createSeatWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90), UUID.randomUUID()));
        train.addLocomotive(WagonFactory.createSteamLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), BigDecimal.valueOf(30), UUID.randomUUID()));
        train.addLocomotive(WagonFactory.createDieselLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), BigDecimal.valueOf(30), UUID.randomUUID()));
    }

    @Test
    public void testGetTotalWagonsWeight() {
        assertEquals(train.getTotalWeight(), 35500);
    }

    @Test
    public void testGetTotalPower() {
        assertEquals(train.getTotalPower(), 140000);
    }

    @Test
    public void testGetMoving() {
        assertEquals(train.getMoving(), Movings.STOPPED);
        train.connectLocomotivesToHead();
        train.moveForward(30);
        assertEquals(train.getMoving(), Movings.FORWARD);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMovingException() {
        assertEquals(train.getMoving(), Movings.STOPPED);
        train.moveForward(30);
        assertEquals(train.getMoving(), Movings.FORWARD);
    }

    @Test
    public void testConnectLocomotivesToHead() {
        train.addLocomotive(WagonFactory.createElectricLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), LocomotiveEngineConditions.ENABLED, UUID.randomUUID()));
        train.connectLocomotivesToHead();
        assertTrue(train.areLocomotivesInHead());
    }

    @Test
    public void testConnectLocomotivesToTail() {
        train.connectLocomotivesToTail();
        assertTrue(train.areLocomotivesInTail());
    }

    @Test
    public void testDisconnectLocomotives() {
        train.connectLocomotivesToTail();
        assertTrue(train.areLocomotivesInTail());
        train.disconnectLocomotives();
        assertFalse(train.areLocomotivesInTail());
    }

    @Test
    public void testAddLocomotive() {
        train.addLocomotive(WagonFactory.createSteamLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), BigDecimal.valueOf(30), UUID.randomUUID()));
        assertEquals(train.getLocomotivesSize(), 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddLocomotiveException() {
        train.addLocomotive(WagonFactory.createSteamLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), BigDecimal.valueOf(30), UUID.randomUUID()));
        train.addLocomotive(WagonFactory.createSteamLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), BigDecimal.valueOf(30), UUID.randomUUID()));
    }

    @Test
    public void testMoveForward() {
        train.connectLocomotivesToTail();
        train.moveForward(11);
        assertEquals(train.getMoving(), Movings.FORWARD);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMoveForwardException() {
        train.unhookLocomotive();
        train.connectLocomotivesToTail();
        for (int i = 0; i < 20; i++) {
            train.addTailWagon(WagonFactory.createRestaurantWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90), UUID.randomUUID()));
        }
        train.moveForward(11);
    }

    @Test
    public void testMoveBehind() {
        train.connectLocomotivesToTail();
        train.moveBehind(11);
        assertEquals(train.getMoving(), Movings.BEHIND);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMoveBehindException() {
        train.unhookLocomotive();
        train.connectLocomotivesToTail();
        for (int i = 0; i < 20; i++) {
            train.addTailWagon(WagonFactory.createRestaurantWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90), UUID.randomUUID()));
        }
        train.moveBehind(11);
    }

    @Test
    public void testStopMoving() {
        train.connectLocomotivesToTail();
        train.moveBehind(11);
        assertEquals(train.getMoving(), Movings.BEHIND);
        train.stopMoving();
        assertEquals(train.getMoving(), Movings.STOPPED);
    }

    @Test
    public void testGetTotalNumberOfSeats() {
        assertEquals(train.getTotalNumberOfSeats(), 100L);
    }

    @Test
    public void testGetTotalNumberOfPassengers() {
        train.getWagon(0).addPassengers(20);
        train.getWagon(1).addPassengers(18);
        assertEquals(train.getTotalNumberOfPassengers(), 38);
    }
}