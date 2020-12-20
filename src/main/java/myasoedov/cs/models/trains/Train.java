package myasoedov.cs.models.trains;

import myasoedov.cs.models.Storable;
import myasoedov.cs.models.abstractWagons.Locomotive;
import myasoedov.cs.models.abstractWagons.Wagon;
import myasoedov.cs.trains.Movings;

import java.math.BigDecimal;
import java.util.*;

public abstract class Train implements Storable {
    protected LinkedList<Wagon> wagons;
    private final LinkedList<Locomotive> locomotives;
    protected final UUID id;
    private int totalPower;
    private Movings moving;
    private int totalWagonsWeight;
    private boolean locomotivesInHead;
    private boolean locomotivesInTail;
    private BigDecimal maxSpeed;
    private BigDecimal currentSpeed;

    public Train(UUID id) {
        this.id = id;
        locomotives = new LinkedList<>();
        wagons = new LinkedList<>();
        totalPower = 0;
        totalWagonsWeight = 0;
        moving = Movings.STOPPED;
        maxSpeed = BigDecimal.valueOf(0);
        currentSpeed = BigDecimal.valueOf(0);
    }

    public Train(List<? extends Wagon> wagons, List<? extends Locomotive> locomotives, UUID id) {
        if (!checkForCorrectLocomotives(locomotives)) {
            throw new IllegalArgumentException();
        } else {
            this.id = id;
            this.wagons = new LinkedList<>(wagons);
            this.locomotives = new LinkedList<>(locomotives);
            countingTotalPower();
            countingTotalWeight();
            countingMaxSpeed();
            setTrainId();
            setWagonsNumber();
            moving = Movings.STOPPED;
            locomotivesInHead = false;
            locomotivesInTail = false;
            currentSpeed = BigDecimal.valueOf(0);
        }
    }

    public Train(List<? extends Wagon> wagons, UUID id) {
        this.id = id;
        this.wagons = new LinkedList<>(wagons);
        this.locomotives = new LinkedList<>();
        countingTotalPower();
        countingTotalWeight();
        setWagonsNumber();
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

    public List<? extends Wagon> getWagons() {
        return wagons;
    }

    public List<? extends Locomotive> getLocomotives() {
        return locomotives;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public abstract int getTotalWeight();

    public void setCurrentSpeed(BigDecimal currentSpeed) {
        if (currentSpeed.compareTo(BigDecimal.valueOf(0)) < 0 || currentSpeed.compareTo(maxSpeed) > 0) {
            throw new IllegalArgumentException("Недопустимая скорость!");
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

    public void addTailWagon(Wagon wagon) {
        if (!locomotivesInTail) {
            wagons.addLast(wagon);
            totalWagonsWeight += wagon.getWeight();
            wagon.setNumberInComposition((long) wagons.size());
            wagon.setTrainId(this.id);
        } else {
            throw new IllegalStateException("Присоединению вагона с этой стороны мешают локомотивы!");
        }
    }

    public void addHeadWagon(Wagon wagon) {
        if (!locomotivesInHead) {
            wagons.addFirst(wagon);
            totalWagonsWeight += wagon.getWeight();
            setWagonsNumber();
            wagon.setTrainId(this.id);
        } else {
            throw new IllegalStateException("Присоединению вагона с этой стороны мешают локомотивы!");
        }
    }

    public void addLocomotive(Locomotive locomotive) {
        if (locomotives.size() < 3) {
            totalPower += locomotive.getPower();
            locomotives.addLast(locomotive);
            totalWagonsWeight += locomotive.getWeight();
            locomotive.setNumberInComposition((long) (locomotives.size()));
            locomotive.setTrainId(this.id);
            if (maxSpeed.compareTo(locomotive.getMaxSpeed()) < 0) {
                maxSpeed = locomotive.getMaxSpeed();
            }
        } else {
            throw new IllegalStateException("Достигнуто максимальное число локомотивов!");
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
            throw new NullPointerException("Состав локомотивов пуст!");
    }

    public Locomotive getLocomotive(int index) {
        return locomotives.get(index);
    }

    public Wagon unhookHeadWagon() {
        if (!wagons.isEmpty()) {
            Wagon wagon = wagons.pollFirst();
            totalWagonsWeight -= wagon.getWeight();
            wagon.setNumberInComposition(null);
            setWagonsNumber();
            wagon.setTrainId(null);
            return wagon;
        } else {
            throw new NullPointerException("Поезд пуст!");
        }
    }

    public Wagon unhookTailWagon() {
        if (!wagons.isEmpty()) {
            Wagon wagon = wagons.pollLast();
            totalWagonsWeight -= wagon.getWeight();
            setWagonsNumber();
            wagon.setNumberInComposition(null);
            wagon.setTrainId(null);
            return wagon;
        } else {
            throw new NullPointerException("Поезд пуст!");
        }
    }

    public void moveForward(double speed) {
            if (totalPower < getTotalWeight()) {
                throw new IllegalStateException("Перегруз!");
            }
            setCurrentSpeed(BigDecimal.valueOf(speed));
            locomotives.forEach(Locomotive::moveForward);
            moving = Movings.FORWARD;
    }

    public void moveBehind(double speed) {
        if (locomotivesInTail || locomotivesInHead) {
            if (totalPower < getTotalWeight()) {
                throw new IllegalStateException("Перегруз!");
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

    private boolean checkForCorrectLocomotives(List<? extends Locomotive> locomotives) {
        return locomotives.size() < 4;
    }

    private void countingTotalPower() {
        if (locomotives.size() > 0)
        totalPower = locomotives.stream().reduce(0, (x, y) -> x + y.getPower(), Integer::sum);
    }

    private void countingTotalWeight() {
        if (wagons.size() > 0)
        totalWagonsWeight = wagons.stream().reduce(0, (x, y) -> x + y.getWeight(), Integer::sum);
        if (locomotives.size() > 0)
        totalWagonsWeight = locomotives.stream().reduce(totalWagonsWeight, (x, y) -> x + y.getWeight(), Integer::sum);
    }

    private void countingMaxSpeed() {
        if (locomotives.size() > 0)
        maxSpeed = locomotives.stream().max(Comparator.comparing(Locomotive::getMaxSpeed)).get().getMaxSpeed();
    }

    private void setWagonsNumber() {
        for (int i = 0; i < wagons.size(); i++) {
            wagons.get(i).setNumberInComposition((long) i + 1);
        }
        for (int i = 0; i < locomotives.size(); i++) {
            locomotives.get(i).setNumberInComposition((long) i + 1);
        }
    }

    private void setTrainId() {
        for (Wagon wagon : wagons) {
            wagon.setTrainId(getId());
        }
        for (Locomotive locomotive : locomotives) {
            locomotive.setTrainId(getId());
        }
    }
}
