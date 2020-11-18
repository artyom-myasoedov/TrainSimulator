package myasoedov.cs.wagons.locomotives;

import myasoedov.cs.models.storages.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.storages.wagons.locomotives.DieselLocomotiveDBStorage;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

public class DieselLocomotive extends FuelLocomotive {
    private static final BigDecimal MAX_VOLUME_FUEL = BigDecimal.valueOf(100);
    private static final BigDecimal CONSUMPTION = BigDecimal.valueOf(1.5);
    private final static int WEIGHT = 7500;
    private final static int POWER = 80000;
    private final static BigDecimal MAX_SPEED = BigDecimal.valueOf(80);
    private final static Storage<DieselLocomotive> storage = new DieselLocomotiveDBStorage<>(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);


    public DieselLocomotive(BigDecimal age, BigDecimal condition, BigDecimal volumeFuel, UUID id) {
        super(WEIGHT, age, condition, POWER, MAX_SPEED, id, MAX_VOLUME_FUEL, CONSUMPTION, volumeFuel);
    }

    public static DieselLocomotive save(DieselLocomotive wagon) throws SQLException {
        storage.save(wagon);
        return wagon;
    }
}
