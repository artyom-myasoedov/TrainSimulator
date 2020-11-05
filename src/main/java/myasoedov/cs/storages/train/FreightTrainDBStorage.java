package myasoedov.cs.storages.train;

import myasoedov.cs.models.Storable;
import myasoedov.cs.models.storages.TrainDBStorage;
import myasoedov.cs.trains.FreightTrain;

public class FreightTrainDBStorage  extends TrainDBStorage {
    private final static String TYPE = "Freight";

    public FreightTrainDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, TYPE);

    }

    public FreightTrainDBStorage() {
        super(TYPE);
    }

    @Override
    public boolean save(Storable item) {
        if (item instanceof FreightTrain) {
            return super.save(item);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
