package wagons.passengerWagons;

import java.math.BigDecimal;

public class CoupeWagon extends wagons.abstractWagons.PassengerWagon {

    public CoupeWagon(BigDecimal age, BigDecimal condition) {
        super(5000, age, condition, 40);
    }
}
