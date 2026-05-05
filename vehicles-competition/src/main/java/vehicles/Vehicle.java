package vehicles;

public abstract class Vehicle {
    protected String model;
    protected int speed;
    protected String name;
    protected int x;

    public Vehicle(String model, int speed, String name) {
        this.model = model;
        this.speed = speed;
        this.name = name;
        this.x = 0;
    }

    public void move() {
        this.x += speed;
        System.out.println(name + " moved " + speed + " km. Total: " + x + " km");
    }

    public String getModel() {
        return model;
    }

    public int getSpeed() {
        return speed;
    }

    public int getX() {
        return x;
    }

    public String getName() {
        return name;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return name + " (" + model + ") at position " + x + " km";
    }
}
