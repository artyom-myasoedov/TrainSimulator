package myasoedov.cs.models.storages.wagons;

import myasoedov.cs.Configs;
import myasoedov.cs.models.abstractWagons.Locomotive;
import myasoedov.cs.storages.train.AttributeType;
import myasoedov.cs.storages.wagons.WagonType;

import java.sql.*;
import java.util.Map;
import java.util.UUID;

public abstract class LocomotiveDBStorage<T extends Locomotive> extends WagonDBStorage<T> {

    public LocomotiveDBStorage(String jdbcUrl, String userName, String userParol, WagonType type) {
        super(jdbcUrl, userName, userParol, type, Configs.DBProperties.getProperty("locomotives.table"));
    }

    public LocomotiveDBStorage(WagonType type) {
        super(type, Configs.DBProperties.getProperty("locomotives.table"));
    }

    @Override
    public boolean save(T item) throws SQLException {
            try (Connection c = getConnection()) {
                if (!super.save(item)) {
                    delete(item.getId());
                }
                PreparedStatement statement = c.prepareStatement(
                        "insert into " + getTable() + " (WAGON_ID, AGE, CONDITION, WEIGHT, NUMBER_IN_COMPOSITION, TRAIN_ID, POWER, MAX_SPEED, ENGINE, WAGON_TYPE) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                statement.setString(1, item.getId().toString());
                statement.setBigDecimal(2, item.getAge());
                statement.setBigDecimal(3, item.getCondition());
                statement.setLong(4, item.getWeight());

                if (item.getNumberInComposition() != null) {
                    statement.setLong(5, item.getNumberInComposition());
                } else {
                    statement.setNull(5, Types.NULL);
                }

                if (item.getTrainId() != null) {
                    statement.setString(6, item.getTrainId().toString());
                } else {
                    statement.setNull(6, Types.NULL);
                }

                statement.setLong(7, item.getPower());
                statement.setBigDecimal(8, item.getMaxSpeed());
                statement.setBoolean(9, item.isEngineWork());
                statement.setString(10, getType().toString());
                statement.execute();
                return true;
            } catch (SQLException e) {
                throw new SQLException("Ошибка записи в базу данных!", e);            }
    }

    public Map<AttributeType, Object> preGet(UUID id) {
        Map<AttributeType, Object> map = super.preGet(id);
        try (Connection c = getConnection()) {
            ResultSet rs = c.prepareStatement("select ELECTRIC_GRID_CONNECTION, VOLUME_OF_FUEL from " + getTable() + " where WAGON_ID = '" + id.toString() + "'").executeQuery();
            rs.next();

            map.put(AttributeType.ELECTRIC_GRID_CONNECTION, rs.getBoolean(1));
            map.put(AttributeType.VOLUME_OF_FUEL, rs.getBigDecimal(2));

            return map;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}