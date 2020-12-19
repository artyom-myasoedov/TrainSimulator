package myasoedov.cs;

import myasoedov.cs.models.abstractWagons.Wagon;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.storages.wagons.WagonType;
import myasoedov.cs.storages.wagons.freight.CoveredWagonDBStorage;
import myasoedov.cs.storages.wagons.freight.PlatformWagonDBStorage;
import myasoedov.cs.storages.wagons.freight.RefrigeratorWagonDBStorage;
import myasoedov.cs.storages.wagons.freight.TankWagonDBStorage;
import myasoedov.cs.storages.wagons.locomotives.DieselLocomotiveDBStorage;
import myasoedov.cs.storages.wagons.locomotives.ElectricLocomotiveDBStorage;
import myasoedov.cs.storages.wagons.locomotives.SteamLocomotiveDBStorage;
import myasoedov.cs.storages.wagons.passenger.CoupeWagonDBStorage;
import myasoedov.cs.storages.wagons.passenger.RestaurantWagonDBStorage;
import myasoedov.cs.storages.wagons.passenger.SeatWagonDBStorage;
import myasoedov.cs.storages.wagons.passenger.SleepWagonDBStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Configs {
    public final static String JDBC_URL = "jdbc:h2:D:\\Projects\\Idea projects\\TrainSimulator\\src\\db\\TrainSimulator";
    public final static String USER_NAME = "art.myas";
    public final static String USER_PAROL = "123321";
    public final static Properties DBProperties;
    public final static Properties WagonFillsProperties;

    static {
        DBProperties = new Properties();
        WagonFillsProperties = new Properties();
        try {
            DBProperties.load(new FileInputStream(new File("").getAbsolutePath() + "\\src\\main\\resources\\DataBase.properties"));
            WagonFillsProperties.load(new FileInputStream(new File("").getAbsolutePath() + "\\src\\main\\resources\\WagonFills.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<WagonType, Storage<? extends Wagon>> createStorageEnumMap() {
        Map<WagonType, Storage<? extends Wagon>> map = new HashMap<>();
        map.put(WagonType.COVERED, new CoveredWagonDBStorage(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(WagonType.PLATFORM, new PlatformWagonDBStorage(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(WagonType.REFRIGERATOR, new RefrigeratorWagonDBStorage(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(WagonType.TANK, new TankWagonDBStorage(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(WagonType.SLEEP, new SleepWagonDBStorage(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(WagonType.COUPE, new CoupeWagonDBStorage(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(WagonType.SEAT, new SeatWagonDBStorage(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(WagonType.RESTAURANT, new RestaurantWagonDBStorage(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(WagonType.DIESEL, new DieselLocomotiveDBStorage(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(WagonType.ELECTRIC, new ElectricLocomotiveDBStorage(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(WagonType.STEAM, new SteamLocomotiveDBStorage(JDBC_URL, USER_NAME, USER_PAROL));

        return map;
    }
}
