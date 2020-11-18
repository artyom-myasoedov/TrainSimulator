package myasoedov.cs.wagons.locomotives;

import myasoedov.cs.models.abstractWagons.Locomotive;
import myasoedov.cs.models.storages.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.storages.wagons.locomotives.ElectricLocomotiveDBStorage;
import myasoedov.cs.storages.wagons.locomotives.SteamLocomotiveDBStorage;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

public class ElectricLocomotive extends Locomotive {
    private LocomotiveEngineConditions powerGridConnection;
    private final static int WEIGHT = 7000;
    private final static int POWER = 100000;
    private final static BigDecimal MAX_SPEED = BigDecimal.valueOf(100);
    private final static Storage<ElectricLocomotive> storage = new ElectricLocomotiveDBStorage<>(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);


    public ElectricLocomotive(BigDecimal age, BigDecimal condition, LocomotiveEngineConditions powerGridConnection, UUID id) {
        super(WEIGHT, age, condition, POWER, MAX_SPEED, id);
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
            System.out.println("Start electric engine");
            engine = LocomotiveEngineConditions.ENABLED;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void stopEngine() {
        super.stopEngine();
        System.out.println("Stop electric engine");
    }
}
