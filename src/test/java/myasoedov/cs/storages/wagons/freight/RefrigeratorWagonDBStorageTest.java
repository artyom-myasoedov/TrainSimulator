package myasoedov.cs.storages.wagons.freight;


import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.models.abstractWagons.Wagon;
import myasoedov.cs.models.storages.Configs;
import myasoedov.cs.models.storages.Storage;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;

public class RefrigeratorWagonDBStorageTest {

    @Test
    public void testSave() throws SQLException {
        Wagon wagon = WagonFactory.createRefrigeratorWagon(BigDecimal.valueOf(12), BigDecimal.valueOf(33), 100L);
        Storage storage = new RefrigeratorWagonDBStorage(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);
        storage.delete(wagon.getId());
    }
}