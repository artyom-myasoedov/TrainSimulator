package myasoedov.cs.storages.wagons.locomotives;

import myasoedov.cs.models.storages.wagons.FuelLocomotiveDBStorage;
import myasoedov.cs.storages.wagons.WagonType;
import myasoedov.cs.wagons.locomotives.DieselLocomotive;


public class DieselLocomotiveDBStorage<T extends DieselLocomotive> extends FuelLocomotiveDBStorage<T> {

    public DieselLocomotiveDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, WagonType.DIESEL);
    }

    public DieselLocomotiveDBStorage() {
        super(WagonType.DIESEL);
    }

}
