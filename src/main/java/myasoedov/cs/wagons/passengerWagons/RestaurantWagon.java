package myasoedov.cs.wagons.passengerWagons;

import myasoedov.cs.models.storages.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.storages.wagons.passenger.RestaurantWagonDBStorage;
import myasoedov.cs.storages.wagons.passenger.SeatWagonDBStorage;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

public class RestaurantWagon extends myasoedov.cs.models.abstractWagons.PassengerWagon {
    private final static int WEIGHT = 8000;
    private final static int NUMBER_OF_SEATS = 0;
    private final static Storage<RestaurantWagon> storage = new RestaurantWagonDBStorage<>(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);


    public RestaurantWagon(BigDecimal age, BigDecimal condition, UUID id) {
        super(WEIGHT, age, condition, NUMBER_OF_SEATS, id);
    }

    public static RestaurantWagon save(RestaurantWagon wagon) throws SQLException {
        storage.save(wagon);
        return wagon;
    }

    public void addPassengers(int numberOfPassengers) {
        this.numberOfPassengers = 0;
    }

}
