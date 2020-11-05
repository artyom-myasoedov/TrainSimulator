package myasoedov.cs.models.storages.wagons;

import myasoedov.cs.models.Storable;
import myasoedov.cs.models.storages.wagons.FreightWagonDBStorage;
import myasoedov.cs.wagons.freightWagons.OpeningWagon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public abstract class OpeningWagonDBStorage extends FreightWagonDBStorage {
    public OpeningWagonDBStorage(String jdbcUrl, String userName, String userParol, String type) {
        super(jdbcUrl, userName, userParol, type);
    }

    public OpeningWagonDBStorage(String type) {
        super(type);
    }

    @Override
    public boolean save(Storable item) {
        if (super.save(item)) {
            OpeningWagon wagon = (OpeningWagon) item;
            try (Connection c = getConnection()) {
                PreparedStatement statement = c.prepareStatement("insert into OPENING_WAGONS (WAGON_ID, IS_OPENED, TRAIN_ID) values (?, ?, ?)");
                statement.setLong(1, item.getId());
                statement.setBoolean(2, wagon.getIsOpened());
                if (wagon.getTrainId() != null) {
                    statement.setLong(3, wagon.getTrainId());
                } else {
                    statement.setLong(3, Types.NULL);
                }
                statement.execute();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
        return false;

    }
}


