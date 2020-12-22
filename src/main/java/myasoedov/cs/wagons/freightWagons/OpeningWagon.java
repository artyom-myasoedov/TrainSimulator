package myasoedov.cs.wagons.freightWagons;

import myasoedov.cs.wagons.locomotives.LocomotiveEngineConditions;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public abstract class OpeningWagon extends myasoedov.cs.models.abstractWagons.FreightWagon implements Serializable {
    protected boolean isOpened;

    public OpeningWagon(int weight, BigDecimal age, BigDecimal condition, int maxCarrying, UUID id) {
        super(weight, age, condition, maxCarrying, id);
        isOpened = false;
    }

    public boolean getIsOpened() {
        return isOpened;
    }

    public void loadCargo(int weight) {
        if (isOpened) {
            openWagon();
        }
        super.loadCargo(weight);
    }

    public void openWagon() {
        isOpened = true;
    }

    public void closeWagon() {
        isOpened = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OpeningWagon that = (OpeningWagon) o;
        return isOpened == that.isOpened;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isOpened);
    }
}
