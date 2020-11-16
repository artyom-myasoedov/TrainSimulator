package myasoedov.cs.storages.train;

import myasoedov.cs.DoubleContainer;
import myasoedov.cs.models.abstractWagons.FreightWagon;
import myasoedov.cs.models.abstractWagons.Locomotive;
import myasoedov.cs.models.abstractWagons.PassengerWagon;
import myasoedov.cs.models.abstractWagons.Wagon;
import myasoedov.cs.models.storages.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.models.storages.TrainDBStorage;
import myasoedov.cs.storages.wagons.freight.CoveredWagonDBStorage;
import myasoedov.cs.storages.wagons.freight.PlatformWagonDBStorage;
import myasoedov.cs.storages.wagons.freight.RefrigeratorWagonDBStorage;
import myasoedov.cs.storages.wagons.freight.TankWagonDBStorage;
import myasoedov.cs.storages.wagons.passenger.CoupeWagonDBStorage;
import myasoedov.cs.storages.wagons.passenger.RestaurantWagonDBStorage;
import myasoedov.cs.storages.wagons.passenger.SeatWagonDBStorage;
import myasoedov.cs.storages.wagons.passenger.SleepWagonDBStorage;
import myasoedov.cs.trains.FreightTrain;
import myasoedov.cs.trains.PassengerTrain;
import myasoedov.cs.wagons.freightWagons.CoveredWagon;
import myasoedov.cs.wagons.freightWagons.PlatformWagon;
import myasoedov.cs.wagons.freightWagons.RefrigeratorWagon;
import myasoedov.cs.wagons.freightWagons.TankWagon;
import myasoedov.cs.wagons.passengerWagons.CoupeWagon;
import myasoedov.cs.wagons.passengerWagons.RestaurantWagon;
import myasoedov.cs.wagons.passengerWagons.SeatWagon;
import myasoedov.cs.wagons.passengerWagons.SleepWagon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class PassengerTrainDBStorage<T extends PassengerTrain<? extends PassengerWagon>> extends TrainDBStorage<T> {
    private final static TrainType TYPE = TrainType.PASSENGER;
    private final static String WAGONS_TABLE = "PASSENGER_WAGONS";

    public PassengerTrainDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, TYPE, WAGONS_TABLE, Configs.createStorageMap(), Configs.createStorageEnumMap());
    }

    public PassengerTrainDBStorage() {
        super(TYPE, WAGONS_TABLE, Configs.createStorageMap(), Configs.createStorageEnumMap());
    }

    @Override
    public boolean save(T item) {
        if (super.save(item)) {
            Storage<CoupeWagon> coupeStorage = new CoupeWagonDBStorage<>(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);
            Storage<SleepWagon> sleepStorage = new SleepWagonDBStorage<>(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);
            Storage<SeatWagon> seatStorage = new SeatWagonDBStorage<>(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);
            Storage<RestaurantWagon> restaurantStorage = new RestaurantWagonDBStorage<>(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);
            item.getWagons().forEach(wagon -> {
                try {
                    if (wagon instanceof CoupeWagon) {
                        coupeStorage.save((CoupeWagon) wagon);
                        //storageList.get(0).save(wagon);
                    } else if (wagon instanceof SleepWagon) {
                        sleepStorage.save((SleepWagon) wagon);
                    } else if (wagon instanceof SeatWagon) {
                        seatStorage.save((SeatWagon) wagon);
                    } else if (wagon instanceof RestaurantWagon) {
                        restaurantStorage.save((RestaurantWagon) wagon);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            return true;
        }
        return false;
    }

    @Override
    public T get(UUID id) {
        DoubleContainer<List<Wagon>, List<Locomotive>> container = super.preGet(id);
        List<PassengerWagon> list = new ArrayList<>();
        container.getFirst().forEach(wagon -> list.add((PassengerWagon) wagon));
        list.sort(Comparator.comparing(Wagon::getNumberInComposition));
        return (T) new PassengerTrain<>(list, container.getSecond(), id);
    }
}
