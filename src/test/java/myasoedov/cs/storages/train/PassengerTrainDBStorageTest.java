package myasoedov.cs.storages.train;

import junit.framework.TestCase;
import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.models.abstractWagons.PassengerWagon;
import myasoedov.cs.models.storages.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.trains.PassengerTrain;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

public class PassengerTrainDBStorageTest extends TestCase {


    PassengerTrain<PassengerWagon> train;
    Storage<PassengerTrain<PassengerWagon>> storage;
    public void setUp() {
        train = new PassengerTrain<>(UUID.fromString("deea44c7-a180-4898-9527-58db0ed37583"));
        train.addHeadWagon(WagonFactory.createCoupeWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(100), UUID.fromString("deea44c7-a180-4898-9527-58db0ed34790")));
        train.addHeadWagon(WagonFactory.createSeatWagon(BigDecimal.valueOf(12), BigDecimal.valueOf(80), UUID.fromString("deea44c7-a180-4898-9527-58db0ed34791")));
        train.addHeadWagon(WagonFactory.createSleepWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90), UUID.fromString("deea44c7-a180-4898-9527-58db0ed34792")));
        train.addLocomotive(WagonFactory.createSteamLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), BigDecimal.valueOf(30), UUID.fromString("deea44c7-a180-4898-9527-58db0ed34793")));
        train.addLocomotive(WagonFactory.createDieselLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), BigDecimal.valueOf(30), UUID.fromString("deea44c7-a180-4898-9527-58db0ed34794")));

        storage = new PassengerTrainDBStorage<>(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);

    }

    public void testSave() throws SQLException {
        storage.save(train);
    }

    public void testDelete() {
        storage.delete(train.getId());
    }

    public void testGet() throws SQLException {
        storage.save(train);
        PassengerTrain<PassengerWagon> train1 = storage.get(train.getId());
        assertEquals(train.getLocomotivesSize(), train1.getLocomotivesSize());
        assertEquals(train.getTotalWeight(), train1.getTotalWeight());
        assertEquals(train.getWagonsSize(), train1.getWagonsSize());

    }
}