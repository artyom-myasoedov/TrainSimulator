package myasoedov.cs.wagons.passengerWagons;

import java.math.BigDecimal;

public class CoupeWagon extends myasoedov.cs.models.abstractWagons.PassengerWagon {
    private final static int WEIGHT = 5000;
    private final static int NUMBER_OF_SEATS = 40;

    public CoupeWagon(BigDecimal age, BigDecimal condition, Long id) {
        super(WEIGHT, age, condition, NUMBER_OF_SEATS, id);
    }
}
