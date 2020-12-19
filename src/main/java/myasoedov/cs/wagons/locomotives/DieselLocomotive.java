package myasoedov.cs.wagons.locomotives;

import myasoedov.cs.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.storages.wagons.locomotives.DieselLocomotiveDBStorage;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

public class DieselLocomotive extends FuelLocomotive {

    private final static Storage<DieselLocomotive> storage = new DieselLocomotiveDBStorage(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);

    public DieselLocomotive(BigDecimal age, BigDecimal condition, BigDecimal volumeFuel, UUID id) {
        super(Integer.parseInt(Configs.WagonFillsProperties.getProperty("dieselWeight")),
                age, condition, Integer.parseInt(Configs.WagonFillsProperties.getProperty("dieselPower")),
                new BigDecimal(Configs.WagonFillsProperties.getProperty("dieselMaxSpeed")), id,
                new BigDecimal(Configs.WagonFillsProperties.getProperty("dieselMaxVolumeFuel")),
                new BigDecimal(Configs.WagonFillsProperties.getProperty("dieselConsumption")), volumeFuel);
    }

    public static DieselLocomotive save(DieselLocomotive wagon) throws SQLException {
        storage.save(wagon);
        return wagon;
    }
}
