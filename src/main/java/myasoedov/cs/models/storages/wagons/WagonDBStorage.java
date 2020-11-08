package myasoedov.cs.models.storages.wagons;

import myasoedov.cs.models.Storable;
import myasoedov.cs.models.storages.DBStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class WagonDBStorage extends DBStorage {

    private final String type;
    private final String table;

    public WagonDBStorage(String jdbcUrl, String userName, String userParol, String type, String table) {
        super(jdbcUrl, userName, userParol);
        this.type = type;
        this.table = table;
    }

    public WagonDBStorage(String type, String table) {
        this.type = type;
        this.table = table;
    }

    public String getType() {
        return type;
    }

    public String getTable() {
        return table;
    }

    @Override
    public boolean delete(Long id) {
        try (Connection c = getConnection()) {
            PreparedStatement statement = c.prepareStatement("delete from " + table + " where WAGON_ID = " + id.toString());
            statement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public List<Object> preGet(Long id) {
        try (Connection c =getConnection()) {
            ResultSet rs = c.prepareStatement("select AGE, CONDITION, NUMBER_IN_COMPOSITION, WAGON_TYPE from " + getTable() + " where WAGON_ID = " + id.toString()).executeQuery();
            rs.next();
            if (rs.getString(4).equals(getType())) {
                List<Object> list = new ArrayList<>();
                list.add(rs.getBigDecimal(1));
                list.add(rs.getBigDecimal(2));
                list.add(rs.getLong(3));
                list.add(rs.getString(4));
                return list;
            }
            throw new IllegalStateException();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Storable get(Long id) {
        return null;
    }
}
