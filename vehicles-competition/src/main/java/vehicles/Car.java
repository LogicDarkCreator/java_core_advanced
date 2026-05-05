package vehicles;

import interfaces.VehicleSimpleCar;

public class Car extends Vehicle implements VehicleSimpleCar {
    private int fuel;
    private static final int MAX_FUEL = 100;
    private static final int FUEL_CONSUMPTION = 10;
    private boolean broken = false;
    private static final double BREAK_PROBABILITY = 0.1; // 10 % chance of breakdown

    public Car(String model, String name) {
        super(model, 60, name); // speed 60 km/turn
        this.fuel = MAX_FUEL;
    }

    @Override
    public boolean isBroken() {
        if (!broken) {
            broken = Math.random() <= BREAK_PROBABILITY;
            if (broken) {
                System.out.println(name + " broke down!");
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
    public void refuel() {
        fuel = MAX_FUEL;
        System.out.println(name + " refueled");
    }

    @Override
    public int getFuel() {
        return fuel;
    }

    @Override
    public int getFuelConsumption() {
        return FUEL_CONSUMPTION;
    }

    @Override
    public void move() {
        if (isBroken()) {
            System.out.println(name + " is broken and cannot move");
            return;
        }

        if (fuel < FUEL_CONSUMPTION) {
            System.out.println(name + " out of fuel, going to refuel");
            refuel();
            return;
        }

        fuel -= FUEL_CONSUMPTION;
        super.move();
        System.out.println(name + " remaining fuel: " + fuel + "%");
    }
}
