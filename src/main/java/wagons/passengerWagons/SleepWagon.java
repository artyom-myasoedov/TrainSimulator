package wagons.passengerWagons;

import java.math.BigDecimal;

public class SleepWagon extends wagons.abstractWagons.PassengerWagon {
    public SleepWagon(BigDecimal age, BigDecimal condition) {
        super(4500, age, condition, 20);
    }
}
