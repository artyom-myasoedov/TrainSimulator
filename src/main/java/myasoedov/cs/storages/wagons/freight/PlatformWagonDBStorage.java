package myasoedov.cs.storages.wagons.freight;

import myasoedov.cs.factories.WagonFactory;
import myasoedov.cs.models.storages.wagons.FreightWagonDBStorage;
import myasoedov.cs.storages.train.AttributeType;
import myasoedov.cs.storages.wagons.WagonType;
import myasoedov.cs.wagons.freightWagons.PlatformWagon;

import java.math.BigDecimal;
import java.util.Map;
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
        Map<AttributeType, Object> map = super.preGet(id);
        PlatformWagon wagon = WagonFactory.createPlatformWagon((BigDecimal) map.get(AttributeType.AGE), (BigDecimal) map.get(AttributeType.CONDITION), id);
        wagon.loadCargo(Math.toIntExact((Long) map.get(AttributeType.CARGO_WEIGHT)));

        Long num = (Long) map.get(AttributeType.NUMBER_IN_COMPOSITION) != 0L ? (Long) map.get(AttributeType.NUMBER_IN_COMPOSITION) : null;
        wagon.setNumberInComposition(num);

        String str = (String) map.get(AttributeType.TRAIN_ID);
        wagon.setTrainId(null);
        if (str != null) {
            wagon.setTrainId(UUID.fromString(str));
        }
        return (T) wagon;
    }
}