package myasoedov.cs.storages.wagons.freight;

import junit.framework.TestCase;
import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.wagons.freightWagons.RefrigeratorWagon;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

public class RefrigeratorWagonDBStorageTest extends TestCase {

    RefrigeratorWagon wagon;
    Storage<RefrigeratorWagon> storage;

    public void setUp() {
        wagon = WagonFactory.createRefrigeratorWagon(BigDecimal.valueOf(12), BigDecimal.valueOf(33), UUID.fromString("cfabef2a-de4a-474f-b565-1dedd6a39011"));

        storage = new RefrigeratorWagonDBStorage(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);

    }



    public void testSave() throws SQLException {
        assertTrue(storage.save(wagon));
    }

    public void testDelete() {
        assertTrue(storage.delete(wagon.getId()));
    }

    public void testGet() throws SQLException {
        storage.save(wagon);
        RefrigeratorWagon wagon2 = storage.get(wagon.getId());
        assertEquals(wagon.getAge(), wagon2.getAge());
        assertEquals(wagon.getCondition(), wagon2.getCondition());
        assertEquals(wagon.getNumberInComposition(), wagon2.getNumberInComposition());
        assertEquals(wagon.getTrainId(), wagon2.getTrainId());
        assertEquals(wagon.getIsOpened(), wagon2.getIsOpened());
        assertEquals(wagon.getCurrentTemperature(), wagon2.getCurrentTemperature());

    }
}

