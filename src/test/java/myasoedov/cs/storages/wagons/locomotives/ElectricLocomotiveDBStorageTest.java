package myasoedov.cs.storages.wagons.locomotives;

import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.models.abstractWagons.Locomotive;
import myasoedov.cs.models.storages.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.wagons.locomotives.LocomotiveEngineConditions;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;

public class ElectricLocomotiveDBStorageTest {

    @Test
    public void testSave() throws SQLException {
        Locomotive locomotive = WagonFactory.createElectricLocomotive(BigDecimal.valueOf(10), BigDecimal.valueOf(29), LocomotiveEngineConditions.ENABLED, 100L);
        Storage storage = new ElectricLocomotiveDBStorage(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);
        storage.save(locomotive);
    }
}