package wagons.locomotives;

import wagons.abstractWagons.Locomotive;

import java.math.BigDecimal;

public class ElectricLocomotive extends Locomotive {
    private LocomotiveEngineConditions powerGridConnection;
    private final static int WEIGHT = 7000;
    private final static int POWER = 100000;
    private final static BigDecimal MAX_SPEED = BigDecimal.valueOf(100);

    public ElectricLocomotive(BigDecimal age, BigDecimal condition, LocomotiveEngineConditions powerGridConnection) {
        super(WEIGHT, age, condition, POWER, MAX_SPEED);
        this.powerGridConnection = powerGridConnection;
    }

    public boolean isPowerGridConnect() {
        return powerGridConnection == LocomotiveEngineConditions.ENABLED;
    }

    public void setPowerGridConnection(LocomotiveEngineConditions powerGridConnection) {
        this.powerGridConnection = powerGridConnection;
    }

    @Override
    public void startEngine() {
        if (powerGridConnection == LocomotiveEngineConditions.ENABLED) {
            System.out.println("Start electric engine");
            engine = LocomotiveEngineConditions.ENABLED;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void stopEngine() {
        super.stopEngine();
        System.out.println("Stop electric engine");
    }
}
