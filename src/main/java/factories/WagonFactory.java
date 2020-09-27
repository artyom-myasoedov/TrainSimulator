package factories;

import wagons.Conditions;
import wagons.abstractWagons.Wagon;
import wagons.freightWagons.*;
import wagons.locomotives.*;
import wagons.passengerWagons.*;

import java.math.BigDecimal;

public final class WagonFactory {

    private final static BigDecimal ZERO = new BigDecimal("0.00");
    private final static BigDecimal MAX_CONDITION = new BigDecimal("100.00");
    private final static BigDecimal MAX_SPEED = new BigDecimal("150.00");

    private WagonFactory() {
    }

    public static Wagon createDieselLocomotive(int weight, BigDecimal age, BigDecimal condition, BigDecimal maxSpeed, int power, BigDecimal volumeFuel) {
        if (checkWeightAgeCondition(weight, age, condition)
                && checkMaxSpeedPower(maxSpeed, power) && volumeFuel.compareTo(ZERO) < 0) {
            return new DieselLocomotive(weight, age, condition, maxSpeed, power, volumeFuel);
        }
        System.out.println("Incorrect parameters!");
        return null;
    }

    public static Wagon createElectricLocomotive(int weight, BigDecimal age, BigDecimal condition, BigDecimal maxSpeed, int power, Conditions powerGridConnection) {
        if (checkWeightAgeCondition(weight, age, condition)
                && checkMaxSpeedPower(maxSpeed, power)) {
            return new ElectricLocomotive(weight, age, condition, maxSpeed, power, powerGridConnection);
        }
        System.out.println("Incorrect parameters!");
        return null;
    }

    public static Wagon createSteamLocomotive(int weight, BigDecimal age, BigDecimal condition, BigDecimal maxSpeed, int power, BigDecimal volumeFuel) {
        if (checkWeightAgeCondition(weight, age, condition)
                && checkMaxSpeedPower(maxSpeed, power) && volumeFuel.compareTo(ZERO) < 0) {
            return new SteamLocomotive(weight, age, condition, maxSpeed, power, volumeFuel);
        }
        System.out.println("Incorrect parameters!");
        return null;
    }

    public static Wagon createCoveredWagon(int weight, BigDecimal age, BigDecimal condition, int maxCarrying) {
        if (checkWeightAgeCondition(weight, age, condition)
                && maxCarrying > 0) {
            return new CoveredWagon(weight, age, condition, maxCarrying);
        }
        System.out.println("Incorrect parameters!");
        return null;
    }

    public static Wagon createPlatformWagon(int weight, BigDecimal age, BigDecimal condition, int maxCarrying) {
        if (checkWeightAgeCondition(weight, age, condition)
                && maxCarrying > 0) {
            return new PlatformWagon(weight, age, condition, maxCarrying);
        }
        System.out.println("Incorrect parameters!");
        return null;
    }

    public static Wagon createRefrigeratorWagon(int weight, BigDecimal age, BigDecimal condition, int maxCarrying, BigDecimal maxTemperature, BigDecimal minTemperature) {
        if (checkWeightAgeCondition(weight, age, condition)
                && maxCarrying > 0 && maxTemperature.compareTo(minTemperature) > 0) {
            return new RefrigeratorWagon(weight, age, condition, maxCarrying, maxTemperature, minTemperature);
        }
        System.out.println("Incorrect parameters!");
        return null;
    }

    public static Wagon createTankWagon(int weight, BigDecimal age, BigDecimal condition, int maxCarrying) {
        if (checkWeightAgeCondition(weight, age, condition)
                && maxCarrying > 0) {
            return new TankWagon(weight, age, condition, maxCarrying);
        }
        System.out.println("Incorrect parameters!");
        return null;
    }

    public static Wagon createCoupeWagon(int weight, BigDecimal age, BigDecimal condition, int numberOfSeats) {
        if (checkWeightAgeCondition(weight, age, condition)
                && numberOfSeats > 0) {
            return new CoupeWagon(weight, age, condition, numberOfSeats);
        }
        System.out.println("Incorrect parameters!");
        return null;
    }

    public static Wagon createRestaurantWagon(int weight, BigDecimal age, BigDecimal condition, int numberOfSeats) {
        if (checkWeightAgeCondition(weight, age, condition)
                && numberOfSeats > 0) {
            return new RestaurantWagon(weight, age, condition, numberOfSeats);
        }
        System.out.println("Incorrect parameters!");
        return null;
    }

    public static Wagon createSeatWagon(int weight, BigDecimal age, BigDecimal condition, int numberOfSeats) {
        if (checkWeightAgeCondition(weight, age, condition)
                && numberOfSeats > 0) {
            return new SeatWagon(weight, age, condition, numberOfSeats);
        }
        System.out.println("Incorrect parameters!");
        return null;
    }

    public static Wagon createSleepWagon(int weight, BigDecimal age, BigDecimal condition, int numberOfSeats) {
        if (checkWeightAgeCondition(weight, age, condition)
                && numberOfSeats > 0) {
            return new SleepWagon(weight, age, condition, numberOfSeats);
        }
        System.out.println("Incorrect parameters!");
        return null;
    }

    private static boolean checkWeightAgeCondition(int weight, BigDecimal age, BigDecimal condition) {
        return weight < 1 && age.compareTo(ZERO) < 0
                && condition.compareTo(ZERO) < 0 && condition.compareTo(MAX_CONDITION) > 0;
    }

    private static boolean checkMaxSpeedPower(BigDecimal maxSpeed, int power) {
        return maxSpeed.compareTo(ZERO) < 0 && maxSpeed.compareTo(MAX_SPEED) > 0
                && power < 0;
    }
}
