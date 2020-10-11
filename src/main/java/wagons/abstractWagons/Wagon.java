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
        if(age.compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new IllegalArgumentException();
        }
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
        this.condition = this.condition.add(BigDecimal.valueOf(condition));
        if (this.condition.compareTo(BigDecimal.valueOf(100)) > 0) {
            this.condition = BigDecimal.valueOf(100);
        }
    }

    public void fullRepair() {
        condition = BigDecimal.valueOf(100);
    }
}
