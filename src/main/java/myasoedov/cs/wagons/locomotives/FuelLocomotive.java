package myasoedov.cs.wagons.locomotives;

import myasoedov.cs.models.abstractWagons.Locomotive;

import java.math.BigDecimal;

public abstract class FuelLocomotive extends Locomotive {
    private BigDecimal volumeFuel;
    private final BigDecimal maxVolumeFuel;
    private final BigDecimal consumption;


    public FuelLocomotive(int weight, BigDecimal age, BigDecimal condition, int power, BigDecimal maxSpeed, Long id, BigDecimal maxVolumeFuel, BigDecimal consumption, BigDecimal volumeFuel) {
        super(weight, age, condition, power, maxSpeed, id);
        this.maxVolumeFuel = maxVolumeFuel;
        this.consumption = consumption;
        this.volumeFuel = volumeFuel;
    }

    public BigDecimal getVolumeFuel() {
        return volumeFuel;
    }

    public BigDecimal getMaxVolumeFuel() {
        return maxVolumeFuel;
    }

    public BigDecimal getConsumption() {
        return consumption;
    }

    public void setVolumeFuel(BigDecimal volumeFuel) {
        if (volumeFuel.compareTo(BigDecimal.valueOf(0)) < 0 || volumeFuel.compareTo(maxVolumeFuel) > 0) {
            throw new IllegalArgumentException();
        }
        this.volumeFuel = volumeFuel;
    }

    @Override
    public void startEngine() {
        if (checkEnoughFuel()) {
            volumeFuel = volumeFuel.subtract(consumption);
            engine = LocomotiveEngineConditions.ENABLED;
        } else {
            throw new IllegalStateException("Not enough fuel");
        }
    }

    @Override
    public void stopEngine() {
        super.stopEngine();
    }

    public void moveForward() {
        if (checkEnoughFuel()) {
            super.moveForward();
            volumeFuel = volumeFuel.subtract(consumption);
        } else {
            stopEngine();
        }
    }

    public void moveBehind() {
        if (checkEnoughFuel()) {
            super.moveBehind();
            volumeFuel = volumeFuel.subtract(consumption);
        } else {
            stopEngine();
        }
    }

    private boolean checkEnoughFuel() {
        return volumeFuel.compareTo(consumption) >= 0;
    }
}
