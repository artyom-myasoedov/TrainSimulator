package myasoedov.cs.storages.wagons.locomotives;

import myasoedov.cs.models.storages.wagons.FuelLocomotiveDBStorage;
import myasoedov.cs.storages.wagons.WagonType;
import myasoedov.cs.wagons.locomotives.SteamLocomotive;

public class SteamLocomotiveDBStorage<T extends SteamLocomotive> extends FuelLocomotiveDBStorage<T> {

    public SteamLocomotiveDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, WagonType.STEAM);
    }

    public SteamLocomotiveDBStorage() {
        super(WagonType.STEAM);
    }
}
