package wagons.locomotives;

import wagons.abstractWagons.Locomotive;

import java.math.BigDecimal;

public class SteamLocomotive extends Locomotive {
    private BigDecimal volumeFuel;
    private static final BigDecimal MAX_VOLUME_FUEL = BigDecimal.valueOf(120);
    private static final BigDecimal CONSUMPTION = BigDecimal.valueOf(2);
    private final static int WEIGHT = 9000;
    private final static int POWER = 60000;
    private final static BigDecimal MAX_SPEED = BigDecimal.valueOf(60);


    public SteamLocomotive(BigDecimal age, BigDecimal condition, BigDecimal volumeFuel) {
        super(WEIGHT, age, condition, POWER, MAX_SPEED);
        this.volumeFuel = volumeFuel;
    }

    public BigDecimal getVolumeFuel() {
        return volumeFuel;
    }

    public void setVolumeFuel(BigDecimal volumeFuel) {
        if (volumeFuel.compareTo(BigDecimal.valueOf(0)) < 0 || volumeFuel.compareTo(MAX_VOLUME_FUEL) > 0) {
            throw new IllegalArgumentException();
        }
        this.volumeFuel = volumeFuel;
    }

    @Override
    public void startEngine() {
        if (volumeFuel.compareTo(BigDecimal.valueOf(2)) >= 0) {
            System.out.println("Start steam engine");
            volumeFuel = volumeFuel.subtract(CONSUMPTION);
            engine = LocomotiveEngineConditions.ENABLED;
            if (volumeFuel.compareTo(BigDecimal.valueOf(0)) < 0) {
                volumeFuel = BigDecimal.valueOf(0);
                stopEngine();
            }
        } else {
            throw new IllegalArgumentException("Not enough fuel");
        }
    }

    @Override
    public void stopEngine() {
        super.stopEngine();
        System.out.println("Stop steam engine");
    }

    public void moveForward() {
        super.moveForward();
        volumeFuel = volumeFuel.subtract(CONSUMPTION);
    }
}
