package myasoedov.cs.storages.wagons.passenger;

import myasoedov.cs.models.storages.wagons.PassengerWagonDBStorage;
import myasoedov.cs.storages.wagons.WagonType;
import myasoedov.cs.wagons.passengerWagons.SeatWagon;

public class SeatWagonDBStorage extends PassengerWagonDBStorage<SeatWagon> {

    public SeatWagonDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, WagonType.SEAT);
    }

    public SeatWagonDBStorage() {
        super(WagonType.SEAT);
    }
}
