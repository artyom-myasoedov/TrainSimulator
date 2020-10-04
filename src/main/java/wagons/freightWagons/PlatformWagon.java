package wagons.freightWagons;

import java.math.BigDecimal;

public class PlatformWagon extends wagons.abstractWagons.FreightWagon {
    public PlatformWagon(BigDecimal age, BigDecimal condition) {
        super(1500, age, condition, 4000);
    }

    @Override
    public void loadCargo(int weight) {
        if (weight + currentCargoWeight < maxCarrying) {
            currentCargoWeight += weight;
            System.out.println("Cargo is loaded to platform wagon");
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void unloadCargo() {
        currentCargoWeight = 0;
        System.out.println("Cargo is unloaded from platform wagon");
    }
}
