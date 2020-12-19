package myasoedov.cs.storages.wagons.freight;

import myasoedov.cs.models.storages.wagons.OpeningWagonDBStorage;
import myasoedov.cs.storages.wagons.WagonType;
import myasoedov.cs.wagons.freightWagons.RefrigeratorWagon;

import java.sql.*;

public class RefrigeratorWagonDBStorage extends OpeningWagonDBStorage<RefrigeratorWagon> {

    public RefrigeratorWagonDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, WagonType.REFRIGERATOR);
    }

    public RefrigeratorWagonDBStorage() {
        super(WagonType.REFRIGERATOR);
    }

    @Override
    public boolean save(RefrigeratorWagon item) throws SQLException {
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
