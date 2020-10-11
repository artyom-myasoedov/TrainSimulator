package wagons.locomotives;

import wagons.Conditions;
import wagons.abstractWagons.Locomotive;

import java.math.BigDecimal;

public class SteamLocomotive extends Locomotive {
    private BigDecimal volumeFuel;
    private static final BigDecimal maxVolumeFuel = BigDecimal.valueOf(120);
    private static final BigDecimal consumption = BigDecimal.valueOf(2);

    public SteamLocomotive(BigDecimal age, BigDecimal condition, BigDecimal volumeFuel) {
        super(9000, age, condition, 60000, BigDecimal.valueOf(60));
        this.volumeFuel = volumeFuel;
    }

    public BigDecimal getVolumeFuel() {
        return volumeFuel;
    }

    public void setVolumeFuel(BigDecimal volumeFuel) {
        if (volumeFuel.compareTo(BigDecimal.valueOf(0)) < 0 || volumeFuel.compareTo(maxVolumeFuel) > 0) {
            throw new IllegalArgumentException();
        }
        this.volumeFuel = volumeFuel;
    }

    @Override
    public void startEngine() {
        if (volumeFuel.compareTo(BigDecimal.valueOf(2)) >= 0) {
            System.out.println("Start steam engine");
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
        System.out.println("Stop steam engine");
    }

    public void moveForward() {
        super.moveForward();
        volumeFuel = volumeFuel.subtract(consumption);
    }
}
