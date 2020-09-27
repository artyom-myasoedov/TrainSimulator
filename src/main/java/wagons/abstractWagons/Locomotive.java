package wagons.abstractWagons;

import wagons.Conditions;

import java.math.BigDecimal;

public abstract class Locomotive extends Wagon {
    protected final int power;
    protected final BigDecimal maxSpeed;
    protected Conditions engine;

    public Locomotive(int weight, BigDecimal age, BigDecimal condition, int power, BigDecimal maxSpeed) {
        super(weight, age, condition);
        this.power = power;
        this.maxSpeed = maxSpeed;
        engine = Conditions.DISABLED;
    }

    public int getPower() {
        return power;
    }

    public BigDecimal getMaxSpeed() {
        return maxSpeed;
    }

    public boolean isEngineWork() {
        return engine != Conditions.DISABLED;
    }

    public abstract void startEngine();

    public abstract void stopEngine();

    public void moveForward() {
        if (engine == Conditions.DISABLED) {
            startEngine();
        }
        System.out.println("This locomotive start moving next");
    }

    public void moveBehind() {
        if (engine == Conditions.DISABLED) {
            startEngine();
        }
        System.out.println("This locomotive start moving behind");
    }

    public void stopMoving() {
        System.out.println("This locomotive stop moving");
    }

}
