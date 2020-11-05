package myasoedov.cs.storages.wagons.passenger;


import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.models.storages.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.wagons.passengerWagons.SleepWagon;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;

public class SleepWagonDBStorageTest {

    @Test
    public void testSave() throws SQLException {
        SleepWagon sleepWagon = WagonFactory.createSleepWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(90), 101L);
        Storage storage = new SleepWagonDBStorage(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);
        storage.save(sleepWagon);
    }
}