package myasoedov.cs.wagons.passengerWagons;

import myasoedov.cs.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.storages.wagons.passenger.RestaurantWagonDBStorage;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

public class RestaurantWagon extends myasoedov.cs.models.abstractWagons.PassengerWagon {
    private final static Storage<RestaurantWagon> storage = new RestaurantWagonDBStorage<>(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);


    public RestaurantWagon(BigDecimal age, BigDecimal condition, UUID id) {
        super(Integer.parseInt(Configs.WagonFillsProperties.getProperty("restaurantWagonWeight")), age, condition,
                Integer.parseInt(Configs.WagonFillsProperties.getProperty("restaurantNumberOfSeats")), id);
    }

    public static RestaurantWagon save(RestaurantWagon wagon) throws SQLException {
        storage.save(wagon);
        return wagon;
    }

    public void addPassengers(int numberOfPassengers) {
        this.numberOfPassengers = 0;
    }

}
