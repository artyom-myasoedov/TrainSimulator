package myasoedov.cs.wagons.freightWagons;

import myasoedov.cs.models.storages.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.storages.wagons.freight.PlatformWagonDBStorage;
import myasoedov.cs.storages.wagons.freight.RefrigeratorWagonDBStorage;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

public class PlatformWagon extends myasoedov.cs.models.abstractWagons.FreightWagon {
    private final static int WEIGHT = 1500;
    private final static int MAX_CARRYING = 4000;
    private final static Storage<PlatformWagon> storage = new PlatformWagonDBStorage<>(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);


    public PlatformWagon(BigDecimal age, BigDecimal condition, UUID id) {
        super(WEIGHT, age, condition, MAX_CARRYING, id);
    }

    public static PlatformWagon save(PlatformWagon wagon) throws SQLException {
        storage.save(wagon);
        return wagon;
    }

    @Override
    public void loadCargo(int weight) {
        super.loadCargo(weight);
        System.out.println("Cargo is loaded to platform wagon");
    }

    @Override
    public void unloadCargo() {
        super.unloadCargo();
        System.out.println("Cargo is unloaded from platform wagon");
    }
}
