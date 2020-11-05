package myasoedov.cs.storages.wagons.locomotives;

import myasoedov.cs.models.Storable;
import myasoedov.cs.models.storages.wagons.LocomotiveDBStorage;
import myasoedov.cs.wagons.locomotives.ElectricLocomotive;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ElectricLocomotiveDBStorage extends LocomotiveDBStorage {
    private final static String TYPE = "Electric";
    public ElectricLocomotiveDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, TYPE);
    }

    public ElectricLocomotiveDBStorage() {
        super(TYPE);
    }

    @Override
    public boolean save(Storable item) throws SQLException {
        if (item instanceof ElectricLocomotive) {
            try (Connection c = getConnection()) {
                PreparedStatement statement = c.prepareStatement("insert into ELECTRIC_GRID_CONDITION (WAGON_ID, CONDITION) values (?, ?)");
                statement.setLong(1, item.getId());
                statement.setBoolean(2, ((ElectricLocomotive) item).isPowerGridConnect());
                statement.execute();
            }
            return super.save(item);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
