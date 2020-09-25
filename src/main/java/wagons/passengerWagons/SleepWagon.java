package wagons.passengerWagons;

import java.math.BigDecimal;

public class SleepWagon extends wagons.abstractWagons.PassengerWagon {
    public SleepWagon(int weight, BigDecimal age, BigDecimal condition, int numberOfSeats) {
        super(weight, age, condition, numberOfSeats);
    }
}
