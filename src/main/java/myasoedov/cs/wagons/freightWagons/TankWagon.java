package myasoedov.cs.wagons.freightWagons;


import myasoedov.cs.models.storages.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.storages.wagons.freight.TankWagonDBStorage;
import myasoedov.cs.storages.wagons.locomotives.DieselLocomotiveDBStorage;
import myasoedov.cs.wagons.locomotives.DieselLocomotive;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

public class TankWagon extends OpeningWagon {
    private final static int WEIGHT = 2500;
    private final static int MAX_CARRYING = 3000;
    private final static Storage<TankWagon> storage = new TankWagonDBStorage<>(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);


    public TankWagon(BigDecimal age, BigDecimal condition, UUID id) {
        super(WEIGHT, age, condition, MAX_CARRYING, id);
    }

    public static TankWagon save(TankWagon wagon) throws SQLException {
        storage.save(wagon);
        return wagon;
    }

    @Override
    public void loadCargo(int weight) {
        super.loadCargo(weight);
        System.out.println("Cargo is loaded to tank wagon");
    }

    @Override
    public void unloadCargo() {
        super.unloadCargo();
        System.out.println("Cargo is unloaded from tank wagon");
    }
}
