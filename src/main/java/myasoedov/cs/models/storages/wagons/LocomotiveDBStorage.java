package myasoedov.cs.models.storages.wagons;

import myasoedov.cs.models.Storable;
import myasoedov.cs.models.abstractWagons.Locomotive;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public abstract class LocomotiveDBStorage extends WagonDBStorage {
    private final static String TABLE = "LOCOMOTIVES";

    public LocomotiveDBStorage(String jdbcUrl, String userName, String userParol, String type) {
        super(jdbcUrl, userName, userParol, type, TABLE);
    }

    public LocomotiveDBStorage(String type) {
        super(type, TABLE);
    }

    @Override
    public boolean save(Storable item) throws SQLException {
        Locomotive locomotive = (Locomotive) item;
        try (Connection c = getConnection()) {
            PreparedStatement statement = c.prepareStatement(
                    "insert into " + getTable() + " (WAGON_ID, AGE, CONDITION, WEIGHT, NUMBER_IN_COMPOSITION, TRAIN_ID, POWER, MAX_SPEED, ENGINE, LOCOMOTIVE_TYPE) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setLong(1, locomotive.getId());
            statement.setBigDecimal(2, locomotive.getAge());
            statement.setBigDecimal(3, locomotive.getCondition());
            statement.setLong(4, locomotive.getWeight());
            if (locomotive.getNumberInComposition() != null) {
                statement.setLong(5, locomotive.getNumberInComposition());
            } else {
                statement.setLong(5, Types.NULL);
            }
            if (locomotive.getTrainId() != null) {
                statement.setLong(6, locomotive.getTrainId());
            } else {
                statement.setLong(6, Types.NULL);
            }
            statement.setLong(7, locomotive.getPower());
            statement.setBigDecimal(8, locomotive.getMaxSpeed());
            statement.setBoolean(9, locomotive.isEngineWork());
            statement.setString(10, getType());
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Storable get(Long id) {
        return null;
    }
}
