package myasoedov.cs.storages.train;

import myasoedov.cs.DoubleContainer;
import myasoedov.cs.models.abstractWagons.FreightWagon;
import myasoedov.cs.models.abstractWagons.Locomotive;
import myasoedov.cs.models.abstractWagons.Wagon;
import myasoedov.cs.models.storages.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.models.storages.TrainDBStorage;
import myasoedov.cs.models.storages.wagons.FreightWagonDBStorage;
import myasoedov.cs.storages.wagons.freight.CoveredWagonDBStorage;
import myasoedov.cs.storages.wagons.freight.PlatformWagonDBStorage;
import myasoedov.cs.storages.wagons.freight.RefrigeratorWagonDBStorage;
import myasoedov.cs.storages.wagons.freight.TankWagonDBStorage;
import myasoedov.cs.trains.FreightTrain;
import myasoedov.cs.wagons.freightWagons.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;


public class FreightTrainDBStorage<T extends FreightTrain<? extends FreightWagon>> extends TrainDBStorage<T> {
    private final static TrainType TYPE = TrainType.FREIGHT;
    private final static String WAGONS_TABLE = "FREIGHT_WAGONS";
    private final static Storage<TankWagon> tankStorage = new TankWagonDBStorage<>(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);
    private final static Storage<CoveredWagon> coveredStorage = new CoveredWagonDBStorage<>(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);
    private final static Storage<PlatformWagon> platformStorage = new PlatformWagonDBStorage<>(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);
    private final static Storage<RefrigeratorWagon> refrigeratorStorage = new RefrigeratorWagonDBStorage<>(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);


    public FreightTrainDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, TYPE, WAGONS_TABLE, Configs.createStorageMap(), Configs.createStorageEnumMap());
    }

    public FreightTrainDBStorage() {
        super(TYPE, WAGONS_TABLE, Configs.createStorageMap(), Configs.createStorageEnumMap());
    }

    @Override
    public boolean save(T item) {
        if (super.save(item)) {
            item.getWagons().forEach(wagon -> {
                try {
                    if (wagon instanceof TankWagon) {
                        tankStorage.save((TankWagon) wagon);
                    } else if (wagon instanceof CoveredWagon) {
                        coveredStorage.save((CoveredWagon) wagon);
                    } else if (wagon instanceof RefrigeratorWagon) {
                        refrigeratorStorage.save((RefrigeratorWagon) wagon);
                    } else if (wagon instanceof PlatformWagon) {
                        platformStorage.save((PlatformWagon) wagon);
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
