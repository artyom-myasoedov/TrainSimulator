package myasoedov.cs.wagons.locomotives;

import myasoedov.cs.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.storages.wagons.locomotives.SteamLocomotiveDBStorage;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

public class SteamLocomotive extends FuelLocomotive {

    private final static Storage<SteamLocomotive> storage = new SteamLocomotiveDBStorage(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);

    public SteamLocomotive(BigDecimal age, BigDecimal condition, BigDecimal volumeFuel, UUID id) {
        super(Integer.parseInt(Configs.WagonFillsProperties.getProperty("steamWeight")),
                age, condition, Integer.parseInt(Configs.WagonFillsProperties.getProperty("steamPower")),
                new BigDecimal(Configs.WagonFillsProperties.getProperty("steamMaxSpeed")), id,
                new BigDecimal(Configs.WagonFillsProperties.getProperty("steamMaxVolumeFuel")),
                new BigDecimal(Configs.WagonFillsProperties.getProperty("steamConsumption")), volumeFuel);
    }

    public static SteamLocomotive save(SteamLocomotive wagon) throws SQLException {
        storage.save(wagon);
        return wagon;
    }
}
