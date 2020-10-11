package wagons.locomotives;

import wagons.Conditions;
import wagons.abstractWagons.Locomotive;

import java.math.BigDecimal;

public class DieselLocomotive extends Locomotive {
    private BigDecimal volumeFuel;
    private static final BigDecimal maxVolumeFuel = BigDecimal.valueOf(100);
    private static final BigDecimal consumption = BigDecimal.valueOf(1.5);

    public DieselLocomotive(BigDecimal age, BigDecimal condition, BigDecimal volumeFuel) {
        super(7500, age, condition, 80000, BigDecimal.valueOf(80));
        this.volumeFuel = volumeFuel;
    }

    public BigDecimal getVolumeFuel() {
        return volumeFuel;
    }

    public void setVolumeFuel(BigDecimal volumeFuel) {
        if (volumeFuel.compareTo(maxVolumeFuel) > 0 || volumeFuel.compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new IllegalArgumentException();
        }
        this.volumeFuel = volumeFuel;
    }

    @Override
    public void startEngine() {
        if (volumeFuel.compareTo(BigDecimal.valueOf(1.5)) >= 0) {
            System.out.println("Start diesel engine");
            volumeFuel = volumeFuel.subtract(consumption);
            engine = Conditions.ENABLED;
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
        volumeFuel = volumeFuel.subtract(consumption);
    }
}
