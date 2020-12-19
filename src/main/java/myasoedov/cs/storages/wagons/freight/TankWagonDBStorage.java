package myasoedov.cs.storages.wagons.freight;

import myasoedov.cs.models.storages.wagons.OpeningWagonDBStorage;
import myasoedov.cs.storages.wagons.WagonType;
import myasoedov.cs.wagons.freightWagons.TankWagon;

public class TankWagonDBStorage extends OpeningWagonDBStorage<TankWagon> {

    public TankWagonDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, WagonType.TANK);
    }

    public TankWagonDBStorage() {
        super(WagonType.TANK);
    }
}
