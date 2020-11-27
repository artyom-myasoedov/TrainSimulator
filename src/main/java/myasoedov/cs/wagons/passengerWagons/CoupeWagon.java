package myasoedov.cs.wagons.passengerWagons;

import myasoedov.cs.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.storages.wagons.passenger.CoupeWagonDBStorage;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

public class CoupeWagon extends myasoedov.cs.models.abstractWagons.PassengerWagon {
    private final static Storage<CoupeWagon> storage = new CoupeWagonDBStorage<>(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);


    public CoupeWagon(BigDecimal age, BigDecimal condition, UUID id) {
        super(Integer.parseInt(Configs.WagonFillsProperties.getProperty("coupeWagonWeight")), age, condition,
                Integer.parseInt(Configs.WagonFillsProperties.getProperty("coupeNumberOfSeats")), id);
    }

    public static CoupeWagon save(CoupeWagon wagon) throws SQLException {
        storage.save(wagon);
        return wagon;
    }
}
