package myasoedov.cs.models.abstractWagons;

import myasoedov.cs.wagons.locomotives.LocomotiveEngineConditions;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Locomotive that = (Locomotive) o;
        return power == that.power && Objects.equals(maxSpeed, that.maxSpeed) && engine == that.engine;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), power, maxSpeed, engine);
    }
}
