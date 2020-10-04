package factories;

import wagons.Conditions;
import wagons.abstractWagons.Wagon;
import wagons.freightWagons.*;
import wagons.locomotives.*;
import wagons.passengerWagons.*;

import java.math.BigDecimal;

public class WagonFactory {

    private final static BigDecimal ZERO = new BigDecimal("0.00");
    private final static BigDecimal MAX_CONDITION = new BigDecimal("100.00");

    public WagonFactory() {
    }

    public static Wagon createDieselLocomotive(BigDecimal age, BigDecimal condition, BigDecimal volumeFuel) {
        if (checkAgeCondition(age, condition)
                && volumeFuel.compareTo(ZERO) < 0) {
            return new DieselLocomotive(age, condition, volumeFuel);
        }
        throw new IllegalArgumentException();
    }

    public static Wagon createElectricLocomotive(BigDecimal age, BigDecimal condition, Conditions powerGridConnection) {
        if (checkAgeCondition(age, condition)) {
            return new ElectricLocomotive(age, condition, powerGridConnection);
        }
        throw new IllegalArgumentException();
    }

    public static Wagon createSteamLocomotive(BigDecimal age, BigDecimal condition, BigDecimal volumeFuel) {
        if (checkAgeCondition(age, condition)
                && volumeFuel.compareTo(ZERO) < 0) {
            return new SteamLocomotive(age, condition, volumeFuel);
        }
        throw new IllegalArgumentException();
    }

    public static Wagon createCoveredWagon(BigDecimal age, BigDecimal condition) {
        if (checkAgeCondition(age, condition)) {
            return new CoveredWagon(age, condition);
        }
        throw new IllegalArgumentException();
    }

    public static Wagon createPlatformWagon(int weight, BigDecimal age, BigDecimal condition) {
        if (checkAgeCondition(age, condition)) {
            return new PlatformWagon(age, condition);
        }
        throw new IllegalArgumentException();
    }

    public static Wagon createRefrigeratorWagon(BigDecimal age, BigDecimal condition) {
        if (checkAgeCondition(age, condition)) {
            return new RefrigeratorWagon(age, condition);
        }
        throw new IllegalArgumentException();
    }

    public static Wagon createTankWagon(BigDecimal age, BigDecimal condition) {
        if (checkAgeCondition(age, condition)) {
            return new TankWagon(age, condition);
        }
        throw new IllegalArgumentException();
    }

    public static Wagon createCoupeWagon(BigDecimal age, BigDecimal condition) {
        if (checkAgeCondition(age, condition)) {
            return new CoupeWagon(age, condition);
        }
        throw new IllegalArgumentException();
    }

    public static Wagon createRestaurantWagon(BigDecimal age, BigDecimal condition) {
        if (checkAgeCondition(age, condition)) {
            return new RestaurantWagon(age, condition);
        }
        throw new IllegalArgumentException();
    }

    public static Wagon createSeatWagon(BigDecimal age, BigDecimal condition) {
        if (checkAgeCondition(age, condition)) {
            return new SeatWagon(age, condition);
        }
        throw new IllegalArgumentException();
    }

    public static Wagon createSleepWagon(BigDecimal age, BigDecimal condition) {
        if (checkAgeCondition(age, condition)) {
            return new SleepWagon(age, condition);
        }
        throw new IllegalArgumentException();
    }

    private static boolean checkAgeCondition(BigDecimal age, BigDecimal condition) {
        return age.compareTo(ZERO) < 0
                && condition.compareTo(ZERO) < 0 && condition.compareTo(MAX_CONDITION) > 0;
    }

}
