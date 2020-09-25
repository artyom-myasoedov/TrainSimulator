package wagons.freightWagons;

import wagons.Conditions;

import java.math.BigDecimal;

public class CoveredWagon extends OpeningWagons {
    public CoveredWagon(int weight, BigDecimal age, BigDecimal condition, int maxCarrying) {
        super(weight, age, condition, maxCarrying);
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
            System.out.println("Overload!");
        }

    }

    @Override
    public void unloadCargo() {
        currentCargoWeight = 0;
        System.out.println("Cargo is unloaded from covered wagon");
    }
}
