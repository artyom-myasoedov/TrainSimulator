package trains;

import wagons.abstractWagons.FreightWagon;
import wagons.abstractWagons.PassengerWagon;
import wagons.abstractWagons.Wagon;

import java.util.LinkedList;

public class PassengerTrain extends Train {
    private int totalNumberOfSeats;
    private int totalNumberOfPassengers;

    public PassengerTrain() {
        super();
        totalNumberOfSeats = 0;
        totalNumberOfPassengers = 0;
    }

    public PassengerTrain(LinkedList<Wagon> wagons) {
        super(wagons);
        if (locomotiveCount == 0) {
            totalNumberOfSeats = 0;
            totalNumberOfPassengers = 0;
        } else {
            if (checkToCorrectPassengerTrain(wagons)) {
                countTotalNumberOfSeatsAndPassengers();
            } else {
                System.out.println("List of wagons contains freight wagons!");
                this.wagons = new LinkedList<>();
                locomotiveCount = 0;
                totalPower = 0;
                totalWagonsWeight = 0;
                totalNumberOfSeats = 0;
                totalNumberOfPassengers = 0;
            }
        }
    }

    public int getTotalNumberOfSeats() {
        return totalNumberOfSeats;
    }

    public int getTotalNumberOfPassengers() {
        return totalNumberOfPassengers;
    }

    public void addWagon(Wagon wagon) {
        if (!(wagon instanceof FreightWagon)) {
            super.addWagon(wagon);
            if (wagon instanceof PassengerWagon) {
                PassengerWagon passengerWagon = ((PassengerWagon) wagon);
                totalNumberOfSeats += passengerWagon.getNumberOfSeats();
                totalNumberOfPassengers += passengerWagon.getNumberOfPassengers();
            }
        } else {
            System.out.println(" You can't add freight wagon!");
        }
    }

    public Wagon unhookWagon() {
        PassengerWagon passengerWagon;
        Wagon wagon;
        if ((wagon = super.unhookWagon()) instanceof PassengerWagon) {
            passengerWagon = (PassengerWagon) wagon;
            totalNumberOfPassengers -= passengerWagon.getNumberOfPassengers();
            totalNumberOfSeats -= passengerWagon.getNumberOfSeats();
        }
        return wagon;
    }

    private void countTotalNumberOfSeatsAndPassengers() {
        int i = locomotiveCount;
        while (i < wagons.size()) {
            PassengerWagon passengerWagon = (PassengerWagon) wagons.get(i);
            totalNumberOfPassengers += passengerWagon.getNumberOfPassengers();
            totalNumberOfSeats += passengerWagon.getNumberOfSeats();
        }
    }

    private boolean checkToCorrectPassengerTrain(LinkedList<Wagon> wagons) {
        for (Wagon wagon :
                wagons) {
            if (wagon instanceof FreightWagon) {
                return false;
            }
        }
        return true;
    }
}
