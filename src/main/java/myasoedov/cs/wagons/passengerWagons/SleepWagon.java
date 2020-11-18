package myasoedov.cs.wagons.passengerWagons;

import java.math.BigDecimal;
import java.util.UUID;

public class SleepWagon extends myasoedov.cs.models.abstractWagons.PassengerWagon {
    private final static int WEIGHT = 4500;
    private final static int NUMBER_OF_SEATS = 20;

    public SleepWagon(BigDecimal age, BigDecimal condition, UUID id) {
        super(WEIGHT, age, condition, NUMBER_OF_SEATS, id);
    }
}