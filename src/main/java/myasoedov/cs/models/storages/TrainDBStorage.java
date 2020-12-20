package myasoedov.cs.models.storages;

import myasoedov.cs.DoubleContainer;
import myasoedov.cs.models.abstractWagons.Locomotive;
import myasoedov.cs.models.abstractWagons.Wagon;
import myasoedov.cs.models.trains.Train;
import myasoedov.cs.storages.train.TrainType;
import myasoedov.cs.storages.wagons.WagonType;
import myasoedov.cs.wagons.locomotives.DieselLocomotive;
import myasoedov.cs.wagons.locomotives.ElectricLocomotive;
import myasoedov.cs.wagons.locomotives.SteamLocomotive;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public abstract class TrainDBStorage<T extends Train> extends DBStorage<T> {

    private final TrainType type;
    private final String table;
    private final Map<WagonType, Storage<? extends Wagon>> storageEnumMap;

    public TrainDBStorage(String jdbcUrl, String userName, String userParol, TrainType type, String table, Map<WagonType, Storage<? extends Wagon>> mapEnum) {
        super(jdbcUrl, userName, userParol);
        this.type = type;
        this.table = table;
        storageEnumMap = mapEnum;
    }

    public TrainDBStorage(TrainType type, String table, Map<WagonType, Storage<? extends Wagon>> mapEnum) {
        this.type = type;
        this.table = table;
        storageEnumMap = mapEnum;
    }

    public TrainType getType() {
        return type;
    }

    public String getTable() {
        return table;
    }

    @Override
    public boolean save(T item) throws SQLException {
        try (Connection c = getConnection()) {
            ResultSet rs = c.prepareStatement("select count(*) from TRAINS where TRAIN_ID = '" + item.getId().toString() + "'").executeQuery();
            rs.next();
            if (rs.getInt(1) != 0) {
                delete(item.getId());
            }
            PreparedStatement statement = c.prepareStatement("insert into TRAINS (TRAIN_ID, NUMBER_OF_WAGONS, NUMBER_OF_LOCOMOTIVES, TRAIN_TYPE) values (?, ?, ?, ?)");
            statement.setString(1, item.getId().toString());
            statement.setLong(2, item.getWagonsSize());
            statement.setLong(3, item.getLocomotivesSize());
            statement.setString(4, getType().toString());
            statement.executeUpdate();
            item.getLocomotives().forEach(locomotive -> {
                try {
                    if (locomotive instanceof DieselLocomotive) {
                        DieselLocomotive.save((DieselLocomotive) locomotive);
                    } else if (locomotive instanceof ElectricLocomotive) {
                        ElectricLocomotive.save((ElectricLocomotive) locomotive);
                    } else if (locomotive instanceof SteamLocomotive) {
                        SteamLocomotive.save((SteamLocomotive) locomotive);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
            return true;
        } catch (SQLException e) {
            throw new SQLException("Ошибка записи в базу данных!", e);
        }
    }

    public boolean delete(UUID id) throws SQLException {
        try (Connection c = getConnection()) {
            PreparedStatement statement = c.prepareStatement("select count(*) from TRAINS where TRAIN_ID = '" + id.toString() + "'");
            ResultSet rs = statement.executeQuery();
            rs.next();
            if (rs.getInt(1) == 0) {
                return true;
            }
            statement = c.prepareStatement("select  TRAIN_TYPE from TRAINS where TRAIN_ID = '" + id.toString() + "'");
            rs = statement.executeQuery();
            rs.next();
            if (rs.getString(1).equals(getType().toString())) {
                statement = c.prepareStatement("delete from TRAINS WHERE TRAIN_ID = '" + id.toString() + "'");
                statement.execute();
                statement = c.prepareStatement("delete from LOCOMOTIVES WHERE TRAIN_ID = '" + id.toString() + "'");
                statement.execute();
                statement = c.prepareStatement("delete from " + getTable() + " WHERE TRAIN_ID = '" + id.toString() + "'");
                statement.execute();
                return true;
            } else {
                throw new IllegalStateException();
            }
        } catch (SQLException | RuntimeException throwables) {
            throw new SQLException("Ошибка записи в базу данных!", throwables);
        }
    }

    public DoubleContainer<List<Wagon>, List<Locomotive>> preGet(UUID id) throws SQLException {
        DoubleContainer<List<Wagon>, List<Locomotive>> container = new DoubleContainer<>();
        container.setFirst(new ArrayList<>());
        container.setSecond(new ArrayList<>());
        try (Connection c = getConnection()) {
            PreparedStatement statement = c.prepareStatement("select WAGON_ID, WAGON_TYPE from " + getTable() + " where TRAIN_ID = '" + id.toString() + "'");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                container.getFirst().add(storageEnumMap.get(Enum.valueOf(WagonType.class, rs.getString(2))).get(UUID.fromString(rs.getString(1))));
            }
            rs = c.prepareStatement("select WAGON_ID, WAGON_TYPE from LOCOMOTIVES where TRAIN_ID = '" + id.toString() + "'").executeQuery();
            while (rs.next()) {
                container.getSecond().add((Locomotive) storageEnumMap.get(Enum.valueOf(WagonType.class, rs.getString(2))).get(UUID.fromString(rs.getString(1))));
            }
            container.getSecond().sort(Comparator.comparing(Wagon::getNumberInComposition));
        } catch (SQLException throwables) {
            throw new SQLException("Ошибка записи в базу данных!", throwables);
        }
        return container;
    }
}
