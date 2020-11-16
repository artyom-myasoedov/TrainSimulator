package myasoedov.cs.models.storages.wagons;

import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.models.Storable;
import myasoedov.cs.storages.wagons.WagonType;
import myasoedov.cs.wagons.freightWagons.OpeningWagon;
import myasoedov.cs.wagons.freightWagons.RefrigeratorWagon;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
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
                throwables.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public T get(UUID id) {
        List<Object> list = super.preGet(id);
        OpeningWagon wagon;
        switch (getType()) {
            case COVERED:
                wagon = WagonFactory.createCoveredWagon((BigDecimal) list.get(0), (BigDecimal) list.get(1), id);
                break;
            case TANK:
                wagon = WagonFactory.createTankWagon((BigDecimal) list.get(0), (BigDecimal) list.get(1), id);
                break;
            case REFRIGERATOR:
                wagon = WagonFactory.createRefrigeratorWagon((BigDecimal) list.get(0), (BigDecimal) list.get(1), id);
                ((RefrigeratorWagon) wagon).setCurrentTemperature((BigDecimal) list.get(7));
                break;
            default:
                throw new IllegalStateException();
        }
        wagon.loadCargo(Math.toIntExact((Long) list.get(5)));
        if ((Boolean) list.get(6)) {
            wagon.openWagon();
        }

        Long num = (Long) list.get(2) != 0L ? (Long) list.get(2) : null;
        wagon.setNumberInComposition(num);

        String str = (String) list.get(4);
        wagon.setTrainId(null);
        if (str != null) {
            wagon.setTrainId(UUID.fromString(str));
        }
        return (T) wagon;
    }
}


