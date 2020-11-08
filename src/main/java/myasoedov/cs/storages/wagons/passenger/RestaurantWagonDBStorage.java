package myasoedov.cs.storages.wagons.passenger;

import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.models.Storable;
import myasoedov.cs.models.storages.wagons.PassengerWagonDBStorage;
import myasoedov.cs.wagons.passengerWagons.CoupeWagon;
import myasoedov.cs.wagons.passengerWagons.RestaurantWagon;

import java.math.BigDecimal;
import java.util.List;

public class RestaurantWagonDBStorage extends PassengerWagonDBStorage {
    private final static String TYPE = "Restaurant";
    public RestaurantWagonDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, TYPE);
    }

    public RestaurantWagonDBStorage() {
        super(TYPE);
    }

    @Override
    public boolean save(Storable item) {
        if (item instanceof RestaurantWagon) {
            return super.save(item);
        } else {
            throw new IllegalArgumentException();
        }
    }


}
