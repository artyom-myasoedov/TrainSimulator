package wagons.freightWagons;


import java.math.BigDecimal;

public class TankWagon extends OpeningWagons {
    public TankWagon(BigDecimal age, BigDecimal condition) {
        super(2500, age, condition, 3000);
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
