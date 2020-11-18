package myasoedov.cs.storages.wagons.freight;

import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.models.storages.wagons.OpeningWagonDBStorage;
import myasoedov.cs.storages.wagons.WagonType;
import myasoedov.cs.wagons.freightWagons.RefrigeratorWagon;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

public class RefrigeratorWagonDBStorage<T extends RefrigeratorWagon> extends OpeningWagonDBStorage<T> {
    private final static WagonType TYPE = WagonType.REFRIGERATOR;

    public RefrigeratorWagonDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, TYPE);
    }

    public RefrigeratorWagonDBStorage() {
        super(TYPE);
    }

    @Override
    public boolean save(T item) throws SQLException {
        if (super.save(item)) {
            try (Connection c = getConnection()) {
                PreparedStatement statement = c.prepareStatement("update " + getTable() + " set MIN_TEMPERATURE = ?, MAX_TEMPERATURE = ?, CURRENT_TEMPERATURE = ? where WAGON_ID = '" + item.getId().toString() + "'");
                statement.setBigDecimal(1, item.getMinTemperature());
                statement.setBigDecimal(2, item.getMaxTemperature());
                statement.setBigDecimal(3, item.getCurrentTemperature());
                statement.execute();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
        return false;
    }
}