package myasoedov.cs.wagons.freightWagons;

import myasoedov.cs.wagons.locomotives.LocomotiveEngineConditions;

import java.math.BigDecimal;

public abstract class OpeningWagon extends myasoedov.cs.models.abstractWagons.FreightWagon {
    protected boolean isOpened;

    public OpeningWagon(int weight, BigDecimal age, BigDecimal condition, int maxCarrying, Long id) {
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

    protected void openWagon() {
        System.out.println("Wagon is opened");
        isOpened = true;
    }

    protected void closeWagon() {
        System.out.println("Wagon is closed");
        isOpened = false;
    }


}
