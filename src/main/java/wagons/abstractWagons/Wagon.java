package wagons.abstractWagons;


import java.math.BigDecimal;

public abstract class Wagon {
    protected final int weight;
    protected BigDecimal age;
    protected BigDecimal condition;

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
