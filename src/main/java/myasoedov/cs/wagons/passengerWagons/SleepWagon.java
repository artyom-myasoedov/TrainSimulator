package myasoedov.cs.wagons.passengerWagons;

import myasoedov.cs.models.storages.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.storages.wagons.passenger.SleepWagonDBStorage;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

public class SleepWagon extends myasoedov.cs.models.abstractWagons.PassengerWagon {
    private final static int WEIGHT = 4500;
    private final static int NUMBER_OF_SEATS = 20;
    private final static Storage<SleepWagon> storage = new SleepWagonDBStorage<>(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);
    public SleepWagon(BigDecimal age, BigDecimal condition, UUID id) {
        super(WEIGHT, age, condition, NUMBER_OF_SEATS, id);
    }

    public static SleepWagon save(SleepWagon wagon) throws SQLException {
        storage.save(wagon);
        return wagon;
    }
}
