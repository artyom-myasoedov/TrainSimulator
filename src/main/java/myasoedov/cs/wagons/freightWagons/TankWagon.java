package myasoedov.cs.wagons.freightWagons;


import myasoedov.cs.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.storages.wagons.freight.TankWagonDBStorage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

public class TankWagon extends OpeningWagon implements Serializable {

    private final static Storage<TankWagon> storage = new TankWagonDBStorage(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);

    public TankWagon(BigDecimal age, BigDecimal condition, UUID id) {
        super(Integer.parseInt(Configs.WagonFillsProperties.getProperty("tankWeight")), age, condition,
                Integer.parseInt(Configs.WagonFillsProperties.getProperty("tankMaxCarrying")), id);
    }

    public static TankWagon save(TankWagon wagon) throws SQLException {
        storage.save(wagon);
        return wagon;
    }

    @Override
    public void loadCargo(int weight) {
        super.loadCargo(weight);
    }

    @Override
    public void unloadCargo() {
        super.unloadCargo();
    }
}
