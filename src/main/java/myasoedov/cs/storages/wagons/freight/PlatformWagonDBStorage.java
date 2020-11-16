package myasoedov.cs.storages.wagons.freight;

import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.models.storages.wagons.FreightWagonDBStorage;
import myasoedov.cs.storages.wagons.WagonType;
import myasoedov.cs.wagons.freightWagons.PlatformWagon;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class PlatformWagonDBStorage<T extends PlatformWagon> extends FreightWagonDBStorage<T> {
    private final static WagonType TYPE = WagonType.PLATFORM;

    public PlatformWagonDBStorage(String jdbcUrl, String userName, String userParol) {
        super(jdbcUrl, userName, userParol, TYPE);
    }

    public PlatformWagonDBStorage() {
        super(TYPE);
    }

    @Override
    public T get(UUID id) {
        List<Object> list = super.preGet(id);
        PlatformWagon wagon = WagonFactory.createPlatformWagon((BigDecimal) list.get(0), (BigDecimal) list.get(1), id);
        wagon.loadCargo(Math.toIntExact((Long) list.get(5)));

        Long num = (Long) list.get(2) != 0L ? (Long) list.get(2) : null;
        wagon.setNumberInComposition(num);

        String str = (String) list.get(4);
        wagon.setTrainId(null);
        if (str != null) {
            wagon.setTrainId(UUID.fromString(str));
        }
        return (T) wagon;
    }
}
