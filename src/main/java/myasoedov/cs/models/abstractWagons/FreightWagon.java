package myasoedov.cs.models.abstractWagons;

import java.math.BigDecimal;

public abstract class FreightWagon extends Wagon {
    protected final int maxCarrying;
    protected int currentCargoWeight;

    public FreightWagon(int weight, BigDecimal age, BigDecimal condition, int maxCarrying, Long id) {
        super(weight, age, condition, id);
        this.maxCarrying = maxCarrying;
        currentCargoWeight = 0;
    }

    public int getMaxCarrying() {
        return maxCarrying;
    }

    public int getCurrentCargoWeight() {
        return currentCargoWeight;
    }

    public  void loadCargo(int weight) {
        if (weight + currentCargoWeight < maxCarrying) {
            currentCargoWeight += weight;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void unloadCargo() {
        currentCargoWeight = 0;
    }

    public int getTotalWeight() {
        return weight + currentCargoWeight;
    }
}