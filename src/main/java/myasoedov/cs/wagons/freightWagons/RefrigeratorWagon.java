package myasoedov.cs.wagons.freightWagons;

import java.math.BigDecimal;
import java.util.UUID;

public class RefrigeratorWagon extends OpeningWagon {
    private BigDecimal currentTemperature;
    private final BigDecimal MAX_TEMPERATURE = BigDecimal.valueOf(25);
    private final BigDecimal MIN_TEMPERATURE = BigDecimal.valueOf(-25);
    private final static int WEIGHT = 3000;
    private final static int MAX_CARRYING = 1500;


    public RefrigeratorWagon(BigDecimal age, BigDecimal condition, UUID id) {
        super(WEIGHT, age, condition, MAX_CARRYING, id);
        currentTemperature = BigDecimal.valueOf(0);
    }

    public BigDecimal getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(BigDecimal currentTemperature) {
        if (MAX_TEMPERATURE.compareTo(currentTemperature) >= 0 && MIN_TEMPERATURE.compareTo(currentTemperature) <= 0) {
            this.currentTemperature = currentTemperature;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public BigDecimal getMaxTemperature() {
        return MAX_TEMPERATURE;
    }

    public BigDecimal getMinTemperature() {
        return MIN_TEMPERATURE;
    }

    @Override
    public void loadCargo(int weight) {
        super.loadCargo(weight);
        System.out.println("Cargo is loaded to refrigerator wagon");
    }

    @Override
    public void unloadCargo() {
        super.unloadCargo();
        System.out.println("Cargo is unloaded from refrigerator wagon");
    }
}
