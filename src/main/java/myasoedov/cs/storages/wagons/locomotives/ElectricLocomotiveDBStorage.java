package myasoedov.cs.storages.wagons.locomotives;

import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.models.storages.wagons.LocomotiveDBStorage;
import myasoedov.cs.storages.wagons.WagonType;
import myasoedov.cs.wagons.locomotives.ElectricLocomotive;
import myasoedov.cs.wagons.locomotives.LocomotiveEngineConditions;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ElectricLocomotiveDBStorage<T extends ElectricLocomotive> extends LocomotiveDBStorage<T> {
    private final static WagonType TYPE = WagonType.ELECTRIC;

    public ElectricLocomotiveDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, TYPE);
    }

    public ElectricLocomotiveDBStorage() {
        super(TYPE);
    }

    @Override
    public boolean save(T item) throws SQLException {
        if (super.save(item)) {
            try (Connection c = getConnection()) {
                PreparedStatement statement = c.prepareStatement("update " + getTable() + " set ELECTRIC_GRID_CONNECTION = ? where WAGON_ID = " + item.getId().toString());
                statement.setBoolean(1, item.isPowerGridConnect());
                statement.execute();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public T get(UUID id) {
        List<Object> list = super.preGet(id);
        ElectricLocomotive locomotive = WagonFactory.createElectricLocomotive((BigDecimal) list.get(0), (BigDecimal) list.get(1), LocomotiveEngineConditions.DISABLED, id);
        if ((Boolean) list.get(5)) {
            locomotive.setPowerGridConnection(LocomotiveEngineConditions.ENABLED);
        }
        Long num = (Long) list.get(2) != 0L ? (Long) list.get(2) : null;
        locomotive.setNumberInComposition(num);

        String str = (String) list.get(4);
        locomotive.setTrainId(null);
        if (str != null) {
            locomotive.setTrainId(UUID.fromString(str));
        }
        return (T) locomotive;
    }
}
