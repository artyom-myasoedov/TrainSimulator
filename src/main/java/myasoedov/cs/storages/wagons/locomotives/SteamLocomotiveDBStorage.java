package myasoedov.cs.storages.wagons;

import myasoedov.cs.models.Storable;
import myasoedov.cs.models.storages.wagons.FuelLocomotiveDBStorage;
import myasoedov.cs.wagons.locomotives.SteamLocomotive;

import java.sql.SQLException;

public class SteamLocomotiveDBStorage extends FuelLocomotiveDBStorage {
    private final static String TYPE = "Steam";
    public SteamLocomotiveDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, TYPE);
    }

    public SteamLocomotiveDBStorage() {
        super(TYPE);
    }

    @Override
    public boolean save(Storable item) throws SQLException {
        if (item instanceof SteamLocomotive) {
            return super.save(item);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
