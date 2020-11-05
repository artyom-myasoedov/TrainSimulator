package myasoedov.cs.storages.wagons.freight;

import myasoedov.cs.models.Storable;
import myasoedov.cs.models.storages.wagons.OpeningWagonDBStorage;
import myasoedov.cs.wagons.freightWagons.TankWagon;

public class TankWagonDBStorage extends OpeningWagonDBStorage {
    private final static String TYPE = "Tank";

    public TankWagonDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, TYPE);
    }

    public TankWagonDBStorage() {
        super(TYPE);
    }

    @Override
    public boolean save(Storable item) {
        if (item instanceof TankWagon) {
            return super.save(item);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
