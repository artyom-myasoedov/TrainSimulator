package myasoedov.cs.storages.wagons.passenger;

import myasoedov.cs.models.storages.wagons.PassengerWagonDBStorage;
import myasoedov.cs.storages.wagons.WagonType;
import myasoedov.cs.wagons.passengerWagons.RestaurantWagon;

public class RestaurantWagonDBStorage<T extends RestaurantWagon> extends PassengerWagonDBStorage<T> {
    private final static WagonType TYPE = WagonType.RESTAURANT;

    public RestaurantWagonDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, TYPE);
    }

    public RestaurantWagonDBStorage() {
        super(TYPE);
    }
}
