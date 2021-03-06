package myasoedov.cs.storages.wagons.locomotives;

import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.models.storages.wagons.LocomotiveDBStorage;
import myasoedov.cs.storages.train.AttributeType;
import myasoedov.cs.storages.wagons.WagonType;
import myasoedov.cs.wagons.locomotives.ElectricLocomotive;
import myasoedov.cs.wagons.locomotives.LocomotiveEngineConditions;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

public class ElectricLocomotiveDBStorage extends LocomotiveDBStorage<ElectricLocomotive> {

    public ElectricLocomotiveDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, WagonType.ELECTRIC);
    }

    public ElectricLocomotiveDBStorage() {
        super(WagonType.ELECTRIC);
    }

    @Override
    public boolean save(ElectricLocomotive item) throws SQLException {
        if (super.save(item)) {
            try (Connection c = getConnection()) {
                PreparedStatement statement = c.prepareStatement("update " + getTable() + " set ELECTRIC_GRID_CONNECTION = ? where WAGON_ID = '" + item.getId().toString() + "'");
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
    public ElectricLocomotive get(UUID id) {
        Map<AttributeType, Object> map = super.preGet(id);
        ElectricLocomotive locomotive = WagonFactory.createElectricLocomotive((BigDecimal) map.get(AttributeType.AGE), (BigDecimal) map.get(AttributeType.CONDITION), LocomotiveEngineConditions.DISABLED, id);
        if ((Boolean) map.get(AttributeType.ELECTRIC_GRID_CONNECTION)) {
            locomotive.setPowerGridConnection(LocomotiveEngineConditions.ENABLED);
        }
        Long num = (Long) map.get(AttributeType.NUMBER_IN_COMPOSITION) != 0L ? (Long) map.get(AttributeType.NUMBER_IN_COMPOSITION) : null;
        locomotive.setNumberInComposition(num);

        String str = (String) map.get(AttributeType.TRAIN_ID);
        locomotive.setTrainId(null);
        if (str != null) {
            locomotive.setTrainId(UUID.fromString(str));
        }
        return locomotive;
    }
}