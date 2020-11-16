package myasoedov.cs.wagons.passengerWagons;

import java.math.BigDecimal;
import java.util.UUID;

public class SeatWagon extends myasoedov.cs.models.abstractWagons.PassengerWagon {
    private final static int WEIGHT = 6000;
    private final static int NUMBER_OF_SEATS = 60;

    public SeatWagon(BigDecimal age, BigDecimal condition, UUID id) {
        super(WEIGHT, age, condition, NUMBER_OF_SEATS, id);
    }
}
