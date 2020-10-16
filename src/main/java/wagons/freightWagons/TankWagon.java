package wagons.freightWagons;


import java.math.BigDecimal;

public class TankWagon extends OpeningWagons {
    private final static int WEIGHT = 2500;
    private final static int MAX_CARRYING = 3000;

    public TankWagon(BigDecimal age, BigDecimal condition) {
        super(WEIGHT, age, condition, MAX_CARRYING);
    }

    @Override
    public void loadCargo(int weight) {
        super.loadCargo(weight);
        System.out.println("Cargo is loaded to tank wagon");
    }

    @Override
    public void unloadCargo() {
        super.unloadCargo();
        System.out.println("Cargo is unloaded from tank wagon");
    }
}
