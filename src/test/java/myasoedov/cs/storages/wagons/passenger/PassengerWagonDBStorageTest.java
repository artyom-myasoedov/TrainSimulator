package myasoedov.cs.storages.wagons.passenger;

import junit.framework.TestCase;
import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.wagons.passengerWagons.CoupeWagon;
import myasoedov.cs.wagons.passengerWagons.RestaurantWagon;
import myasoedov.cs.wagons.passengerWagons.SeatWagon;
import myasoedov.cs.wagons.passengerWagons.SleepWagon;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

public class PassengerWagonDBStorageTest extends TestCase {

    CoupeWagon wagon1;
    SleepWagon wagon2;
    RestaurantWagon wagon3;
    SeatWagon wagon4;
    Storage<CoupeWagon> storage1;
    Storage<SleepWagon> storage2;
    Storage<RestaurantWagon> storage3;
    Storage<SeatWagon> storage4;

    public void setUp() {
        wagon1 = WagonFactory.createCoupeWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(90), UUID.fromString("cfabef2a-de4a-474f-b565-1dedd6a39215"));
        wagon2 = WagonFactory.createSleepWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(90), UUID.fromString("cfabef2a-de4a-474f-b565-1dedd6a31016"));
        wagon3 = WagonFactory.createRestaurantWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(90), UUID.fromString("cfabef2a-de4a-474f-b565-1dedd6a35017"));
        wagon4 = WagonFactory.createSeatWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(90), UUID.fromString("cfabef2a-de4a-474f-b565-1dedd6a39010"));
        storage1 = new CoupeWagonDBStorage(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);
        storage2 = new SleepWagonDBStorage(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);
        storage3 = new RestaurantWagonDBStorage(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);
        storage4 = new SeatWagonDBStorage(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);


    }

    public void testSave() throws SQLException {
        assertTrue(storage1.save(wagon1));
        assertTrue(storage2.save(wagon2));
        assertTrue(storage3.save(wagon3));
        assertTrue(storage4.save(wagon4));
    }

    public void testDelete() {
        assertTrue(storage1.delete(wagon1.getId()));
        assertTrue(storage2.delete(wagon2.getId()));
        assertTrue(storage3.delete(wagon3.getId()));
        assertTrue(storage4.delete(wagon4.getId()));
    }

    public void testGet() throws SQLException {
        storage1.save(wagon1);
        storage2.save(wagon2);
        storage3.save(wagon3);
        storage4.save(wagon4);

        CoupeWagon wagon11 = storage1.get(wagon1.getId());
        assertEquals(wagon1.getAge(), wagon11.getAge());
        assertEquals(wagon1.getCondition(), wagon11.getCondition());
        assertEquals(wagon1.getNumberInComposition(), wagon11.getNumberInComposition());
        assertEquals(wagon1.getTrainId(), wagon11.getTrainId());
        assertEquals(wagon1.getNumberOfPassengers(), wagon11.getNumberOfPassengers());

        SleepWagon wagon12 = storage2.get(wagon2.getId());
        assertEquals(wagon2.getAge(), wagon12.getAge());
        assertEquals(wagon2.getCondition(), wagon12.getCondition());
        assertEquals(wagon2.getNumberInComposition(), wagon12.getNumberInComposition());
        assertEquals(wagon2.getTrainId(), wagon12.getTrainId());
        assertEquals(wagon2.getNumberOfPassengers(), wagon12.getNumberOfPassengers());

        RestaurantWagon wagon13 = storage3.get(wagon3.getId());
        assertEquals(wagon3.getAge(), wagon13.getAge());
        assertEquals(wagon3.getCondition(), wagon13.getCondition());
        assertEquals(wagon3.getNumberInComposition(), wagon13.getNumberInComposition());
        assertEquals(wagon3.getTrainId(), wagon13.getTrainId());
        assertEquals(wagon3.getNumberOfPassengers(), wagon13.getNumberOfPassengers());

        SeatWagon wagon14 = storage4.get(wagon4.getId());
        assertEquals(wagon4.getAge(), wagon14.getAge());
        assertEquals(wagon4.getCondition(), wagon14.getCondition());
        assertEquals(wagon4.getNumberInComposition(), wagon14.getNumberInComposition());
        assertEquals(wagon4.getTrainId(), wagon14.getTrainId());
        assertEquals(wagon4.getNumberOfPassengers(), wagon14.getNumberOfPassengers());
    }
}