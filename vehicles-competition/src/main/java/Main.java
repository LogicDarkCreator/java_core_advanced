import vehicles.*;
import competition.Competition;

public class Main {
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("   VEHICLE RACING COMPETITION");
        System.out.println("======================================");

        // Create different vehicles
        Vehicle bicycle = new Bicycle("GT Avalanche", "Bicycle");
        Vehicle car = new Car("Toyota Camry", "Car");
        Vehicle electricCar = new ElectricCar("Tesla Model 3", "Tesla");
        Vehicle truck = new Truck("Volvo FH", "Truck");

        // Load the truck for added challenge
        if (truck instanceof Truck) {
            ((Truck) truck).loadCargo(1200);
        }

        // Array of participants
        Vehicle[] participants = {bicycle, car, electricCar, truck};

        // Display race information
        System.out.println("\nRACE PARTICIPANTS:");
        System.out.println("------------------");
        for (Vehicle v : participants) {
            System.out.printf("• %-10s: %s (Speed: %d km/turn)\n",
                    v.getName(), v.getModel(), v.getSpeed());
        }

        // Create competition with 500 km distance
        Competition competition = new Competition("Grand Prix", 500);

        // Run the race
        Vehicle winner = competition.race(participants);

        // Print results
        competition.printResults(participants, winner);

        // Print statistics
        System.out.println("\nRACE STATISTICS:");
        System.out.println("================");
        for (Vehicle v : participants) {
            System.out.printf("%s completed %.1f%% of the race\n",
                    v.getName(), (double)v.getX() / competition.getDistance() * 100);
        }
    }
}