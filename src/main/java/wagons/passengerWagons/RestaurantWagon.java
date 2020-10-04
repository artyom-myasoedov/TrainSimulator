package wagons.passengerWagons;

import java.math.BigDecimal;

public class RestaurantWagon extends wagons.abstractWagons.PassengerWagon {
    public RestaurantWagon(BigDecimal age, BigDecimal condition) {
        super(8000, age, condition, 0);
    }
}
