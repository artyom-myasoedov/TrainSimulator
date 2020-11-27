package myasoedov.cs.wagons.locomotives;

import myasoedov.cs.models.abstractWagons.Locomotive;
import myasoedov.cs.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.storages.wagons.locomotives.ElectricLocomotiveDBStorage;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.UUID;

public class ElectricLocomotive extends Locomotive {

    private LocomotiveEngineConditions powerGridConnection;
    private final static Storage<ElectricLocomotive> storage = new ElectricLocomotiveDBStorage<>(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);


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
