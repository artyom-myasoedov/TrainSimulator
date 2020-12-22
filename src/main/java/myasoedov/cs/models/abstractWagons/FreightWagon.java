package myasoedov.cs.models.abstractWagons;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public abstract class FreightWagon extends Wagon implements Serializable {
    protected final int maxCarrying;
    protected int currentCargoWeight;

    public FreightWagon(int weight, BigDecimal age, BigDecimal condition, int maxCarrying, UUID id) {
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
            throw new IllegalArgumentException("Недопустимый вес груза!");
        }
    }

    public void unloadCargo() {
        currentCargoWeight = 0;
    }

    public int getTotalWeight() {
        return weight + currentCargoWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FreightWagon that = (FreightWagon) o;
        return maxCarrying == that.maxCarrying && currentCargoWeight == that.currentCargoWeight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxCarrying, currentCargoWeight);
    }
}