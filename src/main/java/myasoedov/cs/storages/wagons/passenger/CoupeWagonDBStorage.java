package myasoedov.cs.storages.wagons.passenger;

import myasoedov.cs.models.storages.wagons.PassengerWagonDBStorage;
import myasoedov.cs.storages.wagons.WagonType;
import myasoedov.cs.wagons.passengerWagons.CoupeWagon;

public class CoupeWagonDBStorage<T extends CoupeWagon> extends PassengerWagonDBStorage<T> {
    private final static WagonType TYPE = WagonType.COUPE;

    public CoupeWagonDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, TYPE);
    }

    public CoupeWagonDBStorage() {
        super(TYPE);
    }
}
