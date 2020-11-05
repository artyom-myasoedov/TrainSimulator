package myasoedov.cs.storages.wagons.locomotives;

import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.models.abstractWagons.Locomotive;
import myasoedov.cs.models.storages.Configs;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;

public class DieselLocomotiveDBStorageTest {

    @Test
    public void testSave() throws SQLException {
        Locomotive locomotive = WagonFactory.createDieselLocomotive(BigDecimal.valueOf(10), BigDecimal.valueOf(100), BigDecimal.valueOf(20), 10L);
        DieselLocomotiveDBStorage storage = new DieselLocomotiveDBStorage(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);
        storage.save(locomotive);
    }
}