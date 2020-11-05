package myasoedov.cs.models.storages.wagons;

import myasoedov.cs.models.storages.DBStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
