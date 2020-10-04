package wagons.freightWagons;

import wagons.Conditions;

import java.math.BigDecimal;

public class TankWagon extends OpeningWagons {
    public TankWagon(BigDecimal age, BigDecimal condition) {
        super(2500, age, condition, 3000);
    }

    @Override
    public void loadCargo(int weight) {
        if (isOpened == Conditions.DISABLED) {
            openWagon();
        }
        if (weight + currentCargoWeight < maxCarrying) {
            currentCargoWeight += weight;
            System.out.println("Cargo is loaded to tank wagon");
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void unloadCargo() {
        currentCargoWeight = 0;
        System.out.println("Cargo is unloaded from tank wagon");
    }
}
