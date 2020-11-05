package myasoedov.cs.storages.wagons.freight;

import myasoedov.cs.models.Storable;
import myasoedov.cs.models.storages.wagons.OpeningWagonDBStorage;
import myasoedov.cs.wagons.freightWagons.RefrigeratorWagon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class RefrigeratorWagonDBStorage extends OpeningWagonDBStorage {
    private final static String TYPE = "Refrigerator";

    public RefrigeratorWagonDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, TYPE);
    }

    public RefrigeratorWagonDBStorage() {
        super(TYPE);
    }

    @Override
    public boolean save(Storable item) {
        if (item instanceof RefrigeratorWagon) {
            if (super.save(item)) {
                RefrigeratorWagon wagon = (RefrigeratorWagon) item;
                try (Connection c = getConnection()) {
                    PreparedStatement statement = c.prepareStatement("insert into WAGON_TEMPERATURE (WAGON_ID, MIN_TEMPERATURE, MAX_TEMPERATURE, CURRENT_TEMPERATURE, TRAIN_ID) values (?, ?, ?, ?, ?)");
                    statement.setLong(1, wagon.getId());
                    statement.setBigDecimal(2, wagon.getMinTemperature());
                    statement.setBigDecimal(3, wagon.getMaxTemperature());
                    statement.setBigDecimal(4, wagon.getCurrentTemperature());
                    if (wagon.getTrainId() != null) {
                        statement.setLong(5, wagon.getTrainId());
                    } else {
                        statement.setLong(5, Types.NULL);
                    }
                    statement.execute();
                    return true;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    return false;
                }
            }
            return false;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
