package trains;

import wagons.abstractWagons.Locomotive;
import wagons.abstractWagons.Wagon;

import java.util.LinkedList;
import java.util.List;

public abstract class Train {
    protected LinkedList<Wagon> wagons;
    protected int locomotiveCount;
    protected int totalPower;
    protected Movings moving;
    protected int totalWagonsWeight;

    public Train() {
        wagons = new LinkedList<>();
        locomotiveCount = 0;
        totalPower = 0;
        totalWagonsWeight = 0;
        moving = Movings.STOPPED;
    }

    public Train(LinkedList<Wagon> wagons) {
        if (checkForCorrectWagons(wagons)) {
            this.wagons = wagons;
            countingTotalPower();
            countingTotalWeight();
        } else {
            this.wagons = new LinkedList<>();
            locomotiveCount = 0;
            totalPower = 0;
            totalWagonsWeight = 0;
            System.out.println("Incorrect list of wagons! \n Empty train was created");
        }
        moving = Movings.STOPPED;
    }

    public int getTotalWagonsWeight() {
        return totalWagonsWeight;
    }

    public int getLocomotiveCount() {
        return locomotiveCount;
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

    public void addWagon(Wagon wagon) {
        if (wagon instanceof Locomotive) {
            if (locomotiveCount < 3) {
                totalPower += ((Locomotive) wagon).getPower();
                locomotiveCount++;
                wagons.addLast(wagon);
                totalWagonsWeight += wagon.getWeight();
            } else {
                System.out.println("Reached a maximum of locomotives!");
            }
        } else {
            wagons.addLast(wagon);
            totalWagonsWeight += wagon.getWeight();
        }
    }

    public Wagon unhookWagon() {
        if (!wagons.isEmpty()) {
            Wagon wagon = wagons.getLast();
            wagons.removeLast();
            if (wagon instanceof Locomotive) {
                totalPower -= ((Locomotive) wagon).getPower();
                locomotiveCount--;
            }
            totalWagonsWeight -= wagon.getWeight();
            return wagon;
        } else {
            System.out.println("0 wagons!");
            return null;
        }
    }

    public void moveForward() {
        int i = 0;
        Wagon locomotive;
        while (i < locomotiveCount) {
            if ((locomotive = wagons.get(i)) instanceof Locomotive) {
                ((Locomotive) locomotive).moveForward();
            }
        }
        moving = Movings.FORWARD;
    }

    public void moveBehind() {
        int i = 0;
        Wagon locomotive;
        while (i < locomotiveCount) {
            if ((locomotive = wagons.get(i)) instanceof Locomotive) {
                ((Locomotive) locomotive).moveBehind();
            }
        }
        moving = Movings.BEHIND;
    }

    public void stopMoving() {
        int i = 0;
        Wagon locomotive;
        while (i < locomotiveCount) {
            if ((locomotive = wagons.get(i)) instanceof Locomotive) {
                ((Locomotive) locomotive).stopMoving();
            }
        }
        moving = Movings.STOPPED;
    }

    private boolean checkForCorrectWagons(List<Wagon> wagons) {
        int wagonPoint = 0;
        locomotiveCount = 0;
        for (Wagon wagon :
                wagons
        ) {
            if (wagon instanceof Locomotive) {
                locomotiveCount++;
                if (wagonPoint > 2) {
                    return false;
                }
            }
            wagonPoint++;
        }
        return locomotiveCount < 4 && locomotiveCount > 0;
    }

    private void countingTotalPower() {
        int i = 0;
        Wagon locomotive;
        totalPower = 0;
        while (i < locomotiveCount) {
            if ((locomotive = wagons.get(i)) instanceof Locomotive) {
                totalPower += ((Locomotive) locomotive).getPower();
            }
        }
    }

    private void countingTotalWeight() {
        totalWagonsWeight = 0;
        for (Wagon wagon :
                wagons) {
            totalWagonsWeight += wagon.getWeight();
        }
    }
}
