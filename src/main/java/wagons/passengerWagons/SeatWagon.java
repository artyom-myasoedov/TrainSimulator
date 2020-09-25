package wagons.passengerWagons;

import java.math.BigDecimal;

public class SeatWagon extends wagons.abstractWagons.PassengerWagon {

    public SeatWagon(int weight, BigDecimal age, BigDecimal condition, int numberOfSeats) {
        super(weight, age, condition, numberOfSeats);
    }
}
