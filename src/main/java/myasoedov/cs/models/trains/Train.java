package myasoedov.cs.models.trains;

import myasoedov.cs.models.Storable;
import myasoedov.cs.models.abstractWagons.Locomotive;
import myasoedov.cs.models.abstractWagons.Wagon;
import myasoedov.cs.trains.Movings;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class Train<T extends Wagon> implements Storable {
    protected LinkedList<T> wagons;
    private final LinkedList<Locomotive> locomotives;
    protected final Long id;
    private int totalPower;
    private Movings moving;
    private int totalWagonsWeight;
    private boolean locomotivesInHead;
    private boolean locomotivesInTail;
    private BigDecimal maxSpeed;
    private BigDecimal currentSpeed;

    public Train(Long id) {
        this.id = id;
        locomotives = new LinkedList<>();
        wagons = new LinkedList<>();
        totalPower = 0;
        totalWagonsWeight = 0;
        moving = Movings.STOPPED;
        maxSpeed = BigDecimal.valueOf(0);
        currentSpeed = BigDecimal.valueOf(0);
    }

    public Train(List<? extends T> wagons, List< ? extends Locomotive> locomotives, Long id) {
        if (!checkForCorrectLocomotives(locomotives)) {
            throw new IllegalArgumentException();
        } else {
            this.id = id;
            this.wagons = new LinkedList<>(wagons);
            this.locomotives = new LinkedList<>(locomotives);
            countingTotalPower();
            countingTotalWeight();
            countingMaxSpeed();
            moving = Movings.STOPPED;
            locomotivesInHead = false;
            locomotivesInTail = false;
            currentSpeed = BigDecimal.valueOf(0);
        }
    }

    public Train(List< ? extends T> wagons, Long id) {
        this.id = id;
        this.wagons = new LinkedList<>(wagons);
        this.locomotives = new LinkedList<>();
        countingTotalPower();
        countingTotalWeight();
        moving = Movings.STOPPED;
        locomotivesInHead = false;
        locomotivesInTail = false;
        maxSpeed = BigDecimal.valueOf(0);
        currentSpeed = BigDecimal.valueOf(0);
    }

    public int getTotalWagonsWeight() {
        return totalWagonsWeight;
    }

    public int getTotalPower() {
        return totalPower;
    }

    public Movings getMoving() {
        return moving;
    }

    public BigDecimal getCurrentSpeed() {
        return currentSpeed;
    }

    public int getLocomotivesSize() {
        return locomotives.size();
    }

    public int getTrainSize() {
        return wagons.size() + locomotives.size();
    }

    public int getWagonsSize() {
        return wagons.size();
    }

    @Override
    public Long getId() {
        return id;
    }

    public abstract int getTotalWeight();

    public void setCurrentSpeed(BigDecimal currentSpeed) {
        if (currentSpeed.compareTo(BigDecimal.valueOf(0)) < 0 || currentSpeed.compareTo(maxSpeed) > 0) {
            throw new IllegalArgumentException();
        }
        this.currentSpeed = currentSpeed;
    }

    public boolean areLocomotivesInHead() {
        return locomotivesInHead;
    }

    public boolean areLocomotivesInTail() {
        return locomotivesInTail;
    }

    public void connectLocomotivesToHead() {
        disconnectLocomotives();
        locomotivesInHead = true;
    }

    public void connectLocomotivesToTail() {
        disconnectLocomotives();
        locomotivesInTail = true;
    }

    public void disconnectLocomotives() {
        locomotivesInHead = false;
        locomotivesInTail = false;
    }

    public void addTailWagon(T wagon) {
        if (!locomotivesInTail) {
            wagons.addLast(wagon);
            totalWagonsWeight += wagon.getWeight();
            wagon.setNumberInComposition((long) (wagons.size() - 1));
            wagon.setTrainId(this.id);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void addHeadWagon(T wagon) {
        if (!locomotivesInHead) {
            wagons.addFirst(wagon);
            totalWagonsWeight += wagon.getWeight();
            wagon.setNumberInComposition(0L);
            wagon.setTrainId(this.id);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void addLocomotive(Locomotive locomotive) {
        if (locomotives.size() < 3) {
            totalPower += locomotive.getPower();
            locomotives.addLast(locomotive);
            totalWagonsWeight += locomotive.getWeight();
            locomotive.setNumberInComposition((long) (locomotives.size() - 1));
            locomotive.setTrainId(this.id);
            if (maxSpeed.compareTo(locomotive.getMaxSpeed()) < 0) {
                maxSpeed = locomotive.getMaxSpeed();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Locomotive unhookLocomotive() {
        if (locomotives.size() > 0) {
            Locomotive locomotive = locomotives.getLast();
            locomotives.removeLast();
            countingTotalPower();
            locomotive.setNumberInComposition(null);
            locomotive.setTrainId(null);
            return locomotive;
        } else
            throw new NullPointerException();
    }

    public Locomotive getLocomotive(int index) {
        return locomotives.get(index);
    }

    public T unhookHeadWagon() {
        if (!wagons.isEmpty()) {
            T wagon = wagons.pollFirst();
            totalWagonsWeight -= wagon.getWeight();
            wagon.setNumberInComposition(null);
            wagon.setTrainId(null);
            return wagon;
        } else {
            throw new NullPointerException();
        }
    }

    public T unhookTailWagon() {
        if (!wagons.isEmpty()) {
            T wagon = wagons.pollLast();
            totalWagonsWeight -= wagon.getWeight();
            wagon.setNumberInComposition(null);
            wagon.setTrainId(null);
            return wagon;
        } else {
            throw new NullPointerException();
        }
    }

    public void moveForward(double speed) {
        if (locomotivesInTail || locomotivesInHead) {
            if (totalPower < getTotalWeight()) {
                throw new IllegalArgumentException();
            }
            setCurrentSpeed(BigDecimal.valueOf(speed));
            locomotives.forEach(Locomotive::moveForward);
            moving = Movings.FORWARD;
        }
    }

    public void moveBehind(double speed) {
        if (locomotivesInTail || locomotivesInHead) {
            if (totalPower < getTotalWeight()) {
                throw new IllegalArgumentException();
            }
            setCurrentSpeed(BigDecimal.valueOf(speed));
            locomotives.forEach(Locomotive::moveBehind);
            moving = Movings.BEHIND;
        }
    }

    public void stopMoving() {
        currentSpeed = BigDecimal.valueOf(0);
        locomotives.forEach(Locomotive::stopMoving);
        moving = Movings.STOPPED;
    }

    public Iterator<T> iteratorWagons() {
        return wagons.iterator();
    }

    public Iterator<Locomotive> iteratorLocomotives() {
        return locomotives.iterator();
    }

    private boolean checkForCorrectLocomotives(List<? extends Locomotive> locomotives) {
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
