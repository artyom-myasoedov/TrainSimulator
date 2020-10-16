package wagons.passengerWagons;

import java.math.BigDecimal;

public class RestaurantWagon extends wagons.abstractWagons.PassengerWagon {
    private final static int WEIGHT = 8000;
    private final static int NUMBER_OF_SEATS = 0;

    public RestaurantWagon(BigDecimal age, BigDecimal condition) {
        super(WEIGHT, age, condition, NUMBER_OF_SEATS);
    }

    public void addPassengers(int numberOfPassengers) {
        this.numberOfPassengers = 0;
    }

    public void removePassengers(int numberOfPassengers) {
    }
}
