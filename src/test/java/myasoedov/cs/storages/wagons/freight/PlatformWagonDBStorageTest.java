package myasoedov.cs.storages.wagons.freight;

import junit.framework.TestCase;
import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.models.storages.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.wagons.freightWagons.PlatformWagon;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

public class PlatformWagonDBStorageTest extends TestCase {

    PlatformWagon wagon;
    Storage<PlatformWagon> storage;

    public void setUp() {
        wagon = WagonFactory.createPlatformWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(10), UUID.randomUUID());
        storage = new PlatformWagonDBStorage<>(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);
    }

    public void testSave() throws SQLException {
        storage.save(wagon);
    }

    public void testDelete() {
        storage.delete(wagon.getId());
    }

    public void testGet() throws SQLException {
        storage.save(wagon);
        PlatformWagon wagon1 = storage.get(wagon.getId());
        assertEquals(wagon.getAge(), wagon1.getAge());
        assertEquals(wagon.getCondition(), wagon1.getCondition());
        assertEquals(wagon.getNumberInComposition(), wagon1.getNumberInComposition());
        assertEquals(wagon.getTrainId(), wagon1.getTrainId());
    }
}