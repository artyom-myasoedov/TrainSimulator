package wagons.freightWagons;

import java.math.BigDecimal;

public class CoveredWagon extends OpeningWagons {
    private final static int WEIGHT = 2500;
    private final static int MAX_CARRYING = 2000;

    public CoveredWagon(BigDecimal age, BigDecimal condition) {
        super(WEIGHT, age, condition, MAX_CARRYING);
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
