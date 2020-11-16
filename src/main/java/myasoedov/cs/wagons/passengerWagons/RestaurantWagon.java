package myasoedov.cs.wagons.passengerWagons;

import java.math.BigDecimal;
import java.util.UUID;

public class RestaurantWagon extends myasoedov.cs.models.abstractWagons.PassengerWagon {
    private final static int WEIGHT = 8000;
    private final static int NUMBER_OF_SEATS = 0;

    public RestaurantWagon(BigDecimal age, BigDecimal condition, UUID id) {
        super(WEIGHT, age, condition, NUMBER_OF_SEATS, id);
    }

    public void addPassengers(int numberOfPassengers) {
        this.numberOfPassengers = 0;
    }

    public void removePassengers(int numberOfPassengers) {
    }
}
