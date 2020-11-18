package myasoedov.cs.wagons.locomotives;

import myasoedov.cs.models.abstractWagons.Locomotive;
import myasoedov.cs.models.storages.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.storages.wagons.locomotives.SteamLocomotiveDBStorage;
import myasoedov.cs.storages.wagons.passenger.CoupeWagonDBStorage;
import myasoedov.cs.wagons.passengerWagons.CoupeWagon;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

public class SteamLocomotive extends FuelLocomotive {
    private static final BigDecimal MAX_VOLUME_FUEL = BigDecimal.valueOf(120);
    private static final BigDecimal CONSUMPTION = BigDecimal.valueOf(2);
    private final static int WEIGHT = 9000;
    private final static int POWER = 60000;
    private final static BigDecimal MAX_SPEED = BigDecimal.valueOf(60);
    private final static Storage<SteamLocomotive> storage = new SteamLocomotiveDBStorage<>(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);


    public SteamLocomotive(BigDecimal age, BigDecimal condition, BigDecimal volumeFuel, UUID id) {
        super(WEIGHT, age, condition, POWER, MAX_SPEED, id, MAX_VOLUME_FUEL, CONSUMPTION, volumeFuel);
    }

    public static SteamLocomotive save(SteamLocomotive wagon) throws SQLException {
        storage.save(wagon);
        return wagon;
    }
}
