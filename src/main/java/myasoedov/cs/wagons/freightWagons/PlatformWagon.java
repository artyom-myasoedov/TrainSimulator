package myasoedov.cs.wagons.freightWagons;

import myasoedov.cs.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.storages.wagons.freight.PlatformWagonDBStorage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

public class PlatformWagon extends myasoedov.cs.models.abstractWagons.FreightWagon implements Serializable {
    private final static Storage<PlatformWagon> storage = new PlatformWagonDBStorage(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);


    public PlatformWagon(BigDecimal age, BigDecimal condition, UUID id) {
        super(Integer.parseInt(Configs.WagonFillsProperties.getProperty("platformWeight")), age, condition,
                Integer.parseInt(Configs.WagonFillsProperties.getProperty("platformMaxCarrying")), id);
    }

    public static PlatformWagon save(PlatformWagon wagon) throws SQLException {
        storage.save(wagon);
        return wagon;
    }

}
