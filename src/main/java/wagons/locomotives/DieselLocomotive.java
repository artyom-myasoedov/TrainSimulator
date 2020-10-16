package wagons.locomotives;

import wagons.abstractWagons.Locomotive;

import java.math.BigDecimal;

public class DieselLocomotive extends Locomotive {
    private BigDecimal volumeFuel;
    private static final BigDecimal MAX_VOLUME_FUEL = BigDecimal.valueOf(100);
    private static final BigDecimal CONSUMPTION = BigDecimal.valueOf(1.5);
    private final static int WEIGHT = 7500;
    private final static int POWER = 80000;
    private final static BigDecimal MAX_SPEED = BigDecimal.valueOf(80);

    public DieselLocomotive(BigDecimal age, BigDecimal condition, BigDecimal volumeFuel) {
        super(WEIGHT, age, condition, POWER, MAX_SPEED);
        this.volumeFuel = volumeFuel;
    }

    public BigDecimal getVolumeFuel() {
        return volumeFuel;
    }

    public void setVolumeFuel(BigDecimal volumeFuel) {
        if (volumeFuel.compareTo(MAX_VOLUME_FUEL) > 0 || volumeFuel.compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new IllegalArgumentException();
        }
        this.volumeFuel = volumeFuel;
    }

    @Override
    public void startEngine() {
        if (volumeFuel.compareTo(BigDecimal.valueOf(1.5)) >= 0) {
            System.out.println("Start diesel engine");
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
        System.out.println("Stop diesel engine");
    }

    public void moveForward() {
        super.moveForward();
        volumeFuel = volumeFuel.subtract(CONSUMPTION);
    }
}
