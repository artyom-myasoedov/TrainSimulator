package wagons.freightWagons;

import wagons.Conditions;

import java.math.BigDecimal;

public class CoveredWagon extends OpeningWagons {
    public CoveredWagon(BigDecimal age, BigDecimal condition) {
        super(2500, age, condition, 2000);
    }

    @Override
    public void loadCargo(int weight) {
        super.loadCargo(weight);
        System.out.println("Cargo is loaded to covered wagon");
    }

    @Override
    public void unloadCargo() {
        super.unloadCargo();
        System.out.println("Cargo is unloaded from covered wagon");
    }
}
