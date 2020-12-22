package myasoedov.cs.wagons.locomotives;

import myasoedov.cs.models.abstractWagons.Locomotive;
import myasoedov.cs.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.storages.wagons.locomotives.ElectricLocomotiveDBStorage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;

public class ElectricLocomotive extends Locomotive implements Serializable {

    private LocomotiveEngineConditions powerGridConnection;
    private final static Storage<ElectricLocomotive> storage = new ElectricLocomotiveDBStorage(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);


    public ElectricLocomotive(BigDecimal age, BigDecimal condition, LocomotiveEngineConditions powerGridConnection, UUID id) {
        super(Integer.parseInt(Configs.WagonFillsProperties.getProperty("electricWeight")),
                age, condition, Integer.parseInt(Configs.WagonFillsProperties.getProperty("electricPower")),
                new BigDecimal(Configs.WagonFillsProperties.getProperty("electricMaxSpeed")), id);
        this.powerGridConnection = powerGridConnection;
    }

    public static ElectricLocomotive save(ElectricLocomotive wagon) throws SQLException {
        storage.save(wagon);
        return wagon;
    }

    public boolean isPowerGridConnect() {
        return powerGridConnection == LocomotiveEngineConditions.ENABLED;
    }

    public void setPowerGridConnection(LocomotiveEngineConditions powerGridConnection) {
        this.powerGridConnection = powerGridConnection;
    }

    @Override
    public void startEngine() {
        if (powerGridConnection == LocomotiveEngineConditions.ENABLED) {
            engine = LocomotiveEngineConditions.ENABLED;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ElectricLocomotive that = (ElectricLocomotive) o;
        return powerGridConnection == that.powerGridConnection;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), powerGridConnection);
    }
}
