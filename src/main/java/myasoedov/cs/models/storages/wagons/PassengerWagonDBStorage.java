package myasoedov.cs.models.storages.wagons;

import myasoedov.cs.Configs;
import myasoedov.cs.factories.WagonFactory;

import myasoedov.cs.models.abstractWagons.PassengerWagon;
import myasoedov.cs.storages.train.AttributeType;
import myasoedov.cs.storages.wagons.WagonType;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Map;
import java.util.UUID;

public abstract class PassengerWagonDBStorage<T extends PassengerWagon> extends WagonDBStorage<T> {

    public PassengerWagonDBStorage(String jdbcUrl, String userName, String userParol, WagonType type) {
        super(jdbcUrl, userName, userParol, type, Configs.DBProperties.getProperty("passengerWagons.table"));
    }

    public PassengerWagonDBStorage(WagonType type) {
        super(type, Configs.DBProperties.getProperty("passengerWagons.table"));
    }

    @Override
    public boolean save(T item) {
        try (Connection c = getConnection()) {
            PreparedStatement statement = c.prepareStatement(
                    "insert into " + getTable() + " (WAGON_ID, AGE, CONDITION, WEIGHT, NUMBER_IN_COMPOSITION, TRAIN_ID, NUMBER_OF_PASSENGERS, NUMBER_OF_SEATS, WAGON_TYPE) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
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

            statement.setLong(7, item.getNumberOfPassengers());
            statement.setLong(8, item.getNumberOfSeats());
            statement.setString(9, getType().toString());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public T get(UUID id) {
        Map<AttributeType, Object> map = super.preGet(id);
        try (Connection c = getConnection()) {
            ResultSet rs = c.prepareStatement("select NUMBER_OF_PASSENGERS from " + getTable() + " where WAGON_ID = '" + id.toString() + "'").executeQuery();
            rs.next();
            map.put(AttributeType.NUMBER_OF_PASSENGERS, rs.getInt(1));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PassengerWagon wagon;
        switch (getType()) {
            case COUPE:
                wagon = WagonFactory.createCoupeWagon((BigDecimal) map.get(AttributeType.AGE), (BigDecimal) map.get(AttributeType.CONDITION), id);
                break;
            case SLEEP:
                wagon = WagonFactory.createSleepWagon((BigDecimal) map.get(AttributeType.AGE), (BigDecimal) map.get(AttributeType.CONDITION), id);
                break;
            case SEAT:
                wagon = WagonFactory.createSeatWagon((BigDecimal) map.get(AttributeType.AGE), (BigDecimal) map.get(AttributeType.CONDITION), id);
                break;
            case RESTAURANT:
                wagon = WagonFactory.createRestaurantWagon((BigDecimal) map.get(AttributeType.AGE), (BigDecimal) map.get(AttributeType.CONDITION), id);
                break;
            default:
                throw new IllegalStateException();
        }
        wagon.addPassengers((Integer) map.get(AttributeType.NUMBER_OF_PASSENGERS));
        Long num = (Long) map.get(AttributeType.NUMBER_IN_COMPOSITION) != 0L ? (Long) map.get(AttributeType.NUMBER_IN_COMPOSITION) : null;
        wagon.setNumberInComposition(num);

        String str = (String) map.get(AttributeType.TRAIN_ID);
        wagon.setTrainId(null);
        if (str != null) {
            wagon.setTrainId(UUID.fromString(str));
        }
        return (T) wagon;
    }
}