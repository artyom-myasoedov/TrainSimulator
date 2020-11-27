package myasoedov.cs.storages.wagons.locomotives;

import junit.framework.TestCase;
import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.wagons.locomotives.ElectricLocomotive;
import myasoedov.cs.wagons.locomotives.LocomotiveEngineConditions;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

public class ElectricLocomotiveDBStorageTest extends TestCase {

    ElectricLocomotive locomotive;
    Storage<ElectricLocomotive> storage;
    public void setUp() {
        locomotive = WagonFactory.createElectricLocomotive(BigDecimal.valueOf(10), BigDecimal.valueOf(100), LocomotiveEngineConditions.ENABLED, UUID.fromString("cfabef2a-de4a-474f-b565-1dedd6a39012"));
        storage = new ElectricLocomotiveDBStorage<>(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);

    }

    public void testSave() throws SQLException {
        storage.save(locomotive);
    }

    public void testDelete() {
        storage.delete(locomotive.getId());
    }

    public void testGet() throws SQLException {
        storage.save(locomotive);

        ElectricLocomotive wagon2 = storage.get(locomotive.getId());
        assertEquals(locomotive.getAge(), wagon2.getAge());
        assertEquals(locomotive.getCondition(), wagon2.getCondition());
        assertEquals(locomotive.getNumberInComposition(), wagon2.getNumberInComposition());
        assertEquals(locomotive.getTrainId(), wagon2.getTrainId());
        assertEquals(locomotive.isPowerGridConnect(), wagon2.isPowerGridConnect());
    }
}