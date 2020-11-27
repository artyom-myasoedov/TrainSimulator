package myasoedov.cs.storages.train;

import myasoedov.cs.DoubleContainer;
import myasoedov.cs.models.abstractWagons.Locomotive;
import myasoedov.cs.models.abstractWagons.PassengerWagon;
import myasoedov.cs.models.abstractWagons.Wagon;
import myasoedov.cs.Configs;
import myasoedov.cs.models.storages.TrainDBStorage;
import myasoedov.cs.trains.PassengerTrain;
import myasoedov.cs.wagons.passengerWagons.CoupeWagon;
import myasoedov.cs.wagons.passengerWagons.RestaurantWagon;
import myasoedov.cs.wagons.passengerWagons.SeatWagon;
import myasoedov.cs.wagons.passengerWagons.SleepWagon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class PassengerTrainDBStorage<T extends PassengerTrain<? extends PassengerWagon>> extends TrainDBStorage<T> {
    private final static TrainType TYPE = TrainType.PASSENGER;

    public PassengerTrainDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, TYPE, Configs.DBProperties.getProperty("passengerWagons.table"), Configs.createStorageEnumMap());
    }

    public PassengerTrainDBStorage() {
        super(TYPE, Configs.DBProperties.getProperty("passengerWagons.table"), Configs.createStorageEnumMap());
    }

    @Override
    public boolean save(T item) {
        if (super.save(item)) {
           item.getWagons().forEach(wagon -> {
                try {
                    if (wagon instanceof CoupeWagon) {
                        CoupeWagon.save((CoupeWagon) wagon);
                    } else if (wagon instanceof SleepWagon) {
                        SleepWagon.save((SleepWagon) wagon);
                    } else if (wagon instanceof SeatWagon) {
                        SeatWagon.save((SeatWagon) wagon);
                    } else if (wagon instanceof RestaurantWagon) {
                        RestaurantWagon.save((RestaurantWagon) wagon);
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
