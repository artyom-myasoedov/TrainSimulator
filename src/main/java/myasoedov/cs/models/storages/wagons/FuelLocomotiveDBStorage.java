package myasoedov.cs.models.storages.wagons;

import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.models.Storable;
import myasoedov.cs.models.abstractWagons.Locomotive;
import myasoedov.cs.storages.train.AttributeType;
import myasoedov.cs.storages.wagons.WagonType;
import myasoedov.cs.wagons.locomotives.FuelLocomotive;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class FuelLocomotiveDBStorage<T extends FuelLocomotive> extends LocomotiveDBStorage<T> {

    public FuelLocomotiveDBStorage(String jdbcUrl, String userName, String userParol, WagonType type) {
        super(jdbcUrl, userName, userParol, type);
    }

    public FuelLocomotiveDBStorage(WagonType type) {
        super(type);
    }

    @Override
    public boolean save(T item) throws SQLException {
        if (super.save(item)) {
            try (Connection c = getConnection()) {
                PreparedStatement statement = c.prepareStatement("update " + getTable() + " set MAX_VOLUME_FUEL = ?, VOLUME_OF_FUEL = ?, CONSUMPTION = ? where WAGON_ID = '" + item.getId().toString() + "'");
                statement.setBigDecimal(1, item.getMaxVolumeFuel());
                statement.setBigDecimal(2, item.getVolumeFuel());
                statement.setBigDecimal(3, item.getConsumption());
                statement.executeUpdate();
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T get(UUID id) {
        Map<AttributeType, Object> map = super.preGet(id);
        Locomotive locomotive;
        switch (getType()) {
            case DIESEL:
                locomotive = WagonFactory.createDieselLocomotive((BigDecimal) map.get(AttributeType.AGE), (BigDecimal) map.get(AttributeType.CONDITION), (BigDecimal) map.get(AttributeType.VOLUME_OF_FUEL), id);
                break;
            case STEAM:
                locomotive = WagonFactory.createSteamLocomotive((BigDecimal) map.get(AttributeType.AGE), (BigDecimal) map.get(AttributeType.CONDITION), (BigDecimal) map.get(AttributeType.VOLUME_OF_FUEL), id);
                break;
            default:
                throw new IllegalStateException();
        }
        Long num = (Long) map.get(AttributeType.NUMBER_IN_COMPOSITION) != 0L ? (Long) map.get(AttributeType.NUMBER_IN_COMPOSITION) : null;
        locomotive.setNumberInComposition(num);

        String str = (String) map.get(AttributeType.TRAIN_ID);
        locomotive.setTrainId(null);
        if (str != null) {
            locomotive.setTrainId(UUID.fromString(str));
        }
        return (T) locomotive;
    }
}
