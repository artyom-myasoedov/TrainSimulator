package wagons.locomotives;

import wagons.abstractWagons.Locomotive;

import java.math.BigDecimal;

public class SteamLocomotive extends Locomotive {
    private BigDecimal volumeFuel;
    private static final BigDecimal maxVolumeFuel = new BigDecimal("120.00");
    private static final BigDecimal consumption = new BigDecimal("2.00");

    public SteamLocomotive(BigDecimal age, BigDecimal condition, BigDecimal volumeFuel) {
        super(9000, age, condition, 60000, new BigDecimal("60.00"));
        this.volumeFuel = volumeFuel;
    }

    public BigDecimal getVolumeFuel() {
        return volumeFuel;
    }

    public void setVolumeFuel(BigDecimal volumeFuel) {
        if (volumeFuel.compareTo(new BigDecimal("0")) < 0 || volumeFuel.compareTo(maxVolumeFuel) > 0) {
            throw new IllegalArgumentException();
        }
        this.volumeFuel = volumeFuel;
    }

    @Override
    public void startEngine() {
        if (volumeFuel.compareTo(new BigDecimal('0')) > 0) {
            System.out.println("Start steam engine");
            volumeFuel = volumeFuel.subtract(consumption);
            if (volumeFuel.compareTo(new BigDecimal('0')) < 0) {
                volumeFuel = new BigDecimal("0");
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void stopEngine() {
        System.out.println("Stop steam engine");
    }
}
