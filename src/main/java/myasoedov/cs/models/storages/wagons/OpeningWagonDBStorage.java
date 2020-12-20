package myasoedov.cs.models.storages.wagons;

import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.storages.train.AttributeType;
import myasoedov.cs.storages.wagons.WagonType;
import myasoedov.cs.wagons.freightWagons.OpeningWagon;
import myasoedov.cs.wagons.freightWagons.RefrigeratorWagon;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

public abstract class OpeningWagonDBStorage<T extends OpeningWagon> extends FreightWagonDBStorage<T> {
    public OpeningWagonDBStorage(String jdbcUrl, String userName, String userParol, WagonType type) {
        super(jdbcUrl, userName, userParol, type);
    }

    public OpeningWagonDBStorage(WagonType type) {
        super(type);
    }

    @Override
    public boolean save(T item) throws SQLException {
        if (super.save(item)) {
            try (Connection c = getConnection()) {
                PreparedStatement statement = c.prepareStatement("update " + getTable() + " set IS_OPEN = ? where WAGON_ID = '" + item.getId().toString() + "'");
                statement.setBoolean(1, item.getIsOpened());
                statement.execute();
                return true;
            } catch (SQLException throwables) {
                throw new SQLException("Ошибка записи в базу данных!", throwables);
            }
        }
        return false;
    }

    @Override
    public T get(UUID id) {
        Map<AttributeType, Object> map = super.preGet(id);
        OpeningWagon wagon;
        switch (getType()) {
            case COVERED:
                wagon = WagonFactory.createCoveredWagon((BigDecimal) map.get(AttributeType.AGE), (BigDecimal) map.get(AttributeType.CONDITION), id);
                break;
            case TANK:
                wagon = WagonFactory.createTankWagon((BigDecimal) map.get(AttributeType.AGE), (BigDecimal) map.get(AttributeType.CONDITION), id);
                break;
            case REFRIGERATOR:
                wagon = WagonFactory.createRefrigeratorWagon((BigDecimal) map.get(AttributeType.AGE), (BigDecimal) map.get(AttributeType.CONDITION), id);
                ((RefrigeratorWagon) wagon).setCurrentTemperature((BigDecimal) map.get(AttributeType.CURRENT_TEMPERATURE));
                break;
            default:
                throw new IllegalStateException();
        }
        wagon.loadCargo(Math.toIntExact((Long) map.get(AttributeType.CARGO_WEIGHT)));
        if ((Boolean) map.get(AttributeType.IS_OPEN)) {
            wagon.openWagon();
        }

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