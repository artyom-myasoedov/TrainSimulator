package myasoedov.cs.storages.wagons.freight;

import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.models.Storable;
import myasoedov.cs.models.storages.wagons.OpeningWagonDBStorage;
import myasoedov.cs.wagons.freightWagons.OpeningWagon;
import myasoedov.cs.wagons.freightWagons.RefrigeratorWagon;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

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

    @Override
    public Storable get(Long id) {
        List<Object> list = super.preGet(id);
        RefrigeratorWagon wagon = WagonFactory.createRefrigeratorWagon((BigDecimal) list.get(0), (BigDecimal) list.get(1), id);
        wagon.loadCargo(Math.toIntExact((Long) list.get(5)));
        try (Connection c = getConnection()) {
            ResultSet rs = c.prepareStatement("select CURRENT_TEMPERATURE from WAGON_TEMPERATURE where WAGON_ID = " + id.toString()).executeQuery();
            rs.next();
            wagon.setCurrentTemperature(rs.getBigDecimal(1));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        if ((Long) list.get(2) != 0L) {
            wagon.setNumberInComposition((Long) list.get(2));
        } else {
            wagon.setNumberInComposition(null);
        }
        return wagon;
    }
}
