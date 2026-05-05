package vehicles;

import interfaces.VehicleTruck;

public class Truck extends Vehicle implements VehicleTruck {
    private int fuel;
    private static final int MAX_FUEL = 200;
    private static final int FUEL_CONSUMPTION = 20;
    private boolean hasCargo = false;
    private int cargoWeight = 0;
    private int baseSpeed;

    public Truck (String model, String name) {
        super(model, 50, name); // base speed 50 rm/turn
        this.baseSpeed = 50;
        this.fuel =MAX_FUEL;
    }

    @Override
    public void loadCargo(int weight) {
        this.hasCargo = true;
        this.cargoWeight = weight;
        // Speed decreases proportionally to cargo weight
        int speedReduction = Math.min(30, weight / 100);
        this.speed = baseSpeed - speedReduction;
        System.out.println(name + " loaded with cargo " + weight + "kg. Speed reduced to " + speed + " km/turn");
    }

    @Override
    public void unloadCargo() {
        this.hasCargo = false;
        this.cargoWeight = 0;
        this.speed = baseSpeed;
        System.out.println(name + " unloaded. Speed restored to " + speed + " km/turn");
    }

    @Override
    public boolean hasCargo() {
        return hasCargo;
    }

    @Override
    public int getCargoWeight() {
        return cargoWeight;
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
