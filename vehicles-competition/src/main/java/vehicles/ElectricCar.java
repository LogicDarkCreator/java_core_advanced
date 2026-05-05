package vehicles;

import interfaces.VehicleBatteryCar;

public class ElectricCar extends Vehicle implements VehicleBatteryCar {
    private int batteryCharge;
    private static final int MAX_CHARGE = 100;
    private static final int CHARGE_CONSUMPTION = 8;
    private boolean broken = false;
    private static final double BREAK_PROBABILITY = 0.05; // %% chance of breakdown

    public ElectricCar(String model, String name) {
        super(model, 70, name); // speed 70 km/turn
        this.batteryCharge = MAX_CHARGE;
    }

    @Override
    public boolean isBroken() {
        if (!broken) {
            broken = Math.random() <= BREAK_PROBABILITY;
            if (broken) {
                System.out.println(name + " broke down");
            }
        }
        return broken;
    }

    @Override
    public void repair() {
        broken = false;
        System.out.println(name + " has been repaired");
    }

    @Override
    public double getBreakProbability() {
        return BREAK_PROBABILITY;
    }

    @Override
    public int getRemainingRange() {
        return (batteryCharge / CHARGE_CONSUMPTION) * speed;
    }

    @Override
    public boolean hasResource() {
        return batteryCharge >= CHARGE_CONSUMPTION;
    }

    @Override
    public void recharge() {
        batteryCharge = MAX_CHARGE;
        System.out.println(name + " recharged");
    }

    @Override
    public int getResourceLevel() {
        return batteryCharge;
    }

    @Override
    public void move() {
        if (isBroken()) {
            System.out.println(name + " is broken and cannot move");
            return;
        }

        if(!hasResource()) {
            System.out.println(name + " battery depleted, going to recharge");
            recharge();
            return;
        }

        batteryCharge -= CHARGE_CONSUMPTION;
        super.move();
        System.out.println(name + " remaining battery: " + batteryCharge + "%");
    }
}
