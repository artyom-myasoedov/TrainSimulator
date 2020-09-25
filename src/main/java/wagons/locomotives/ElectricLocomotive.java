package wagons.locomotives;

import wagons.Conditions;
import wagons.abstractWagons.Locomotive;

import java.math.BigDecimal;

public class ElectricLocomotive extends Locomotive {
    private Conditions powerGridConnection;

    public ElectricLocomotive(int weight, BigDecimal age, BigDecimal condition, BigDecimal maxSpeed, int power, Conditions powerGridConnection) {
        super(weight, age, condition, power, maxSpeed);
        this.powerGridConnection = powerGridConnection;
    }

    public boolean isPowerGridConnect() {
        return powerGridConnection != Conditions.DISABLED;
    }

    public void setPowerGridConnection(Conditions powerGridConnection) {
        this.powerGridConnection = powerGridConnection;
    }

    @Override
    public void startEngine() {
        if (powerGridConnection == Conditions.ENABLED) {
            System.out.println("Start electric engine");
        } else {
            System.out.println("Locomotive is not connected to the power grid!");
        }
    }

    @Override
    public void stopEngine() {
        System.out.println("Stop electric engine");
    }
}
