package myasoedov.cs.wagons.passengerWagons;

import myasoedov.cs.models.storages.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.storages.wagons.passenger.SeatWagonDBStorage;
import myasoedov.cs.storages.wagons.passenger.SleepWagonDBStorage;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

public class SeatWagon extends myasoedov.cs.models.abstractWagons.PassengerWagon {
    private final static int WEIGHT = 6000;
    private final static int NUMBER_OF_SEATS = 60;
    private final static Storage<SeatWagon> storage = new SeatWagonDBStorage<>(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);


    public SeatWagon(BigDecimal age, BigDecimal condition, UUID id) {
        super(WEIGHT, age, condition, NUMBER_OF_SEATS, id);
    }

    public static SeatWagon save(SeatWagon wagon) throws SQLException {
        storage.save(wagon);
        return wagon;
    }
}
