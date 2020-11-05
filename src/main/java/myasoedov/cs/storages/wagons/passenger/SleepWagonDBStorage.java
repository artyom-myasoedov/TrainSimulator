package myasoedov.cs.storages.wagons.passenger;

import myasoedov.cs.models.Storable;
import myasoedov.cs.models.storages.wagons.PassengerWagonDBStorage;
import myasoedov.cs.wagons.passengerWagons.SleepWagon;

public class SleepWagonDBStorage extends PassengerWagonDBStorage {
    private final static String TYPE = "Sleep";

    public SleepWagonDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, TYPE);
    }

    public SleepWagonDBStorage() {
        super(TYPE);
    }

    @Override
    public boolean save(Storable item) {
        if (item instanceof SleepWagon) {
            return super.save(item);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
