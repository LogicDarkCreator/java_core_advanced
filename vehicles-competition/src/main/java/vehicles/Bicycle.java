package vehicles;

import interfaces.Breakable;

public class Bicycle extends Vehicle implements Breakable {
    private boolean broken = false;
    private static final double BREAK_PROBABILITY = 0.15; // 15% chance of breakdown

    public Bicycle(String model, String name) {
        super(model, 15, name);
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
    public void move() {
        if (isBroken()) {
            System.out.println(name + " is broken and cannot move");
            return;
        }
        super.move();
    }
}