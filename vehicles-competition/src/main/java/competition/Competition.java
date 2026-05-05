package competition;

import vehicles.Vehicle;

public class Competition {
    private int distance;
    private String name;

    public Competition(String name, int distance) {
        this.name = name;
        this.distance = distance;
    }

    public Vehicle race(Vehicle[] vehicles) {
        System.out.println("\n=== " + name.toUpperCase() + " RACE START ===");
        System.out.println("Distance: " + distance + " km\n");

        boolean raceFinished = false;
        int step = 1;
        Vehicle winner = null;

        while (!raceFinished) {
            System.out.println("\n--- Turn " + step + " ---");

            for (Vehicle v : vehicles) {
                if (v.getX() < distance) {
                    v.move();

                    if (v.getX() >= distance) {
                        winner = v;
                        raceFinished = true;
                        break;
                    }
                }
            }

            step++;

            if (step > 100) { // Safety guard against infinite loop
                System.out.println("Race is taking too long... stopping");
                break;
            }
        }

        System.out.println("\n=== RACE FINISHED ===");
        return winner;
    }

    public void printResults(Vehicle[] vehicles, Vehicle winner) {
        System.out.println("\nRACE RESULTS:");
        System.out.println("==============");
        for (Vehicle v : vehicles) {
            System.out.printf("%-15s: %d km\n", v.getName(), v.getX());
        }

        if (winner != null) {
            System.out.println("\n🏆 WINNER: " + winner.getName() + " (" + winner.getModel() + ")");
            System.out.println("   Final position: " + winner.getX() + " km");
        } else {
            System.out.println("\n❌ No one finished the race");
        }
    }

    public int getDistance() {
        return distance;
    }
}