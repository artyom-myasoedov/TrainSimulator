package myasoedov.cs.storages;


import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.models.abstractWagons.PassengerWagon;
import myasoedov.cs.models.abstractWagons.Wagon;
import myasoedov.cs.models.storages.Configs;
import myasoedov.cs.models.storages.DBStorage;
import myasoedov.cs.storages.train.PassengerTrainDBStorage;
import myasoedov.cs.storages.wagons.freight.RefrigeratorWagonDBStorage;
import myasoedov.cs.storages.wagons.passenger.CoupeWagonDBStorage;
import myasoedov.cs.trains.PassengerTrain;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Random;

public class TrainDBStorageTest {

    public static PassengerTrain<PassengerWagon> train;

    @Before
    public void prepareTrain() {
        train = new PassengerTrain<>(12L);
        train.addHeadWagon(WagonFactory.createCoupeWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90), 100L));
        train.addTailWagon(WagonFactory.createRestaurantWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90), 100L));
        train.addHeadWagon(WagonFactory.createSeatWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90), 100L));
        train.addLocomotive(WagonFactory.createSteamLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), BigDecimal.valueOf(30), 100L));
        train.addLocomotive(WagonFactory.createDieselLocomotive(BigDecimal.valueOf(2), BigDecimal.valueOf(90), BigDecimal.valueOf(30), 100L));
    }

    @Test
    public void testSave() throws SQLException {
        DBStorage storage = new PassengerTrainDBStorage(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);
        Wagon wagon = WagonFactory.createCoupeWagon(BigDecimal.valueOf(2), BigDecimal.valueOf(90), 100L);
        wagon.setTrainId(12L);
        DBStorage storage1 = new CoupeWagonDBStorage(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);
        //storage.save(train);
        //storage1.save(wagon);
        storage.delete(12L);

    }
}