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

public abstract class TrainDBStorage<T extends Train<? extends Wagon>> extends DBStorage<T> {

    private final TrainType type;
    private final String table;
    private final Map<Class<? extends Wagon>, Storage<? extends Wagon>> storageMap;
    private final Map<WagonType, Storage<? extends Wagon>> storageEnumMap;
    private final Map<Class<? extends Locomotive>, Storage<? extends Locomotive>> storageLocomotiveMap = Configs.createLocomotivesMap();

    public TrainDBStorage(String jdbcUrl, String userName, String userParol, TrainType type, String table, Map<Class<? extends Wagon>, Storage<? extends Wagon>> map, Map<WagonType, Storage<? extends Wagon>> mapEnum) {
        super(jdbcUrl, userName, userParol);
        this.type = type;
        this.table = table;
        storageMap = map;
        storageEnumMap = mapEnum;
    }

    public TrainDBStorage(TrainType type, String table, Map<Class<? extends Wagon>, Storage<? extends Wagon>> map, Map<WagonType, Storage<? extends Wagon>> mapEnum) {
        this.type = type;
        this.table = table;
        storageMap = map;
        storageEnumMap = mapEnum;
    }

    public TrainType getType() {
        return type;
    }

    public String getTable() {
        return table;
    }

    @Override
    public boolean save(T item) {
        try (Connection c = getConnection()) {
            ResultSet rs = c.prepareStatement("select count(*) from TRAINS where TRAIN_ID = '" + item.getId().toString() + "'").executeQuery();
            rs.next();
            if (rs.getInt(1) != 0) {
                return false;
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
            //как сохранять?
//            item.getWagons().forEach(wagon -> {
//                storageMap.get(wagon.getClass()).save(wagon.getClass().cast(wagon));
//            });
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(UUID id) {
        try (Connection c = getConnection()) {
            PreparedStatement statement = c.prepareStatement("select  TRAIN_TYPE from TRAINS where TRAIN_ID = '" + id.toString() + "'");
            ResultSet rs = statement.executeQuery();
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
            throwables.printStackTrace();
            return false;
        }
    }

    public DoubleContainer<List<Wagon>, List<Locomotive>> preGet(UUID id) {
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
            throwables.printStackTrace();
        }
        return container;
    }
}
