package myasoedov.cs.models.abstractWagons;

import myasoedov.cs.wagons.locomotives.LocomotiveEngineConditions;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public abstract class Locomotive extends Wagon implements Serializable {
    protected final int power;
    protected final BigDecimal maxSpeed;
    protected LocomotiveEngineConditions engine;

    public Locomotive(int weight, BigDecimal age, BigDecimal condition, int power, BigDecimal maxSpeed, UUID id) {
        super(weight, age, condition, id);
        this.power = power;
        this.maxSpeed = maxSpeed;
        engine = LocomotiveEngineConditions.DISABLED;
    }

    public int getPower() {
        return power;
    }

    public BigDecimal getMaxSpeed() {
        return maxSpeed;
    }

    public boolean isEngineWork() {
        return engine != LocomotiveEngineConditions.DISABLED;
    }

    public abstract void startEngine();

    public void stopEngine() {
        engine = LocomotiveEngineConditions.DISABLED;
    }

    public void moveForward() {
        if (!isEngineWork()) {
            startEngine();
        }
    }

    public void moveBehind() {
        if (!isEngineWork()) {
            startEngine();
        }
    }

    public void stopMoving() {
    }

}
