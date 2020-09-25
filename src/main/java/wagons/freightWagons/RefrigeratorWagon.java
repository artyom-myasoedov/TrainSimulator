package wagons.freightWagons;

import wagons.Conditions;

import java.math.BigDecimal;

public class RefrigeratorWagon extends OpeningWagons {
    private BigDecimal currentTemperature;
    private final BigDecimal maxTemperature;
    private final BigDecimal minTemperature;

    public RefrigeratorWagon(int weight, BigDecimal age, BigDecimal condition, int maxCarrying, BigDecimal maxTemperature, BigDecimal minTemperature) {
        super(weight, age, condition, maxCarrying);
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        currentTemperature = new BigDecimal("0.00");
    }

    public BigDecimal getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(BigDecimal currentTemperature) {
        if (maxTemperature.compareTo(currentTemperature) >= 0 && minTemperature.compareTo(currentTemperature) <= 0)
            this.currentTemperature = currentTemperature;
    }

    public BigDecimal getMaxTemperature() {
        return maxTemperature;
    }

    public BigDecimal getMinTemperature() {
        return minTemperature;
    }

    @Override
    public void loadCargo(int weight) {
        if (isOpened == Conditions.DISABLED) {
            openWagon();
        }
        if (weight + currentCargoWeight < maxCarrying) {
            currentCargoWeight += weight;
            System.out.println("Cargo is loaded to refrigerator wagon");
        } else {
            System.out.println("Overload!");
        }
    }

    @Override
    public void unloadCargo() {
        currentCargoWeight = 0;
        System.out.println("Cargo is unloaded from refrigerator wagon");
    }
}
