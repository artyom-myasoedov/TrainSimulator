package myasoedov.cs.models.storages.wagons;

import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.models.Storable;
import myasoedov.cs.models.abstractWagons.FreightWagon;
import myasoedov.cs.models.abstractWagons.Locomotive;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

public abstract class FreightWagonDBStorage extends WagonDBStorage {
    private final static String TABLE = "FREIGHT_WAGONS";
    public FreightWagonDBStorage(String jdbcUrl, String userName, String userParol, String type) {
        super(jdbcUrl, userName, userParol, type, TABLE);
    }

    public FreightWagonDBStorage(String type) {
        super(type, TABLE);
    }

    @Override
    public boolean save(Storable item) {
        FreightWagon wagon = (FreightWagon) item;
        try (Connection c = getConnection()) {
            PreparedStatement statement = c.prepareStatement("insert into " + getTable() + " (WAGON_ID, TRAIN_ID, WEIGHT, AGE, CONDITION, NUMBER_IN_COMPOSITION, CARGO_WEIGHT, MAX_CARRYING, WAGON_TYPE) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setLong(1, wagon.getId());
            if (wagon.getTrainId() != null) {
                statement.setLong(2, wagon.getTrainId());
            } else {
                statement.setLong(2, Types.NULL);
            }
            statement.setLong(3, wagon.getWeight());
            statement.setBigDecimal(4, wagon.getAge());
            statement.setBigDecimal(5, wagon.getCondition());
            if (wagon.getNumberInComposition() != null) {
                statement.setLong(6, wagon.getNumberInComposition());
            } else {
                statement.setLong(6, Types.NULL);
            }
            statement.setLong(7, wagon.getCurrentCargoWeight());
            statement.setLong(8, wagon.getMaxCarrying());
            statement.setString(9, getType());
            statement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Object> preGet(Long id) {
        List<Object> list = super.preGet(id);
        try (Connection c = getConnection()) {
            ResultSet rs = c.prepareStatement("select CARGO_WEIGHT from " + getTable() + " where WAGON_ID = " + id.toString()).executeQuery();
            rs.next();
            list.add(rs.getLong(1));
            return list;
    } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
