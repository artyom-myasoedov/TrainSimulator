package trains;

import wagons.abstractWagons.FreightWagon;
import wagons.abstractWagons.Locomotive;

import java.util.Iterator;
import java.util.List;

public class FreightTrain<T extends FreightWagon> extends Train<T> {
    private int totalMaxCarrying;
    private int totalCurrentCargoWeight;

    public FreightTrain() {
        super();
        totalMaxCarrying = 0;
        totalCurrentCargoWeight = 0;
    }

    public FreightTrain(List<T> wagons, List<Locomotive> locomotives) {
        super(wagons, locomotives);
        countTotalMaxCarrying();
        countTotalCurrentCargoWeight();
    }

    public FreightTrain(List<T> wagons) {
        super(wagons);
        countTotalMaxCarrying();
        countTotalCurrentCargoWeight();
    }

    public int getTotalMaxCarrying() {
        return totalMaxCarrying;
    }

    public int getTotalCurrentCargoWeight() {
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
        totalMaxCarrying = wagons.stream().reduce(0, (x ,y) -> x + y.getMaxCarrying(), Integer::sum);
    }

    private void countTotalCurrentCargoWeight() {
        totalCurrentCargoWeight = wagons.stream().reduce(0, (x ,y) -> x + y.getCurrentCargoWeight(), Integer::sum);
    }

    @Override
    public Iterator<T> iterator() {
        return wagons.iterator();
    }
}
