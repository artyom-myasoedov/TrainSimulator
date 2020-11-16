package myasoedov.cs.storages.wagons.passenger;

import myasoedov.cs.models.storages.wagons.PassengerWagonDBStorage;
import myasoedov.cs.storages.wagons.WagonType;
import myasoedov.cs.wagons.passengerWagons.SeatWagon;

public class SeatWagonDBStorage<T extends SeatWagon> extends PassengerWagonDBStorage<T> {
    private final static WagonType TYPE = WagonType.SEAT;

    public SeatWagonDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, TYPE);
    }

    public SeatWagonDBStorage() {
        super(TYPE);
    }
}
