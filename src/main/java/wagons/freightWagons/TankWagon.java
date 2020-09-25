package wagons.freightWagons;

import wagons.Conditions;

import java.math.BigDecimal;

public class TankWagon extends OpeningWagons {
    public TankWagon(int weight, BigDecimal age, BigDecimal condition, int maxCarrying) {
        super(weight, age, condition, maxCarrying);
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
            System.out.println("Overload!");
        }
    }

    @Override
    public void unloadCargo() {
        currentCargoWeight = 0;
        System.out.println("Cargo is unloaded from tank wagon");
    }
}
