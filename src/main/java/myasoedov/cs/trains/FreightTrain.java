package myasoedov.cs.trains;

import myasoedov.cs.models.trains.Train;
import myasoedov.cs.models.abstractWagons.FreightWagon;
import myasoedov.cs.models.abstractWagons.Locomotive;

import java.util.List;

public class FreightTrain<T extends FreightWagon> extends Train<T> {
    private int totalMaxCarrying;
    private int totalCurrentCargoWeight;

    public FreightTrain(Long id) {
        super(id);
        totalMaxCarrying = 0;
        totalCurrentCargoWeight = 0;
    }

    public FreightTrain(List<? extends T> wagons, List<? extends Locomotive> locomotives, Long id) {
        super(wagons, locomotives, id);
        countTotalMaxCarrying();
        countTotalCurrentCargoWeight();
    }

    public FreightTrain(List<? extends T> wagons, Long id) {
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

    public void addHeadWagon(T wagon) {
        super.addHeadWagon(wagon);
        totalCurrentCargoWeight += wagon.getCurrentCargoWeight();
        totalMaxCarrying += wagon.getMaxCarrying();
    }

    public void addTailWagon(T wagon) {
        super.addTailWagon(wagon);
        totalCurrentCargoWeight += wagon.getCurrentCargoWeight();
        totalMaxCarrying += wagon.getMaxCarrying();
    }

    public T getWagon(int index) {
        return wagons.get(index);
    }

    public T unhookHeadWagon() {
        T wagon = super.unhookHeadWagon();
        totalMaxCarrying -= wagon.getMaxCarrying();
        totalCurrentCargoWeight -= wagon.getCurrentCargoWeight();
        return wagon;
    }

    public T unhookTailWagon() {
        T wagon = super.unhookTailWagon();
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
