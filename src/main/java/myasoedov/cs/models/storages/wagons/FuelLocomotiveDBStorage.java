package myasoedov.cs.models.storages.wagons;

import myasoedov.cs.models.Storable;
import myasoedov.cs.wagons.locomotives.FuelLocomotive;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class FuelLocomotiveDBStorage extends LocomotiveDBStorage {

    public FuelLocomotiveDBStorage(String jdbcUrl, String userName, String userParol, String type) {
        super(jdbcUrl, userName, userParol, type);
    }

    public FuelLocomotiveDBStorage(String type) {
        super(type);
    }

    @Override
    public boolean save(Storable item) throws SQLException {
        boolean isSaved = super.save(item);
        if (isSaved) {
            try (Connection c = getConnection()) {
                FuelLocomotive locomotive = (FuelLocomotive) item;
                PreparedStatement statement = c.prepareStatement("insert into FUEl_PARAMETERS (WAGON_ID, MAX_VOLUME_FUEL, VOLUME_OF_FUEL, CONSUMPTION) values (?, ?, ?, ?)");
                statement.setLong(1, locomotive.getId());
                statement.setBigDecimal(2, locomotive.getMaxVolumeFuel());
                statement.setBigDecimal(3, locomotive.getVolumeFuel());
                statement.setBigDecimal(4, locomotive.getConsumption());
                statement.executeUpdate();
            }
            return true;
        } else {
            return false;
        }
    }
}
