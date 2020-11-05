package myasoedov.cs.models.storages;

import myasoedov.cs.models.Storable;
import myasoedov.cs.models.trains.Train;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class TrainDBStorage extends DBStorage {

    private final String type;

    public TrainDBStorage(String jdbcUrl, String userName, String userParol, String type) {
        super(jdbcUrl, userName, userParol);
        this.type = type;
    }

    public TrainDBStorage(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }


    @Override
    public boolean save(Storable item) {
        Train<?> train = (Train<?>) item;
        try (Connection c = getConnection()) {
            PreparedStatement statement = c.prepareStatement("insert into TRAINS (TRAIN_ID, NUMBER_OF_WAGONS, NUMBER_OF_LOCOMOTIVES, TRAIN_TYPE) values (?, ?, ?, ?)");
            statement.setLong(1, train.getId());
            statement.setLong(2, train.getWagonsSize());
            statement.setLong(3, train.getLocomotivesSize());
            statement.setString(4, getType());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection c = getConnection()) {
            PreparedStatement statement = c.prepareStatement("select  TRAIN_TYPE from TRAINS where TRAIN_ID = " + id.toString());
            ResultSet rs = statement.executeQuery();
            rs.next();
            if (rs.getString(1).equals(getType())) {
                statement = c.prepareStatement("delete from TRAINS WHERE TRAIN_ID = " + id.toString());
                statement.execute();
                statement = c.prepareStatement("delete from LOCOMOTIVES WHERE TRAIN_ID = " + id.toString());
                statement.execute();
                String table;
                if (getType().equals("Passenger")) {
                    table = "PASSENGER_WAGONS";
                } else {
                    table = "FREIGHT_WAGONS";
                }
                statement = c.prepareStatement("delete from " + table + " WHERE TRAIN_ID = " + id.toString());
                statement.execute();
                return true;
            } else {
                throw new IllegalStateException();
            }
        } catch (SQLException | RuntimeException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public Storable get(Long id) {
        return null;
    }
}
