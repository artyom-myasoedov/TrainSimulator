package myasoedov.cs.models.storages.wagons;

import myasoedov.cs.Configs;
import myasoedov.cs.models.abstractWagons.FreightWagon;
import myasoedov.cs.storages.train.AttributeType;
import myasoedov.cs.storages.wagons.WagonType;

import java.sql.*;
import java.util.Map;
import java.util.UUID;

public abstract class FreightWagonDBStorage<T extends FreightWagon> extends WagonDBStorage<T> {

    public FreightWagonDBStorage(String jdbcUrl, String userName, String userParol, WagonType type) {
        super(jdbcUrl, userName, userParol, type, Configs.DBProperties.getProperty("freightWagons.table"));
    }

    public FreightWagonDBStorage(WagonType type) {
        super(type, Configs.DBProperties.getProperty("freightWagons.table"));
    }

    @Override
    public boolean save(T item) throws SQLException {
            try (Connection c = getConnection()) {
                if(!super.save(item)) {
                    delete(item.getId());
                }
                PreparedStatement statement = c.prepareStatement("insert into " + getTable() + " (WAGON_ID, TRAIN_ID, WEIGHT, AGE, CONDITION, NUMBER_IN_COMPOSITION, CARGO_WEIGHT, MAX_CARRYING, WAGON_TYPE) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                statement.setString(1, item.getId().toString());

                if (item.getTrainId() != null) {
                    statement.setString(2, item.getTrainId().toString());
                } else {
                    statement.setNull(2, Types.NULL);
                }

                statement.setLong(3, item.getWeight());
                statement.setBigDecimal(4, item.getAge());
                statement.setBigDecimal(5, item.getCondition());

                if (item.getNumberInComposition() != null) {
                    statement.setLong(6, item.getNumberInComposition());
                } else {
                    statement.setNull(6, Types.NULL);
                }

                statement.setLong(7, item.getCurrentCargoWeight());
                statement.setLong(8, item.getMaxCarrying());
                statement.setString(9, getType().toString());
                statement.execute();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        return false;
    }

    @Override
    public Map<AttributeType, Object> preGet(UUID id) {
        Map<AttributeType, Object> map = super.preGet(id);
        try (Connection c = getConnection()) {
            ResultSet rs = c.prepareStatement("select CARGO_WEIGHT, IS_OPEN, CURRENT_TEMPERATURE from " + getTable() + " where WAGON_ID = '" + id.toString() + "'").executeQuery();
            rs.next();
            map.put(AttributeType.CARGO_WEIGHT, rs.getLong(1));
            map.put(AttributeType.IS_OPEN, rs.getBoolean(2));
            map.put(AttributeType.CURRENT_TEMPERATURE, rs.getBigDecimal(3));
            return map;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}