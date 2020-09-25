package wagons.abstractWagons;


import java.math.BigDecimal;

public abstract class Wagon {
    protected final int weight;
    protected BigDecimal age;
    protected BigDecimal condition;
    protected Wagon neighborNext;
    protected Wagon neighborBehind;

    public Wagon getNeighborNext() {
        return neighborNext;
    }

    public void setNeighborNext(Wagon neighborNext) {
        this.neighborNext = neighborNext;
    }

    public Wagon getNeighborBehind() {
        return neighborBehind;
    }

    public void setNeighborBehind(Wagon neighborBehind) {
        this.neighborBehind = neighborBehind;
    }

    public int getWeight() {
        return weight;
    }

    public BigDecimal getAge() {
        return age;
    }

    public void setAge(BigDecimal age) {
        this.age = age;
    }

    public BigDecimal getCondition() {
        return condition;
    }

    public Wagon(int weight, BigDecimal age, BigDecimal condition) {
        this.weight = weight;
        this.age = age;
        this.condition = condition;
    }

    public void repair(double condition) {
        this.condition = this.condition.add(new BigDecimal(condition));
        if (this.condition.compareTo(new BigDecimal("100.00")) > 0) {
            this.condition = new BigDecimal("100.00");
        }
    }

    public void fullRepair() {
        condition = new BigDecimal("100.00");
    }
}
