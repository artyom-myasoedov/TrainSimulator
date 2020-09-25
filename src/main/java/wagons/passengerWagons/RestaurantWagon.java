package wagons.passengerWagons;

import java.math.BigDecimal;

public class RestaurantWagon extends wagons.abstractWagons.PassengerWagon {
    public RestaurantWagon(int weight, BigDecimal age, BigDecimal condition, int numberOfSeats) {
        super(weight, age, condition, numberOfSeats);
    }

    public void startCooking() {
        System.out.println("Restaurant wagon is started cooking");
    }

    public void stopCooking() {
        System.out.println("Restaurant wagon is stopped cooking");
    }
}
