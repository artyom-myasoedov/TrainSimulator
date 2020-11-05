package myasoedov.cs.storages.wagons.freight;

import myasoedov.cs.models.Storable;
import myasoedov.cs.models.storages.wagons.OpeningWagonDBStorage;
import myasoedov.cs.wagons.freightWagons.CoveredWagon;

public class CoveredWagonDBStorage extends OpeningWagonDBStorage {
    private final static String TYPE = "Platform";
    public CoveredWagonDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, TYPE);
    }

    public CoveredWagonDBStorage() {
        super(TYPE);
    }

    @Override
    public boolean save(Storable item) {
        if (item instanceof CoveredWagon) {
            return super.save(item);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
