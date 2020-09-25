package wagons.freightWagons;

import wagons.Conditions;

import java.math.BigDecimal;

public abstract class OpeningWagons extends wagons.abstractWagons.FreightWagon {
    protected Conditions isOpened;

    public OpeningWagons(int weight, BigDecimal age, BigDecimal condition, int maxCarrying) {
        super(weight, age, condition, maxCarrying);
        isOpened = Conditions.DISABLED;
    }

    public boolean getIsOpened() {
        return isOpened != Conditions.DISABLED;
    }

    protected void openWagon() {
        System.out.println("Wagon is opened");
        isOpened = Conditions.ENABLED;
    }

    protected void closeWagon() {
        System.out.println("Wagon is closed");
        isOpened = Conditions.DISABLED;
    }


}
