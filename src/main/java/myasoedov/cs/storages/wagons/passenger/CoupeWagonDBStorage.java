package myasoedov.cs.storages.wagons.passenger;

import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.models.Storable;
import myasoedov.cs.models.storages.wagons.PassengerWagonDBStorage;
import myasoedov.cs.wagons.passengerWagons.CoupeWagon;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CoupeWagonDBStorage extends PassengerWagonDBStorage {
    private final static String TYPE = "Coupe";

    public CoupeWagonDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, TYPE);
    }

    public CoupeWagonDBStorage() {
        super(TYPE);
    }

    @Override
    public boolean save(Storable item) {
        if (item instanceof CoupeWagon) {
            return super.save(item);
        } else {
            throw new IllegalArgumentException();
        }
    }



}
