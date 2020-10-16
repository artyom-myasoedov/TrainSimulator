package wagons.freightWagons;

import wagons.locomotives.LocomotiveEngineConditions;

import java.math.BigDecimal;

public abstract class OpeningWagons extends wagons.abstractWagons.FreightWagon {
    protected LocomotiveEngineConditions isOpened;

    public OpeningWagons(int weight, BigDecimal age, BigDecimal condition, int maxCarrying) {
        super(weight, age, condition, maxCarrying);
        isOpened = LocomotiveEngineConditions.DISABLED;
    }

    public boolean getIsOpened() {
        return isOpened != LocomotiveEngineConditions.DISABLED;
    }

    public void loadCargo(int weight) {
        if (isOpened == LocomotiveEngineConditions.DISABLED) {
            openWagon();
        }
        super.loadCargo(weight);
    }

    protected void openWagon() {
        System.out.println("Wagon is opened");
        isOpened = LocomotiveEngineConditions.ENABLED;
    }

    protected void closeWagon() {
        System.out.println("Wagon is closed");
        isOpened = LocomotiveEngineConditions.DISABLED;
    }


}
