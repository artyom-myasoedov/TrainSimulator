package myasoedov.cs.models.storages.wagons;

import myasoedov.cs.models.Storable;

import myasoedov.cs.models.abstractWagons.PassengerWagon;
import myasoedov.cs.wagons.passengerWagons.SleepWagon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public abstract class PassengerWagonDBStorage extends WagonDBStorage {
    private final static String TABLE = "PASSENGER_WAGONS";
    public PassengerWagonDBStorage(String jdbcUrl, String userName, String userParol, String type) {
        super(jdbcUrl, userName, userParol, type, TABLE);
    }

    public PassengerWagonDBStorage(String type) {
        super(type, TABLE);
    }

    @Override
    public boolean save(Storable item) {
        PassengerWagon wagon = (PassengerWagon) item;
        try (Connection c = getConnection()) {
            PreparedStatement statement = c.prepareStatement(
                    "insert into " + getTable() + " (WAGON_ID, AGE, CONDITION, WEIGHT, NUMBER_IN_COMPOSITION, TRAIN_ID, NUMBER_OF_PASSENGERS, NUMBER_OF_SEATS, WAGON_TYPE) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setLong(1, wagon.getId());
            statement.setBigDecimal(2, wagon.getAge());
            statement.setBigDecimal(3, wagon.getCondition());
            statement.setLong(4, wagon.getWeight());
            if (wagon.getNumberInComposition() != null) {
                statement.setLong(5, wagon.getNumberInComposition());
            } else {
                statement.setLong(5, Types.NULL);
            }
            if (wagon.getTrainId() != null) {
                statement.setLong(6, wagon.getTrainId());
            } else {
                statement.setLong(6, Types.NULL);
            }
            statement.setLong(7, wagon.getNumberOfPassengers());
            statement.setLong(8, wagon.getNumberOfSeats());
            statement.setString(9, getType());
            statement.executeUpdate();
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
