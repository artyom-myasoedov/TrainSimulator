package myasoedov.cs.wagons.passengerWagons;

import myasoedov.cs.models.storages.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.storages.wagons.passenger.CoupeWagonDBStorage;
import myasoedov.cs.storages.wagons.passenger.SeatWagonDBStorage;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

public class CoupeWagon extends myasoedov.cs.models.abstractWagons.PassengerWagon {
    private final static int WEIGHT = 5000;
    private final static int NUMBER_OF_SEATS = 40;
    private final static Storage<CoupeWagon> storage = new CoupeWagonDBStorage<>(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);


    public CoupeWagon(BigDecimal age, BigDecimal condition, UUID id) {
        super(WEIGHT, age, condition, NUMBER_OF_SEATS, id);
    }

    public static CoupeWagon save(CoupeWagon wagon) throws SQLException {
        storage.save(wagon);
        return wagon;
    }
}
