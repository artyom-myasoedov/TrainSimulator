package myasoedov.cs.models.abstractWagons;


import myasoedov.cs.models.Storable;
import myasoedov.cs.models.storages.Storage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;

public abstract class Wagon implements Storable, Serializable {
    protected final int weight;
    protected final UUID id;
    protected Long numberInComposition;
    protected Storage<? extends Wagon> storage;

    protected UUID trainId;
    protected BigDecimal age;
    protected BigDecimal condition;

    public Wagon(int weight, BigDecimal age, BigDecimal condition, UUID id) {
        this.weight = weight;
        this.age = age;
        this.condition = condition;
        this.id = id;
        numberInComposition = null;
        trainId = null;
    }

    @Override
    public UUID getId() {
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

    public UUID getTrainId() {
        return trainId;
    }

    public Storage<? extends Wagon> getStorage() {
        return storage;
    }

    public void setStorage(Storage<? extends Wagon> storage) {
        this.storage = storage;
    }

    public void setTrainId(UUID trainId) {
        this.trainId = trainId;
    }

    public void setAge(BigDecimal age) {
        if (age.compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new IllegalArgumentException("Недопустимый возраст вагона!");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wagon wagon = (Wagon) o;
        return weight == wagon.weight && id.equals(wagon.id) && Objects.equals(numberInComposition, wagon.numberInComposition) && storage.equals(wagon.storage) && Objects.equals(trainId, wagon.trainId) && age.equals(wagon.age) && condition.equals(wagon.condition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, id, numberInComposition, storage, trainId, age, condition);
    }
}


