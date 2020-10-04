package wagons.locomotives;

import wagons.abstractWagons.Locomotive;

import java.math.BigDecimal;

public class DieselLocomotive extends Locomotive {
    private BigDecimal volumeFuel;
    private static final BigDecimal maxVolumeFuel = new BigDecimal("100.00");
    private static final BigDecimal consumption = new BigDecimal("1.50");

    public DieselLocomotive(BigDecimal age, BigDecimal condition, BigDecimal volumeFuel) {
        super(7500, age, condition, 80000, new BigDecimal("80.00"));
        this.volumeFuel = volumeFuel;
    }

    public BigDecimal getVolumeFuel() {
        return volumeFuel;
    }

    public void setVolumeFuel(BigDecimal volumeFuel) {
        if (volumeFuel.compareTo(maxVolumeFuel) > 0 || volumeFuel.compareTo(new BigDecimal("0")) < 0) {
            throw new IllegalArgumentException();
        }
        this.volumeFuel = volumeFuel;
    }

    @Override
    public void startEngine() {
        if (volumeFuel.compareTo(new BigDecimal('0')) > 0) {
            System.out.println("Start diesel engine");
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
        System.out.println("Stop diesel engine");
    }
}
