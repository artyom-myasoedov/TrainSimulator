package myasoedov.cs.models.storages.wagons;

import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.models.Storable;
import myasoedov.cs.models.abstractWagons.Locomotive;
import myasoedov.cs.wagons.locomotives.FuelLocomotive;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class FuelLocomotiveDBStorage extends LocomotiveDBStorage {

    public FuelLocomotiveDBStorage(String jdbcUrl, String userName, String userParol, String type) {
        super(jdbcUrl, userName, userParol, type);
    }

    public FuelLocomotiveDBStorage(String type) {
        super(type);
    }

    @Override
    public boolean save(Storable item) throws SQLException {
        boolean isSaved = super.save(item);
        if (isSaved) {
            try (Connection c = getConnection()) {
                FuelLocomotive locomotive = (FuelLocomotive) item;
                PreparedStatement statement = c.prepareStatement("insert into FUEl_PARAMETERS (WAGON_ID, MAX_VOLUME_FUEL, VOLUME_OF_FUEL, CONSUMPTION) values (?, ?, ?, ?)");
                statement.setLong(1, locomotive.getId());
                statement.setBigDecimal(2, locomotive.getMaxVolumeFuel());
                statement.setBigDecimal(3, locomotive.getVolumeFuel());
                statement.setBigDecimal(4, locomotive.getConsumption());
                statement.executeUpdate();
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Storable get(Long id) {
        List<Object> list = super.preGet(id);
        try (Connection c = getConnection()) {
            ResultSet rs = c.prepareStatement("select VOLUME_OF_FUEL from FUEL_PARAMETERS where WAGON_ID = " + id.toString()).executeQuery();
            rs.next();
            Locomotive locomotive;
            switch (getType()) {
                case "Diesel":
                    locomotive = WagonFactory.createDieselLocomotive((BigDecimal) list.get(0), (BigDecimal) list.get(1), rs.getBigDecimal(1), id);
                    break;
                case "Steam":
                    locomotive = WagonFactory.createSteamLocomotive((BigDecimal) list.get(0), (BigDecimal) list.get(1), rs.getBigDecimal(1), id);
                    break;
                default:
                    throw new IllegalStateException();
            }
            if ((Long) list.get(2) != 0L) {
                locomotive.setNumberInComposition((Long) list.get(2));
            } else {
                locomotive.setNumberInComposition(null);
            }
            return locomotive;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
