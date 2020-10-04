package trains;

import wagons.abstractWagons.Locomotive;
import wagons.abstractWagons.Wagon;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public abstract class Train<T extends Wagon> implements Iterable<T> {
    protected Deque<T> wagons;
    private final Deque<Locomotive> locomotives;
    private int totalPower;
    private Movings moving;
    private int totalWagonsWeight;
    private boolean locomotivesInHead;
    private boolean locomotivesInTail;
    private BigDecimal maxSpeed;

    public Train() {
        locomotives = new LinkedList<>();
        wagons = new LinkedList<>();
        totalPower = 0;
        totalWagonsWeight = 0;
        moving = Movings.STOPPED;
        maxSpeed = new BigDecimal("0");
    }

    public Train(List<T> wagons, List<Locomotive> locomotives) {
        if (!checkForCorrectLocomotives(locomotives)) {
            throw new IllegalArgumentException();
        } else {
            this.wagons = new LinkedList<>(wagons);
            this.locomotives = new LinkedList<>(locomotives);
            countingTotalPower();
            countingTotalWeight();
            moving = Movings.STOPPED;
            locomotivesInHead = false;
            locomotivesInTail = false;

        }
    }

    public Train(List<T> wagons) {
        this.wagons = new LinkedList<>(wagons);
        this.locomotives = new LinkedList<>();
        countingTotalPower();
        countingTotalWeight();
        moving = Movings.STOPPED;
        locomotivesInHead = false;
        locomotivesInTail = false;
        maxSpeed = new BigDecimal("0");
    }

    public int getTotalWagonsWeight() {
        return totalWagonsWeight;
    }

    public int getLocomotiveCount() {
        return locomotives.size();
    }

    public int getTotalPower() {
        return totalPower;
    }

    public int getNumberOfWagons() {
        return wagons.size();
    }

    public Movings getMoving() {
        return moving;
    }

    public void connectLocomotivesToHead() {
        disconnectLocomotives();
        locomotivesInHead = true;
        countingTotalPower();
    }

    public void connectLocomotivesToTail() {
        disconnectLocomotives();
        locomotivesInTail = true;
        countingTotalPower();
    }

    public void disconnectLocomotives() {
        locomotivesInHead = false;
        locomotivesInTail = false;
        totalPower = 0;
    }

    public void addTailWagon(T wagon) {
        if (!locomotivesInTail) {
            wagons.addLast(wagon);
            totalWagonsWeight += wagon.getWeight();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void addHeadWagon(T wagon) {
        if (!locomotivesInHead) {
            wagons.addFirst(wagon);
            totalWagonsWeight += wagon.getWeight();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void addLocomotive(Locomotive locomotive) throws IllegalAccessException {
        if (locomotives.size() < 3) {
            totalPower += locomotive.getPower();
            locomotives.addLast(locomotive);
            totalWagonsWeight += locomotive.getWeight();
            if (maxSpeed.compareTo(locomotive.getMaxSpeed()) < 0) {
                maxSpeed = locomotive.getMaxSpeed();
            }
        } else {
            throw new IllegalAccessException();
        }
    }

    public T unhookHeadWagon() {
        if (!wagons.isEmpty()) {
            T wagon = wagons.pollFirst();
            totalWagonsWeight -= wagon.getWeight();
            return wagon;
        } else {
            throw new NullPointerException();
        }
    }

    public T unhookTailWagon() {
        if (!wagons.isEmpty()) {
            T wagon = wagons.pollLast();
            totalWagonsWeight -= wagon.getWeight();
            return wagon;
        } else {
            throw new NullPointerException();
        }
    }

    public void moveForward() {
        if (locomotivesInTail || locomotivesInHead) {
            locomotives.forEach(Locomotive::moveForward);
            moving = Movings.FORWARD;
        }
    }

    public void moveBehind() {
        if (locomotivesInTail || locomotivesInHead) {
            locomotives.forEach(Locomotive::moveBehind);
            moving = Movings.BEHIND;
        }
    }

    public void stopMoving() {
        locomotives.forEach(Locomotive::stopMoving);
        moving = Movings.STOPPED;
    }

    private boolean checkForCorrectLocomotives(List<Locomotive> locomotives) {
        return locomotives.size() < 4;
    }

    private void countingTotalPower() {
        totalPower = locomotives.stream().reduce(0, (x, y) -> x + y.getPower(), Integer::sum);
    }

    private void countingTotalWeight() {
        totalWagonsWeight = wagons.stream().reduce(0, (x, y) -> x + y.getWeight(), Integer::sum);
        totalWagonsWeight = locomotives.stream().reduce(totalWagonsWeight, (x, y) -> x + y.getWeight(), Integer::sum);
    }

    private void countingMaxSpeed() {
        maxSpeed = locomotives.stream().max(Comparator.comparing(Locomotive::getMaxSpeed)).get().getMaxSpeed();
    }
}
