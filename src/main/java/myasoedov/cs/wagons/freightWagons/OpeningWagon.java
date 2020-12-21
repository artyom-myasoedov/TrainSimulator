package myasoedov.cs.wagons.freightWagons;

import myasoedov.cs.wagons.locomotives.LocomotiveEngineConditions;

import java.io.Serializable;
import java.math.BigDecimal;
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
        System.out.println("Wagon is opened");
        isOpened = true;
    }

    public void closeWagon() {
        System.out.println("Wagon is closed");
        isOpened = false;
    }


}
