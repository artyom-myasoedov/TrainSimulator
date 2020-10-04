package wagons.passengerWagons;

import java.math.BigDecimal;

public class SeatWagon extends wagons.abstractWagons.PassengerWagon {

    public SeatWagon(BigDecimal age, BigDecimal condition) {
        super(6000, age, condition, 60);
    }
}
