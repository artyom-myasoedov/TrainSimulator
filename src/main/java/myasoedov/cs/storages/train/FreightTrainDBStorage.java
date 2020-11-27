package myasoedov.cs.storages.train;

import myasoedov.cs.DoubleContainer;
import myasoedov.cs.models.abstractWagons.FreightWagon;
import myasoedov.cs.models.abstractWagons.Locomotive;
import myasoedov.cs.models.abstractWagons.Wagon;
import myasoedov.cs.Configs;
import myasoedov.cs.models.storages.TrainDBStorage;
import myasoedov.cs.trains.FreightTrain;
import myasoedov.cs.wagons.freightWagons.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;


public class FreightTrainDBStorage<T extends FreightTrain<? extends FreightWagon>> extends TrainDBStorage<T> {
    private final static TrainType TYPE = TrainType.FREIGHT;

    public FreightTrainDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, TYPE, Configs.DBProperties.getProperty("freightWagons.table"), Configs.createStorageEnumMap());
    }

    public FreightTrainDBStorage() {
        super(TYPE, Configs.DBProperties.getProperty("freightWagons.table"), Configs.createStorageEnumMap());
    }

    @Override
    public boolean save(T item) {
        if (super.save(item)) {
              item.getWagons().forEach(wagon -> {
                try {
                    if (wagon instanceof TankWagon) {
                        TankWagon.save((TankWagon) wagon);
                    } else if (wagon instanceof CoveredWagon) {
                        CoveredWagon.save((CoveredWagon) wagon);
                    } else if (wagon instanceof RefrigeratorWagon) {
                        RefrigeratorWagon.save((RefrigeratorWagon) wagon);
                    } else if (wagon instanceof PlatformWagon) {
                        PlatformWagon.save((PlatformWagon) wagon);
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
        List<FreightWagon> list = new ArrayList<>();
        container.getFirst().forEach(wagon -> list.add((FreightWagon) wagon));
        list.sort(Comparator.comparing(Wagon::getNumberInComposition));
        return (T) new FreightTrain<>(list, container.getSecond(), id);
    }
}
