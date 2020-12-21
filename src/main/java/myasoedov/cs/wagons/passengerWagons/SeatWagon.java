package myasoedov.cs.wagons.passengerWagons;

import myasoedov.cs.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.storages.wagons.passenger.SeatWagonDBStorage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

public class SeatWagon extends myasoedov.cs.models.abstractWagons.PassengerWagon implements Serializable {
    private final static Storage<SeatWagon> storage = new SeatWagonDBStorage(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);


    public SeatWagon(BigDecimal age, BigDecimal condition, UUID id) {
        super(Integer.parseInt(Configs.WagonFillsProperties.getProperty("seatWagonWeight")), age, condition,
                Integer.parseInt(Configs.WagonFillsProperties.getProperty("seatWagonNumberOfSeats")), id);
    }

    public static SeatWagon save(SeatWagon wagon) throws SQLException {
        storage.save(wagon);
        return wagon;
    }
}
