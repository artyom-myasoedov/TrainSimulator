package trains;

import wagons.abstractWagons.Locomotive;
import wagons.abstractWagons.PassengerWagon;

import java.util.Iterator;
import java.util.List;

public class PassengerTrain<T extends PassengerWagon> extends Train<T> {
    private int totalNumberOfSeats;
    private int totalNumberOfPassengers;

    public PassengerTrain() {
        super();
        totalNumberOfSeats = 0;
        totalNumberOfPassengers = 0;
    }

    public PassengerTrain(List<T> wagons, List<Locomotive> locomotives) {
        super(wagons, locomotives);
        countTotalNumberOfSeats();
        countTotalNumberOfPassengers();
    }

    public PassengerTrain(List<T> wagons) {
        super(wagons);
        countTotalNumberOfSeats();
        countTotalNumberOfPassengers();
    }

    public int getTotalWeight() {
        return getTotalWagonsWeight();
    }

    public int getTotalNumberOfSeats() {
        return totalNumberOfSeats;
    }

    public int getTotalNumberOfPassengers() {
        countTotalNumberOfPassengers();
        return totalNumberOfPassengers;
    }

    public void addHeadWagon(T wagon) {
            super.addHeadWagon(wagon);
            totalNumberOfSeats += wagon.getNumberOfSeats();
            totalNumberOfPassengers += wagon.getNumberOfPassengers();
    }
    public void addTailWagon(T wagon) {
        super.addTailWagon(wagon);
        totalNumberOfSeats += wagon.getNumberOfSeats();
        totalNumberOfPassengers += wagon.getNumberOfPassengers();
    }

    public T getWagon(int index) {
        return wagons.get(index);
    }

    public T unhookHeadWagon() {
        T wagon = super.unhookHeadWagon();
        totalNumberOfSeats -= wagon.getNumberOfSeats();
        totalNumberOfPassengers -= wagon.getNumberOfPassengers();
        return wagon;
    }

    public T unhookTailWagon() {
        T wagon = super.unhookTailWagon();
        totalNumberOfSeats -= wagon.getNumberOfSeats();
        totalNumberOfPassengers -= wagon.getNumberOfPassengers();
        return wagon;
    }

    private void countTotalNumberOfSeats() {
        totalNumberOfSeats = wagons.stream().reduce(0, (x, y) -> x + y.getNumberOfSeats(), Integer::sum);
    }

    private void countTotalNumberOfPassengers() {
        totalNumberOfPassengers = wagons.stream().reduce(0, (x, y) -> x + y.getNumberOfPassengers(), Integer::sum);
    }
}
