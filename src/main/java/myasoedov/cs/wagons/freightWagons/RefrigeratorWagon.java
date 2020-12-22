package myasoedov.cs.wagons.freightWagons;

import myasoedov.cs.Configs;
import myasoedov.cs.models.storages.Storage;
import myasoedov.cs.storages.wagons.freight.RefrigeratorWagonDBStorage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;

public class RefrigeratorWagon extends OpeningWagon implements Serializable {
    private BigDecimal currentTemperature;
    private final BigDecimal MAX_TEMPERATURE = new BigDecimal(Configs.WagonFillsProperties.getProperty("refrigeratorMaxTemp"));
    private final BigDecimal MIN_TEMPERATURE = new BigDecimal(Configs.WagonFillsProperties.getProperty("refrigeratorMinTemp"));
    private final static Storage<RefrigeratorWagon> storage = new RefrigeratorWagonDBStorage(Configs.JDBC_URL, Configs.USER_NAME, Configs.USER_PAROL);



    public RefrigeratorWagon(BigDecimal age, BigDecimal condition, UUID id) {
        super(Integer.parseInt(Configs.WagonFillsProperties.getProperty("refrigeratorWeight")), age, condition,
                Integer.parseInt(Configs.WagonFillsProperties.getProperty("refrigeratorMaxCarrying")), id);
        currentTemperature = BigDecimal.valueOf(0);
    }

    public static RefrigeratorWagon save(RefrigeratorWagon wagon) throws SQLException {
        storage.save(wagon);
        return wagon;
    }

    public BigDecimal getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(BigDecimal currentTemperature) {
        if (MAX_TEMPERATURE.compareTo(currentTemperature) >= 0 && MIN_TEMPERATURE.compareTo(currentTemperature) <= 0) {
            this.currentTemperature = currentTemperature;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public BigDecimal getMaxTemperature() {
        return MAX_TEMPERATURE;
    }

    public BigDecimal getMinTemperature() {
        return MIN_TEMPERATURE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RefrigeratorWagon that = (RefrigeratorWagon) o;
        return Objects.equals(currentTemperature, that.currentTemperature) && Objects.equals(MAX_TEMPERATURE, that.MAX_TEMPERATURE) && Objects.equals(MIN_TEMPERATURE, that.MIN_TEMPERATURE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), currentTemperature, MAX_TEMPERATURE, MIN_TEMPERATURE);
    }
}
