package myasoedov.cs.storages.wagons.locomotives;

import myasoedov.cs.models.Storable;
import myasoedov.cs.models.storages.wagons.FuelLocomotiveDBStorage;
import myasoedov.cs.wagons.locomotives.DieselLocomotive;

import java.sql.SQLException;

public class DieselLocomotiveDBStorage extends FuelLocomotiveDBStorage {
    private final static String TYPE = "Diesel";
    public DieselLocomotiveDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, TYPE);
    }

    public DieselLocomotiveDBStorage() {
        super(TYPE);
    }

    @Override
    public boolean save(Storable item) throws SQLException {
        if (item instanceof DieselLocomotive) {
            return super.save(item);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
