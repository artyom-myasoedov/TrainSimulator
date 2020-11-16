package myasoedov.cs.factories;

import myasoedov.cs.wagons.locomotives.LocomotiveEngineConditions;
import myasoedov.cs.wagons.freightWagons.*;
import myasoedov.cs.wagons.locomotives.*;
import myasoedov.cs.wagons.passengerWagons.*;

import java.math.BigDecimal;
import java.util.UUID;

public class WagonFactory {

    private final static BigDecimal ZERO = new BigDecimal("0.00");
    private final static BigDecimal MAX_CONDITION = new BigDecimal("100.00");
    private static final BigDecimal MAX_FUEl_DIESEL = new BigDecimal("100.00");
    private static final BigDecimal MAX_FUEL_STEAM = new BigDecimal("120.00");

    public WagonFactory() {
    }

    public static DieselLocomotive createDieselLocomotive(BigDecimal age, BigDecimal condition, BigDecimal volumeFuel, UUID id) {
        if (checkAgeCondition(age, condition)
                && checkVolumeOfFuelDiesel(volumeFuel)) {
            return new DieselLocomotive(age, condition, volumeFuel, id);
        }
        throw new IllegalArgumentException();
    }

    public static ElectricLocomotive createElectricLocomotive(BigDecimal age, BigDecimal condition, LocomotiveEngineConditions powerGridConnection, UUID id) {
        if (checkAgeCondition(age, condition)) {
            return new ElectricLocomotive(age, condition, powerGridConnection, id);
        }
        throw new IllegalArgumentException();
    }

    public static SteamLocomotive createSteamLocomotive(BigDecimal age, BigDecimal condition, BigDecimal volumeFuel, UUID id) {
        if (checkAgeCondition(age, condition)
                && checkVolumeOfFuelSteam(volumeFuel)) {
            return new SteamLocomotive(age, condition, volumeFuel, id);
        }
        throw new IllegalArgumentException();
    }

    public static CoveredWagon createCoveredWagon(BigDecimal age, BigDecimal condition, UUID id) {
        if (checkAgeCondition(age, condition)) {
            return new CoveredWagon(age, condition, id);
        }
        throw new IllegalArgumentException();
    }

    public static PlatformWagon createPlatformWagon(BigDecimal age, BigDecimal condition, UUID id) {
        if (checkAgeCondition(age, condition)) {
            return new PlatformWagon(age, condition, id);
        }
        throw new IllegalArgumentException();
    }

    public static RefrigeratorWagon createRefrigeratorWagon(BigDecimal age, BigDecimal condition, UUID id) {
        if (checkAgeCondition(age, condition)) {
            return new RefrigeratorWagon(age, condition, id);
        }
        throw new IllegalArgumentException();
    }

    public static TankWagon createTankWagon(BigDecimal age, BigDecimal condition, UUID id) {
        if (checkAgeCondition(age, condition)) {
            return new TankWagon(age, condition, id);
        }
        throw new IllegalArgumentException();
    }

    public static CoupeWagon createCoupeWagon(BigDecimal age, BigDecimal condition, UUID id) {
        if (checkAgeCondition(age, condition)) {
            return new CoupeWagon(age, condition, id);
        }
        throw new IllegalArgumentException();
    }

    public static RestaurantWagon createRestaurantWagon(BigDecimal age, BigDecimal condition, UUID id) {
        if (checkAgeCondition(age, condition)) {
            return new RestaurantWagon(age, condition, id);
        }
        throw new IllegalArgumentException();
    }

    public static SeatWagon createSeatWagon(BigDecimal age, BigDecimal condition, UUID id) {
        if (checkAgeCondition(age, condition)) {
            return new SeatWagon(age, condition, id);
        }
        throw new IllegalArgumentException();
    }

    public static SleepWagon createSleepWagon(BigDecimal age, BigDecimal condition, UUID id) {
        if (checkAgeCondition(age, condition)) {
            return new SleepWagon(age, condition, id);
        }
        throw new IllegalArgumentException();
    }

    private static boolean checkAgeCondition(BigDecimal age, BigDecimal condition) {
        return !(age.compareTo(ZERO) < 0)
                && !(condition.compareTo(ZERO) < 0) && !(condition.compareTo(MAX_CONDITION) > 0);
    }

    private static boolean checkVolumeOfFuelDiesel(BigDecimal volumeOfFuel) {
        return !(volumeOfFuel.compareTo(ZERO) < 0) && !(volumeOfFuel.compareTo(MAX_FUEl_DIESEL) > 0);
    }

    private static boolean checkVolumeOfFuelSteam(BigDecimal volumeOfFuel) {
        return !(volumeOfFuel.compareTo(ZERO) < 0) && !(volumeOfFuel.compareTo(MAX_FUEL_STEAM) > 0);
    }

}
