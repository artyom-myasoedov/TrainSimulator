package myasoedov.cs.storages.train;

import myasoedov.cs.models.Storable;
import myasoedov.cs.models.storages.TrainDBStorage;
import myasoedov.cs.trains.PassengerTrain;

public class PassengerTrainDBStorage extends TrainDBStorage {
    private final static String TYPE = "Passenger";
    public PassengerTrainDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, TYPE);
    }

    public PassengerTrainDBStorage() {
        super(TYPE);
    }

    @Override
    public boolean save(Storable item) {
        if (item instanceof PassengerTrain) {
            return super.save(item);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
