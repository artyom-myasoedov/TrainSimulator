package myasoedov.cs.models.storages;


import myasoedov.cs.models.abstractWagons.FreightWagon;
import myasoedov.cs.models.abstractWagons.Locomotive;
import myasoedov.cs.models.abstractWagons.PassengerWagon;
import myasoedov.cs.models.abstractWagons.Wagon;
import myasoedov.cs.models.storages.wagons.FreightWagonDBStorage;
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
import myasoedov.cs.wagons.freightWagons.CoveredWagon;
import myasoedov.cs.wagons.freightWagons.PlatformWagon;
import myasoedov.cs.wagons.freightWagons.RefrigeratorWagon;
import myasoedov.cs.wagons.freightWagons.TankWagon;
import myasoedov.cs.wagons.locomotives.DieselLocomotive;
import myasoedov.cs.wagons.locomotives.ElectricLocomotive;
import myasoedov.cs.wagons.locomotives.SteamLocomotive;
import myasoedov.cs.wagons.passengerWagons.CoupeWagon;
import myasoedov.cs.wagons.passengerWagons.RestaurantWagon;
import myasoedov.cs.wagons.passengerWagons.SeatWagon;
import myasoedov.cs.wagons.passengerWagons.SleepWagon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Configs {
    public final static String JDBC_URL = "jdbc:h2:D:\\Projects\\Idea projects\\TrainSimulator\\src\\db\\TrainSimulator";
    public final static String USER_NAME = "art.myas";
    public final static String USER_PAROL = "123321";

    public static Map<Class<? extends Wagon>, Storage<? extends Wagon>> createStorageMap() {
        Map<Class<? extends Wagon>, Storage<? extends Wagon>> map = new HashMap<>();
        map.put(CoveredWagon.class, new CoveredWagonDBStorage<>(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(PlatformWagon.class, new PlatformWagonDBStorage<>(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(RefrigeratorWagon.class, new RefrigeratorWagonDBStorage<>(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(TankWagon.class, new TankWagonDBStorage<>(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(SleepWagon.class, new SleepWagonDBStorage<>(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(CoupeWagon.class, new CoupeWagonDBStorage<>(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(SeatWagon.class, new SeatWagonDBStorage<>(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(RestaurantWagon.class, new RestaurantWagonDBStorage<>(JDBC_URL, USER_NAME, USER_PAROL));

        return map;
    }

    public static Map<WagonType, Storage<? extends Wagon>> createStorageEnumMap() {
        Map<WagonType, Storage<? extends Wagon>> map = new HashMap<>();
        map.put(WagonType.COVERED, new CoveredWagonDBStorage<>(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(WagonType.PLATFORM, new PlatformWagonDBStorage<>(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(WagonType.REFRIGERATOR, new RefrigeratorWagonDBStorage<>(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(WagonType.TANK, new TankWagonDBStorage<>(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(WagonType.SLEEP, new SleepWagonDBStorage<>(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(WagonType.COUPE, new CoupeWagonDBStorage<>(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(WagonType.SEAT, new SeatWagonDBStorage<>(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(WagonType.RESTAURANT, new RestaurantWagonDBStorage<>(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(WagonType.DIESEL, new DieselLocomotiveDBStorage<>(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(WagonType.ELECTRIC, new ElectricLocomotiveDBStorage<>(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(WagonType.STEAM, new SteamLocomotiveDBStorage<>(JDBC_URL, USER_NAME, USER_PAROL));

        return map;
    }

    public static Map<Class<? extends Locomotive>, Storage<? extends Locomotive>> createLocomotivesMap() {
        Map<Class<? extends Locomotive>, Storage<? extends Locomotive>> map = new HashMap<>();
        map.put(DieselLocomotive.class, new DieselLocomotiveDBStorage<>(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(ElectricLocomotive.class, new ElectricLocomotiveDBStorage<>(JDBC_URL, USER_NAME, USER_PAROL));
        map.put(SteamLocomotive.class, new SteamLocomotiveDBStorage<>(JDBC_URL, USER_NAME, USER_PAROL));
        return map;
    }

    public static List<FreightWagonDBStorage<? extends FreightWagon>> createFreightList() {
        List<FreightWagonDBStorage<? extends FreightWagon>> list = new ArrayList<>();
        list.add(new TankWagonDBStorage<>(JDBC_URL, USER_NAME, USER_PAROL));
        list.add(new CoveredWagonDBStorage<>(JDBC_URL, USER_NAME, USER_PAROL));
        list.add(new PlatformWagonDBStorage<>(JDBC_URL, USER_NAME, USER_PAROL));
        list.add(new RefrigeratorWagonDBStorage<>(JDBC_URL, USER_NAME, USER_PAROL));
        return list;
    }

}
