package myasoedov.cs.models.storages.wagons;

import myasoedov.cs.models.Storable;
import myasoedov.cs.models.abstractWagons.Wagon;
import myasoedov.cs.models.storages.DBStorage;
import myasoedov.cs.storages.train.AttributeType;
import myasoedov.cs.storages.wagons.WagonType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public abstract class WagonDBStorage<T extends Wagon> extends DBStorage<T> {

    private final WagonType type;
    private final String table;

    public WagonDBStorage(String jdbcUrl, String userName, String userParol, WagonType type, String table) {
        super(jdbcUrl, userName, userParol);
        this.type = type;
        this.table = table;
    }

    public WagonDBStorage(WagonType type, String table) {
        this.type = type;
        this.table = table;
    }

    public WagonType getType() {
        return type;
    }

    public String getTable() {
        return table;
    }

    @Override
    public boolean save(T item) throws SQLException {
        try (Connection c = getConnection()) {
            ResultSet rs = c.prepareStatement("select count(WAGON_ID) from " + getTable() + " where WAGON_ID = '" + item.getId().toString() + "'").executeQuery();
            rs.next();
            return rs.getInt(1) == 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(UUID id) {
        try (Connection c = getConnection()) {
            PreparedStatement statement = c.prepareStatement("select WAGON_TYPE from " + getTable() + " where WAGON_ID = '" + id.toString() + "'");
            ResultSet rs = statement.executeQuery();
            rs.next();
            if (Enum.valueOf(WagonType.class, rs.getString(1)).equals(getType())) {
                statement = c.prepareStatement("delete from " + table + " where WAGON_ID = " + id.toString());
                statement.execute();
                return true;
            }
            throw new IllegalStateException();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public Map<AttributeType, Object> preGet(UUID id) {
        try (Connection c = getConnection()) {
            ResultSet rs = c.prepareStatement("select AGE, CONDITION, NUMBER_IN_COMPOSITION, WAGON_TYPE, TRAIN_ID from " + getTable() + " where WAGON_ID = '" + id.toString() + "'").executeQuery();
            rs.next();

            if (rs.getString(4).equals(getType().toString())) {
                Map<AttributeType, Object> map = new HashMap<>();
                map.put(AttributeType.AGE, rs.getBigDecimal(1));
                map.put(AttributeType.CONDITION, rs.getBigDecimal(2));
                map.put(AttributeType.NUMBER_IN_COMPOSITION, rs.getLong(3));
                map.put(AttributeType.WAGON_TYPE, rs.getString(4));
                map.put(AttributeType.TRAIN_ID, rs.getString(5));

                return map;
            }
            throw new IllegalStateException();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}