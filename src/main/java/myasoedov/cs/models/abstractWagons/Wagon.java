package myasoedov.cs.models.abstractWagons;


import myasoedov.cs.models.Storable;

import java.math.BigDecimal;

public abstract class Wagon implements Storable {
    protected final int weight;
    protected final Long id;
    protected Long numberInComposition;

    protected Long trainId;
    protected BigDecimal age;
    protected BigDecimal condition;

    public Wagon(int weight, BigDecimal age, BigDecimal condition, Long id) {
        this.weight = weight;
        this.age = age;
        this.condition = condition;
        this.id = id;
        numberInComposition = null;
        trainId = null;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Long getNumberInComposition() {
        return numberInComposition;
    }

    public int getWeight() {
        return weight;
    }

    public BigDecimal getAge() {
        return age;
    }

    public BigDecimal getCondition() {
        return condition;
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public void setAge(BigDecimal age) {
        if (age.compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new IllegalArgumentException();
        }
        this.age = age;
    }

    public void setNumberInComposition(Long position) {
        numberInComposition = position;
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
