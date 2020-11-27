package myasoedov.cs.storages.wagons.freight;

import myasoedov.cs.models.storages.wagons.OpeningWagonDBStorage;
import myasoedov.cs.storages.wagons.WagonType;
import myasoedov.cs.wagons.freightWagons.CoveredWagon;

public class CoveredWagonDBStorage<T extends CoveredWagon> extends OpeningWagonDBStorage<T> {

    public CoveredWagonDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, WagonType.COVERED);
    }

    public CoveredWagonDBStorage() {
        super(WagonType.COVERED);
    }

}
