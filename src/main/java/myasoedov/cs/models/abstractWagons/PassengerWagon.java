package myasoedov.cs.models.abstractWagons;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public abstract class PassengerWagon extends Wagon implements Serializable {
    protected final int numberOfSeats;
    protected int numberOfPassengers;

    public PassengerWagon(int weight, BigDecimal age, BigDecimal condition, int seats, UUID id) {
        super(weight, age, condition, id);
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
            throw new IllegalArgumentException("Недопустимое количество пассажиров!");
        }
    }

    public void removePassengers(int numberOfPassengers) {
        this.numberOfPassengers = Math.max(this.numberOfPassengers - numberOfPassengers, 0);
    }
}
