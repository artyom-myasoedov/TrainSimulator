package myasoedov.cs.storages.train;

import junit.framework.TestCase;
import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.models.abstractWagons.FreightWagon;
import myasoedov.cs.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.trains.FreightTrain;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

public class FreightTrainDBStorageTest extends TestCase {

    FreightTrain train;
    Storage<FreightTrain> storage;

    public void setUp() {
        train = new FreightTrain(UUID.fromString("deea44c7-a180-4898-9527-58db0ed34674"));
        train.addHeadWagon(WagonFactory.createRefrigeratorWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(100), UUID.fromString("deea44c7-a180-4898-9527-58db0ed34621")));
        train.addHeadWagon(WagonFactory.createCoveredWagon(BigDecimal.valueOf(12), BigDecimal.valueOf(80), UUID.fromString("deea44c7-a180-4898-9527-58db0ed34620")));
        train.addHeadWagon(WagonFactory.createPlatformWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90), UUID.fromString("deea44c7-a180-4898-9527-58db0ed34622")));
        train.addLocomotive(WagonFactory.createSteamLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), BigDecimal.valueOf(30), UUID.fromString("deea44c7-a180-4898-9527-58db0ed34623")));
        train.addLocomotive(WagonFactory.createDieselLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), BigDecimal.valueOf(30), UUID.fromString("deea44c7-a180-4898-9527-58db0ed34624")));

        storage = new FreightTrainDBStorage(Configs.DBProperties.getProperty("db.url"), Configs.DBProperties.getProperty("db.user"), Configs.DBProperties.getProperty("db.parol"));

    }

    public void testSave() throws SQLException {
        assertTrue(storage.save(train));
    }

    public void testDelete() {
        try {
            assertTrue(storage.delete(train.getId()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void testGet() throws SQLException {
        storage.save(train);
        FreightTrain train1 = storage.get(train.getId());
        assertEquals(train.getLocomotivesSize(), train1.getLocomotivesSize());
        assertEquals(train.getTotalWeight(), train1.getTotalWeight());
        assertEquals(train.getWagonsSize(), train1.getWagonsSize());

    }
}