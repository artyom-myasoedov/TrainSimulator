package wagons.passengerWagons;

import java.math.BigDecimal;

public class SeatWagon extends wagons.abstractWagons.PassengerWagon {
    private final static int WEIGHT = 6000;
    private final static int NUMBER_OF_SEATS = 60;

    public SeatWagon(BigDecimal age, BigDecimal condition) {
        super(WEIGHT, age, condition, NUMBER_OF_SEATS);
    }
}
