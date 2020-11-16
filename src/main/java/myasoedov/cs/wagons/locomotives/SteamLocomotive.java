package myasoedov.cs.wagons.locomotives;

import myasoedov.cs.models.abstractWagons.Locomotive;

import java.math.BigDecimal;
import java.util.UUID;

public class SteamLocomotive extends FuelLocomotive {
    private static final BigDecimal MAX_VOLUME_FUEL = BigDecimal.valueOf(120);
    private static final BigDecimal CONSUMPTION = BigDecimal.valueOf(2);
    private final static int WEIGHT = 9000;
    private final static int POWER = 60000;
    private final static BigDecimal MAX_SPEED = BigDecimal.valueOf(60);


    public SteamLocomotive(BigDecimal age, BigDecimal condition, BigDecimal volumeFuel, UUID id) {
        super(WEIGHT, age, condition, POWER, MAX_SPEED, id, MAX_VOLUME_FUEL, CONSUMPTION, volumeFuel);
    }

}
