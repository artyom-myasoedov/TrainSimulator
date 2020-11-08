package myasoedov.cs.storages.wagons.locomotives;

import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.models.Storable;
import myasoedov.cs.models.abstractWagons.Locomotive;
import myasoedov.cs.models.storages.wagons.LocomotiveDBStorage;
import myasoedov.cs.wagons.locomotives.ElectricLocomotive;
import myasoedov.cs.wagons.locomotives.LocomotiveEngineConditions;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ElectricLocomotiveDBStorage extends LocomotiveDBStorage {
    private final static String TYPE = "Electric";

    public ElectricLocomotiveDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, TYPE);
    }

    public ElectricLocomotiveDBStorage() {
        super(TYPE);
    }

    @Override
    public boolean save(Storable item) throws SQLException {
        if (item instanceof ElectricLocomotive) {
            try (Connection c = getConnection()) {
                PreparedStatement statement = c.prepareStatement("insert into ELECTRIC_GRID_CONDITION (WAGON_ID, CONDITION) values (?, ?)");
                statement.setLong(1, item.getId());
                statement.setBoolean(2, ((ElectricLocomotive) item).isPowerGridConnect());
                statement.execute();
            }
            return super.save(item);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Storable get(Long id) {
        List<Object> list = super.preGet(id);
        try (Connection c = getConnection()) {
            ResultSet rs = c.prepareStatement("select CONDITION from ELECTRIC_GRID_CONDITION where WAGON_ID = " + id.toString()).executeQuery();
            rs.next();
            LocomotiveEngineConditions cond = LocomotiveEngineConditions.DISABLED;
            if (rs.getBoolean(1)) {
                cond = LocomotiveEngineConditions.ENABLED;
            }
            ElectricLocomotive locomotive = WagonFactory.createElectricLocomotive((BigDecimal) list.get(0), (BigDecimal) list.get(1), cond, id);
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
