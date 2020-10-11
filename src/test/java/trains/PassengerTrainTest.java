package trains;

import factories.WagonFactory;
import junit.framework.TestCase;
import wagons.Conditions;

import java.math.BigDecimal;

public class PassengerTrainTest extends TestCase {

    public void testGetTotalWagonsWeight() {
        PassengerTrain train = new PassengerTrain();
        train.addHeadWagon(WagonFactory.createCoupeWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90)));
        train.addHeadWagon(WagonFactory.createRestaurantWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90)));
        train.addHeadWagon(WagonFactory.createSeatWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90)));
        assertEquals(train.getTotalWeight(), 19000);
    }

    public void testGetTotalPower() {
        PassengerTrain train = new PassengerTrain();
        train.addHeadWagon(WagonFactory.createCoupeWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90)));
        train.addHeadWagon(WagonFactory.createRestaurantWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90)));
        train.addHeadWagon(WagonFactory.createSeatWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90)));
        train.addLocomotive(WagonFactory.createSteamLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), BigDecimal.valueOf(30)));
        train.addLocomotive(WagonFactory.createSteamLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), BigDecimal.valueOf(30)));
        train.addLocomotive(WagonFactory.createElectricLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), Conditions.ENABLED));
        assertEquals(train.getTotalPower(), 220000);
    }

    public void testGetMoving() {
        PassengerTrain train = new PassengerTrain();
        train.addHeadWagon(WagonFactory.createCoupeWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90)));
        train.addHeadWagon(WagonFactory.createRestaurantWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90)));
        train.addHeadWagon(WagonFactory.createSeatWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90)));
        train.addLocomotive(WagonFactory.createSteamLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), BigDecimal.valueOf(30)));
        train.addLocomotive(WagonFactory.createSteamLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), BigDecimal.valueOf(30)));
        train.addLocomotive(WagonFactory.createElectricLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), Conditions.ENABLED));
        assertEquals(train.getMoving(), Movings.STOPPED);
        train.connectLocomotivesToHead();
        train.moveForward(30);
        assertEquals(train.getMoving(), Movings.FORWARD);
    }

    public void testConnectLocomotivesToHead() {
        PassengerTrain train = new PassengerTrain();
        train.addHeadWagon(WagonFactory.createCoupeWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90)));
        train.addHeadWagon(WagonFactory.createRestaurantWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90)));
        train.addHeadWagon(WagonFactory.createSeatWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90)));
        train.addLocomotive(WagonFactory.createSteamLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), BigDecimal.valueOf(30)));
        train.addLocomotive(WagonFactory.createSteamLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), BigDecimal.valueOf(30)));
        train.addLocomotive(WagonFactory.createElectricLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), Conditions.ENABLED));
        train.connectLocomotivesToHead();
        assertTrue(train.areLocomotivesInHead());
    }

    public void testConnectLocomotivesToTail() {
        PassengerTrain train = new PassengerTrain();
        train.addHeadWagon(WagonFactory.createCoupeWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90)));
        train.addHeadWagon(WagonFactory.createRestaurantWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90)));
        train.addHeadWagon(WagonFactory.createSeatWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90)));
        train.addLocomotive(WagonFactory.createSteamLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), BigDecimal.valueOf(30)));
        train.addLocomotive(WagonFactory.createSteamLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), BigDecimal.valueOf(30)));
        train.addLocomotive(WagonFactory.createElectricLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), Conditions.ENABLED));
        train.connectLocomotivesToTail();
        assertTrue(train.areLocomotivesInTail());
    }

    public void testDisconnectLocomotives() {
        PassengerTrain train = new PassengerTrain();
        train.addHeadWagon(WagonFactory.createCoupeWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90)));
        train.addHeadWagon(WagonFactory.createRestaurantWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90)));
        train.addHeadWagon(WagonFactory.createSeatWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90)));
        train.addLocomotive(WagonFactory.createSteamLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), BigDecimal.valueOf(30)));
        train.addLocomotive(WagonFactory.createSteamLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), BigDecimal.valueOf(30)));
        train.addLocomotive(WagonFactory.createElectricLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), Conditions.ENABLED));
        train.connectLocomotivesToTail();
        assertTrue(train.areLocomotivesInTail());
        train.disconnectLocomotives();
        assertFalse(train.areLocomotivesInTail());
    }

    public void testAddLocomotive() {
        PassengerTrain train = new PassengerTrain();
        train.addLocomotive(WagonFactory.createSteamLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), BigDecimal.valueOf(30)));
        assertEquals(train.getLocomotivesSize(), 1);
    }

    public void testMoveForward() {
        PassengerTrain train = new PassengerTrain();
        train.addLocomotive(WagonFactory.createSteamLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), BigDecimal.valueOf(30)));
        train.connectLocomotivesToTail();
        train.moveForward(11);
        assertEquals(train.getMoving(), Movings.FORWARD);
    }

    public void testMoveBehind() {
        PassengerTrain train = new PassengerTrain();
        train.addLocomotive(WagonFactory.createSteamLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), BigDecimal.valueOf(30)));
        train.connectLocomotivesToTail();
        train.moveBehind(11);
        assertEquals(train.getMoving(), Movings.BEHIND);
    }

    public void testStopMoving() {
        PassengerTrain train = new PassengerTrain();
        train.addLocomotive(WagonFactory.createSteamLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), BigDecimal.valueOf(30)));
        train.connectLocomotivesToTail();
        train.moveBehind(11);
        assertEquals(train.getMoving(), Movings.BEHIND);
        train.stopMoving();
        assertEquals(train.getMoving(), Movings.STOPPED);
    }

    public void testGetTotalNumberOfSeats() {
        PassengerTrain train = new PassengerTrain();
        train.addHeadWagon(WagonFactory.createCoupeWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90)));
        train.addHeadWagon(WagonFactory.createRestaurantWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90)));
        train.addHeadWagon(WagonFactory.createSeatWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90)));
        assertEquals(train.getTotalNumberOfSeats(), 100);
    }

    public void testGetTotalNumberOfPassengers() {
        PassengerTrain train = new PassengerTrain();
        train.addHeadWagon(WagonFactory.createCoupeWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90)));
        train.addHeadWagon(WagonFactory.createRestaurantWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90)));
        train.addHeadWagon(WagonFactory.createSeatWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90)));
        train.getWagon(0).addPassengers(20);
        train.getWagon(1).addPassengers(18);
        assertEquals(train.getTotalNumberOfPassengers(), 20);
    }
}