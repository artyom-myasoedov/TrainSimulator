package myasoedov.cs.storages.wagons.passenger;

import myasoedov.cs.models.Storable;
import myasoedov.cs.models.storages.wagons.PassengerWagonDBStorage;
import myasoedov.cs.wagons.passengerWagons.CoupeWagon;

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
