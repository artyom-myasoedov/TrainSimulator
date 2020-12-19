package myasoedov.cs.trains;

import myasoedov.cs.models.trains.Train;
import myasoedov.cs.models.abstractWagons.Locomotive;
import myasoedov.cs.models.abstractWagons.PassengerWagon;

import java.util.List;
import java.util.UUID;

public class PassengerTrain extends Train<PassengerWagon> {
    private int totalNumberOfSeats;
    private int totalNumberOfPassengers;

    public PassengerTrain(UUID id) {
        super(id);
        totalNumberOfSeats = 0;
        totalNumberOfPassengers = 0;
    }

    public PassengerTrain(List<? extends PassengerWagon> wagons, List<? extends Locomotive> locomotives, UUID id) {
        super(wagons, locomotives, id);
        countTotalNumberOfSeats();
        countTotalNumberOfPassengers();
    }

    public PassengerTrain(List<? extends PassengerWagon> wagons, UUID id) {
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

    public void addHeadWagon(PassengerWagon wagon) {
            super.addHeadWagon(wagon);
            totalNumberOfSeats += wagon.getNumberOfSeats();
            totalNumberOfPassengers += wagon.getNumberOfPassengers();
    }
    public void addTailWagon(PassengerWagon wagon) {
        super.addTailWagon(wagon);
        totalNumberOfSeats += wagon.getNumberOfSeats();
        totalNumberOfPassengers += wagon.getNumberOfPassengers();
    }

    public PassengerWagon getWagon(int index) {
        return wagons.get(index);
    }

    public PassengerWagon unhookHeadWagon() {
        PassengerWagon wagon = super.unhookHeadWagon();
        totalNumberOfSeats -= wagon.getNumberOfSeats();
        totalNumberOfPassengers -= wagon.getNumberOfPassengers();
        return wagon;
    }

    public PassengerWagon unhookTailWagon() {
        PassengerWagon wagon = super.unhookTailWagon();
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
