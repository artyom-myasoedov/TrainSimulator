package myasoedov.cs.trains;

import myasoedov.cs.models.trains.Train;
import myasoedov.cs.models.abstractWagons.FreightWagon;
import myasoedov.cs.models.abstractWagons.Locomotive;

import java.util.List;
import java.util.UUID;

public class FreightTrain extends Train<FreightWagon> {
    private int totalMaxCarrying;
    private int totalCurrentCargoWeight;

    public FreightTrain(UUID id) {
        super(id);
        totalMaxCarrying = 0;
        totalCurrentCargoWeight = 0;
    }

    public FreightTrain(List<? extends FreightWagon> wagons, List<? extends Locomotive> locomotives, UUID id) {
        super(wagons, locomotives, id);
        countTotalMaxCarrying();
        countTotalCurrentCargoWeight();
    }

    public FreightTrain(List<? extends FreightWagon> wagons, UUID id) {
        super(wagons, id);
        countTotalMaxCarrying();
        countTotalCurrentCargoWeight();
    }


    public int getTotalMaxCarrying() {
        return totalMaxCarrying;
    }

    public int getTotalCurrentCargoWeight() {
        countTotalCurrentCargoWeight();
        return totalCurrentCargoWeight;
    }

    public int getTotalWeight() {
        return getTotalWagonsWeight() + getTotalCurrentCargoWeight();
    }

    public void addHeadWagon(FreightWagon wagon) {
        super.addHeadWagon(wagon);
        totalCurrentCargoWeight += wagon.getCurrentCargoWeight();
        totalMaxCarrying += wagon.getMaxCarrying();
    }

    public void addTailWagon(FreightWagon wagon) {
        super.addTailWagon(wagon);
        totalCurrentCargoWeight += wagon.getCurrentCargoWeight();
        totalMaxCarrying += wagon.getMaxCarrying();
    }

    public FreightWagon getWagon(int index) {
        return wagons.get(index);
    }

    public FreightWagon unhookHeadWagon() {
        FreightWagon wagon = super.unhookHeadWagon();
        totalMaxCarrying -= wagon.getMaxCarrying();
        totalCurrentCargoWeight -= wagon.getCurrentCargoWeight();
        return wagon;
    }

    public FreightWagon unhookTailWagon() {
        FreightWagon wagon = super.unhookTailWagon();
        totalMaxCarrying -= wagon.getMaxCarrying();
        totalCurrentCargoWeight -= wagon.getCurrentCargoWeight();
        return wagon;
    }

    private void countTotalMaxCarrying() {
        totalMaxCarrying = wagons.stream().reduce(0, (x, y) -> x + y.getMaxCarrying(), Integer::sum);
    }

    private void countTotalCurrentCargoWeight() {
        totalCurrentCargoWeight = wagons.stream().reduce(0, (x, y) -> x + y.getCurrentCargoWeight(), Integer::sum);
    }
}
