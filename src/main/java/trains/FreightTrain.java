package trains;

import wagons.abstractWagons.FreightWagon;
import wagons.abstractWagons.PassengerWagon;
import wagons.abstractWagons.Wagon;

import java.util.LinkedList;

public class FreightTrain extends Train {
    private int totalMaxCarrying;
    private int totalCurrentCargoWeight;

    public FreightTrain() {
        totalMaxCarrying = 0;
        totalCurrentCargoWeight = 0;
    }

    public FreightTrain(LinkedList<Wagon> wagons) {
        super(wagons);
        if (locomotiveCount == 0) {
            totalMaxCarrying = 0;
            totalCurrentCargoWeight = 0;
        } else {
            if (checkToCorrectFreightTrain(wagons)) {
                countTotalMaxCarryingAndCargoWeight();
            } else {
                System.out.println("List of wagons contains freight wagons!");
                this.wagons = new LinkedList<>();
                locomotiveCount = 0;
                totalPower = 0;
                totalWagonsWeight = 0;
                totalMaxCarrying = 0;
                totalCurrentCargoWeight = 0;
            }
        }
    }

    public int getTotalMaxCarrying() {
        return totalMaxCarrying;
    }

    public int getTotalCurrentCargoWeight() {
        return totalCurrentCargoWeight;
    }

    public void addWagon(Wagon wagon) {
        if (!(wagon instanceof PassengerWagon)) {
            super.addWagon(wagon);
            if (wagon instanceof FreightWagon) {
                FreightWagon freightWagon = ((FreightWagon) wagon);
                totalMaxCarrying += freightWagon.getMaxCarrying();
                totalCurrentCargoWeight += freightWagon.getCurrentCargoWeight();
            }
        } else {
            System.out.println(" You can't add passenger wagon!");
        }
    }

    public Wagon unhookWagon() {
        FreightWagon freightWagon;
        Wagon wagon;
        if ((wagon = super.unhookWagon()) instanceof FreightWagon) {
            freightWagon = (FreightWagon) wagon;
            totalCurrentCargoWeight -= freightWagon.getCurrentCargoWeight();
            totalMaxCarrying -= freightWagon.getMaxCarrying();
        }
        return wagon;
    }

    private boolean checkToCorrectFreightTrain(LinkedList<Wagon> wagons) {
        for (Wagon wagon :
                wagons) {
            if (wagon instanceof PassengerWagon) {
                return false;
            }
        }
        return true;
    }

    private void countTotalMaxCarryingAndCargoWeight() {
        int i = locomotiveCount;
        while (i < wagons.size()) {
            FreightWagon freightWagon = (FreightWagon) wagons.get(i);
            totalCurrentCargoWeight += freightWagon.getCurrentCargoWeight();
            totalMaxCarrying += freightWagon.getMaxCarrying();
        }
    }
}
