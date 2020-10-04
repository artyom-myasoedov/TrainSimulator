package wagons.freightWagons;

import wagons.Conditions;

import java.math.BigDecimal;

public class CoveredWagon extends OpeningWagons {
    public CoveredWagon(BigDecimal age, BigDecimal condition) {
        super(2500, age, condition, 2000);
    }

    @Override
    public void loadCargo(int weight) {
        if (isOpened == Conditions.DISABLED) {
            openWagon();
        }
        if (weight + currentCargoWeight < maxCarrying) {
            currentCargoWeight += weight;
            System.out.println("Cargo is loaded to covered wagon");
        } else {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public void unloadCargo() {
        currentCargoWeight = 0;
        System.out.println("Cargo is unloaded from covered wagon");
    }
}
