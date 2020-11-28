package myasoedov.cs.storages.wagons.freight;

import junit.framework.TestCase;
import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.wagons.freightWagons.CoveredWagon;
import myasoedov.cs.wagons.freightWagons.TankWagon;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

public class OpeningWagonDBStorageTest extends TestCase {

    CoveredWagon wagon;
    Storage<CoveredWagon> storage;
    TankWagon wagon1;
    Storage<TankWagon> storage1;
    public void setUp() {
        wagon = WagonFactory.createCoveredWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(10), UUID.fromString("deea44c7-a180-4898-9527-58db0ed34683"));
        wagon.openWagon();
        storage = new CoveredWagonDBStorage<>(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);
        wagon1 = WagonFactory.createTankWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(10), UUID.fromString("deea44c7-a180-4898-9527-58db0ed34684"));
        storage1 = new TankWagonDBStorage<>(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);

    }



    public void testSave() throws SQLException {
        assertTrue(storage1.save(wagon1));
        assertTrue(storage.save(wagon));
    }

    public void testDelete() {
        assertTrue(storage1.delete(wagon1.getId()));
        assertTrue(storage.delete(wagon.getId()));
    }

    public void testGet() throws SQLException {
        storage.save(wagon);
        CoveredWagon wagon2 = storage.get(wagon.getId());
        assertEquals(wagon.getAge(), wagon2.getAge());
        assertEquals(wagon.getCondition(), wagon2.getCondition());
        assertEquals(wagon.getNumberInComposition(), wagon2.getNumberInComposition());
        assertEquals(wagon.getTrainId(), wagon2.getTrainId());
        assertEquals(wagon.getIsOpened(), wagon2.getIsOpened());

        storage1.save(wagon1);
        TankWagon wagon3 = storage1.get(wagon1.getId());
        assertEquals(wagon1.getAge(), wagon3.getAge());
        assertEquals(wagon1.getCondition(), wagon3.getCondition());
        assertEquals(wagon1.getNumberInComposition(), wagon3.getNumberInComposition());
        assertEquals(wagon1.getTrainId(), wagon3.getTrainId());
        assertEquals(wagon1.getIsOpened(), wagon3.getIsOpened());


    }
}