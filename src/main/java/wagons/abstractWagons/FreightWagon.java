package wagons.abstractWagons;

import java.math.BigDecimal;

public abstract class FreightWagon extends Wagon {
    protected final int maxCarrying;
    protected int currentCargoWeight;

    public FreightWagon(int weight, BigDecimal age, BigDecimal condition, int maxCarrying) {
        super(weight, age, condition);
        this.maxCarrying = maxCarrying;
        currentCargoWeight = 0;
    }

    public int getMaxCarrying() {
        return maxCarrying;
    }

    public int getCurrentCargoWeight() {
        return currentCargoWeight;
    }

    public abstract void loadCargo(int weight);

    public abstract void unloadCargo();

    public int getTotalWeight() {
        return weight + currentCargoWeight;
    }
}