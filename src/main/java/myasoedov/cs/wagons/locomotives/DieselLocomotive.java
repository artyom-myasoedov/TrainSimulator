package myasoedov.cs.wagons.locomotives;

import myasoedov.cs.models.abstractWagons.Locomotive;

import java.math.BigDecimal;

public class DieselLocomotive extends FuelLocomotive {
    private static final BigDecimal MAX_VOLUME_FUEL = BigDecimal.valueOf(100);
    private static final BigDecimal CONSUMPTION = BigDecimal.valueOf(1.5);
    private final static int WEIGHT = 7500;
    private final static int POWER = 80000;
    private final static BigDecimal MAX_SPEED = BigDecimal.valueOf(80);

    public DieselLocomotive(BigDecimal age, BigDecimal condition, BigDecimal volumeFuel, Long id) {
        super(WEIGHT, age, condition, POWER, MAX_SPEED, id, MAX_VOLUME_FUEL, CONSUMPTION, volumeFuel);
    }
}
