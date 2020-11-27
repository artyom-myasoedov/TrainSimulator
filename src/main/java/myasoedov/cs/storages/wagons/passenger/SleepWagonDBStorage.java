package myasoedov.cs.storages.wagons.passenger;

import myasoedov.cs.models.storages.wagons.PassengerWagonDBStorage;
import myasoedov.cs.storages.wagons.WagonType;
import myasoedov.cs.wagons.passengerWagons.SleepWagon;

public class SleepWagonDBStorage<T extends SleepWagon> extends PassengerWagonDBStorage<T> {

    public SleepWagonDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, WagonType.SLEEP);
    }

    public SleepWagonDBStorage() {
        super(WagonType.SLEEP);
    }
}
