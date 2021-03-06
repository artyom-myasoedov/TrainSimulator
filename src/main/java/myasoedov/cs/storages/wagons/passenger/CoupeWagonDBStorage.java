package myasoedov.cs.storages.wagons.passenger;

import myasoedov.cs.models.storages.wagons.PassengerWagonDBStorage;
import myasoedov.cs.storages.wagons.WagonType;
import myasoedov.cs.wagons.passengerWagons.CoupeWagon;

public class CoupeWagonDBStorage extends PassengerWagonDBStorage<CoupeWagon> {

    public CoupeWagonDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, WagonType.COUPE);
    }

    public CoupeWagonDBStorage() {
        super(WagonType.COUPE);
    }
}
