package myasoedov.cs.storages.wagons.passenger;


import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.models.storages.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.wagons.passengerWagons.CoupeWagon;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;

public class SleepWagonDBStorageTest {

    @Test
    public void testSave() throws SQLException {
        CoupeWagon wagon = WagonFactory.createCoupeWagon(BigDecimal.valueOf(10), BigDecimal.valueOf(90), 101L);
        Storage storage = new CoupeWagonDBStorage(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);
        //storage.save(wagon);
        CoupeWagon cp = (CoupeWagon) storage.get(wagon.getId());

    }
}