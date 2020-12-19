package myasoedov.cs.storages.wagons.locomotives;

import junit.framework.TestCase;
import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.wagons.locomotives.DieselLocomotive;
import myasoedov.cs.wagons.locomotives.SteamLocomotive;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

public class FuelLocomotiveDBStorageTest extends TestCase {

    DieselLocomotive locomotive;
    Storage<DieselLocomotive> storage;
    SteamLocomotive locomotive1;
    Storage<SteamLocomotive> storage1;
    public void setUp() {
        locomotive = WagonFactory.createDieselLocomotive(BigDecimal.valueOf(10), BigDecimal.valueOf(100), BigDecimal.valueOf(20), UUID.fromString("cfabef2a-de4a-474f-b565-1dedd6a39014"));
        storage = new DieselLocomotiveDBStorage(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);
        locomotive1 = WagonFactory.createSteamLocomotive(BigDecimal.valueOf(10), BigDecimal.valueOf(100), BigDecimal.valueOf(20), UUID.fromString("cfabef2a-de4a-474f-b565-1dedd6a39013"));
        storage1 = new SteamLocomotiveDBStorage(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);

    }

    public void testSave() throws SQLException {
        assertTrue(storage.save(locomotive));
        assertTrue(storage1.save(locomotive1));
    }

    public void testDelete() {
        assertTrue(storage.delete(locomotive.getId()));
        assertTrue(storage1.delete(locomotive1.getId()));
    }

    public void testGet() throws SQLException {
        storage.save(locomotive);
        storage1.save(locomotive1);


        DieselLocomotive wagon2 = storage.get(locomotive.getId());
        assertEquals(locomotive.getAge(), wagon2.getAge());
        assertEquals(locomotive.getCondition(), wagon2.getCondition());
        assertEquals(locomotive.getNumberInComposition(), wagon2.getNumberInComposition());
        assertEquals(locomotive.getTrainId(), wagon2.getTrainId());
        assertEquals(locomotive.getVolumeFuel(), wagon2.getVolumeFuel());

        SteamLocomotive wagon1 = storage1.get(locomotive1.getId());
        assertEquals(locomotive1.getAge(), wagon1.getAge());
        assertEquals(locomotive1.getCondition(), wagon1.getCondition());
        assertEquals(locomotive1.getNumberInComposition(), wagon1.getNumberInComposition());
        assertEquals(locomotive1.getTrainId(), wagon1.getTrainId());
        assertEquals(locomotive1.getVolumeFuel(), wagon1.getVolumeFuel());
    }
}