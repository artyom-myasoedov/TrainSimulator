package myasoedov.cs.wagons.freightWagons;

import myasoedov.cs.models.storages.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.storages.wagons.freight.CoveredWagonDBStorage;
import myasoedov.cs.storages.wagons.freight.PlatformWagonDBStorage;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

public class CoveredWagon extends OpeningWagon {
    private final static int WEIGHT = 2500;
    private final static int MAX_CARRYING = 2000;
    private final static Storage<CoveredWagon> storage = new CoveredWagonDBStorage<>(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);


    public CoveredWagon(BigDecimal age, BigDecimal condition, UUID id) {
        super(WEIGHT, age, condition, MAX_CARRYING, id);
    }

    public static CoveredWagon save(CoveredWagon wagon) throws SQLException {
        storage.save(wagon);
        return wagon;
    }

    @Override
    public void loadCargo(int weight) {
        super.loadCargo(weight);
        System.out.println("Cargo is loaded to covered wagon");
    }

    @Override
    public void unloadCargo() {
        super.unloadCargo();
        System.out.println("Cargo is unloaded from covered wagon");
    }
}
