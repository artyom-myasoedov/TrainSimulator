package wagons.locomotives;

import wagons.Conditions;
import wagons.abstractWagons.Locomotive;

import java.math.BigDecimal;

public class ElectricLocomotive extends Locomotive {
    private Conditions powerGridConnection;

    public ElectricLocomotive(BigDecimal age, BigDecimal condition, Conditions powerGridConnection) {
        super(7000, age, condition, 100000, new BigDecimal("100.00"));
        this.powerGridConnection = powerGridConnection;
    }

    public boolean isPowerGridConnect() {
        return powerGridConnection == Conditions.ENABLED;
    }

    public void setPowerGridConnection(Conditions powerGridConnection) {
        this.powerGridConnection = powerGridConnection;
    }

    @Override
    public void startEngine() {
        if (powerGridConnection == Conditions.ENABLED) {
            System.out.println("Start electric engine");
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void stopEngine() {
        System.out.println("Stop electric engine");
    }
}
