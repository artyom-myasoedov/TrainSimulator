package myasoedov.cs.models.abstractWagons;

import myasoedov.cs.wagons.locomotives.LocomotiveEngineConditions;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class Locomotive extends Wagon {
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
        System.out.println("This locomotive start moving forward");
    }

    public void moveBehind() {
        if (!isEngineWork()) {
            startEngine();
        }
        System.out.println("This locomotive start moving behind");
    }

    public void stopMoving() {
        System.out.println("This locomotive stop moving");
    }

}
