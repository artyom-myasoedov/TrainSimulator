package myasoedov.cs.storages.wagons.freight;

import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.models.Storable;
import myasoedov.cs.models.storages.wagons.FreightWagonDBStorage;
import myasoedov.cs.wagons.freightWagons.PlatformWagon;

import java.math.BigDecimal;
import java.util.List;

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

    @Override
    public Storable get(Long id) {
        List<Object> list = super.preGet(id);
        PlatformWagon wagon = WagonFactory.createPlatformWagon((BigDecimal) list.get(0), (BigDecimal) list.get(1), id);
        wagon.loadCargo(Math.toIntExact((Long) list.get(4)));
        if ((Long) list.get(2) != 0L) {
            wagon.setNumberInComposition((Long) list.get(2));
        } else {
            wagon.setNumberInComposition(null);
        }
        return wagon;
    }
}
