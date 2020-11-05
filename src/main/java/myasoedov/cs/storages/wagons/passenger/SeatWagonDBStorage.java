package myasoedov.cs.storages.wagons.passenger;

import myasoedov.cs.models.Storable;
import myasoedov.cs.models.storages.wagons.PassengerWagonDBStorage;
import myasoedov.cs.wagons.passengerWagons.SeatWagon;

public class SeatWagonDBStorage extends PassengerWagonDBStorage {
    private final static String TYPE = "Seat";
    public SeatWagonDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, TYPE);
    }

    public SeatWagonDBStorage() {
        super(TYPE);
    }

    @Override
    public boolean save(Storable item) {
        if (item instanceof SeatWagon) {
            return super.save(item);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
