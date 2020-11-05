package myasoedov.cs.trains;

import myasoedov.cs.models.trains.Train;
import myasoedov.cs.models.abstractWagons.Locomotive;
import myasoedov.cs.models.abstractWagons.PassengerWagon;

import java.util.List;

public class PassengerTrain<T extends PassengerWagon> extends Train<T> {
    private int totalNumberOfSeats;
    private int totalNumberOfPassengers;

    public PassengerTrain(Long id) {
        super(id);
        totalNumberOfSeats = 0;
        totalNumberOfPassengers = 0;
    }

    public PassengerTrain(List<? extends T> wagons, List<? extends Locomotive> locomotives, Long id) {
        super(wagons, locomotives, id);
        countTotalNumberOfSeats();
        countTotalNumberOfPassengers();
    }

    public PassengerTrain(List<? extends T> wagons, Long id) {
        super(wagons, id);
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
