package wagons.freightWagons;

import java.math.BigDecimal;

public class PlatformWagon extends wagons.abstractWagons.FreightWagon {
    public PlatformWagon(int weight, BigDecimal age, BigDecimal condition, int maxCarrying) {
        super(weight, age, condition, maxCarrying);
    }

    @Override
    public void loadCargo(int weight) {
        if (weight + currentCargoWeight < maxCarrying) {
            currentCargoWeight += weight;
            System.out.println("Cargo is loaded to platform wagon");
        } else {
            System.out.println("Overload!");
        }
    }

    @Override
    public void unloadCargo() {
        currentCargoWeight = 0;
        System.out.println("Cargo is unloaded from platform wagon");
    }
}
