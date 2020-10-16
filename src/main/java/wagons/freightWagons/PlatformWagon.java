package wagons.freightWagons;

import java.math.BigDecimal;

public class PlatformWagon extends wagons.abstractWagons.FreightWagon {
    private final static int WEIGHT = 1500;
    private final static int MAX_CARRYING = 4000;

    public PlatformWagon(BigDecimal age, BigDecimal condition) {
        super(WEIGHT, age, condition, MAX_CARRYING);
    }

    @Override
    public void loadCargo(int weight) {
        super.loadCargo(weight);
        System.out.println("Cargo is loaded to platform wagon");
    }

    @Override
    public void unloadCargo() {
        super.unloadCargo();
        System.out.println("Cargo is unloaded from platform wagon");
    }
}
