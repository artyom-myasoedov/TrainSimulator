package wagons.abstractWagons;

import java.math.BigDecimal;

public abstract class PassengerWagon extends Wagon {
    protected final int numberOfSeats;
    protected int numberOfPassengers;

    public PassengerWagon(int weight, BigDecimal age, BigDecimal condition, int seats) {
        super(weight, age, condition);
        this.numberOfSeats = seats;
        numberOfPassengers = 0;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void addPassengers(int numberOfPassengers) {
        if (numberOfPassengers + this.numberOfPassengers < numberOfSeats) {
            this.numberOfPassengers += numberOfPassengers;
        } else {
            System.out.println("Overload!");
        }
    }

    public void removePassengers(int numberOfPassengers) {
        if (this.numberOfPassengers - numberOfPassengers >= 0) {
            this.numberOfPassengers -= numberOfPassengers;
        } else {
            System.out.println("Not enough passengers!");
        }
    }
}
