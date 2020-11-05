package myasoedov.cs.storages.wagons.freight;

import myasoedov.cs.models.Storable;
import myasoedov.cs.models.storages.wagons.FreightWagonDBStorage;
import myasoedov.cs.wagons.freightWagons.PlatformWagon;

public class PlatformWagonDBStorage extends FreightWagonDBStorage {
    private final static String TYPE = "Platform";
    public PlatformWagonDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, TYPE);
    }

    public PlatformWagonDBStorage() {
        super(TYPE);
    }

    @Override
    public boolean save(Storable item) {
        if (item instanceof PlatformWagon) {
            return super.save(item);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
