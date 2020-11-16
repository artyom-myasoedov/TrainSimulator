package myasoedov.cs.models.storages.wagons;

import myasoedov.cs.models.Storable;
import myasoedov.cs.models.abstractWagons.Wagon;
import myasoedov.cs.models.storages.DBStorage;
import myasoedov.cs.storages.wagons.WagonType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public List<Object> preGet(UUID id) {
        try (Connection c = getConnection()) {
            ResultSet rs = c.prepareStatement("select AGE, CONDITION, NUMBER_IN_COMPOSITION, WAGON_TYPE, TRAIN_ID from " + getTable() + " where WAGON_ID = '" + id.toString() + "'").executeQuery();
            rs.next();

            if (rs.getString(4).equals(getType().toString())) {
                List<Object> list = new ArrayList<>();
                list.add(rs.getBigDecimal(1));
                list.add(rs.getBigDecimal(2));
                list.add(rs.getLong(3));
                list.add(rs.getString(4));
                list.add(rs.getString(5));

                return list;
            }
            throw new IllegalStateException();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}
