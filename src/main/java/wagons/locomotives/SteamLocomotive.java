package wagons.locomotives;

import wagons.abstractWagons.Locomotive;

import java.math.BigDecimal;

public class SteamLocomotive extends Locomotive {
    private BigDecimal volumeFuel;

    public SteamLocomotive(int weight, BigDecimal age, BigDecimal condition, BigDecimal maxSpeed, int power, BigDecimal volumeFuel) {
        super(weight, age, condition, power, maxSpeed);
        this.volumeFuel = volumeFuel;
    }

    public BigDecimal getVolumeFuel() {
        return volumeFuel;
    }

    public void setVolumeFuel(BigDecimal volumeFuel) {
        this.volumeFuel = volumeFuel;
    }

    @Override
    public void startEngine() {
        if (volumeFuel.compareTo(new BigDecimal('0')) != 0) {
            System.out.println("Start steam engine");
        } else {
            System.out.println("Not enough fuel!");
        }
    }

    @Override
    public void stopEngine() {
        System.out.println("Stop steam engine");

    }
}
