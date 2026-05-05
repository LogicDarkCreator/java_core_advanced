package test;

import vehicles.*;
import competition.Competition;

public class RaceSimulator {

    public static void runMultipleRaces(int raceCount, int distance) {
        System.out.println("\n=== SIMULATING " + raceCount + " RACES ===");

        int bicycleWins = 0;
        int carWins = 0;
        int teslaWins = 0;
        int truckWins = 0;

        for (int i = 1; i <= raceCount; i++) {
            System.out.println("\n--- Race #" + i + " ---");

            Vehicle bicycle = new Bicycle("GT Avalanche", "Bicycle");
            Vehicle car = new Car("Toyota Camry", "Car");
            Vehicle electricCar = new ElectricCar("Tesla Model 3", "Tesla");
            Vehicle truck = new Truck("Volvo FH", "Truck");

            // Randomly load truck for variety
            if (Math.random() > 0.5) {
                ((Truck) truck).loadCargo(800 + (int)(Math.random() * 1000));
            }

            Vehicle[] participants = {bicycle, car, electricCar, truck};
            Competition competition = new Competition("Race #" + i, distance);
            Vehicle winner = competition.race(participants);

            if (winner != null) {
                switch (winner.getName()) {
                    case "Bicycle": bicycleWins++; break;
                    case "Car": carWins++; break;
                    case "Tesla": teslaWins++; break;
                    case "Truck": truckWins++; break;
                }
            }
        }

        System.out.println("\n=== OVERALL STATISTICS ===");
        System.out.printf("Bicycle wins : %d (%.1f%%)\n",
                bicycleWins, (double)bicycleWins/raceCount * 100);
        System.out.printf("Car wins     : %d (%.1f%%)\n",
                carWins, (double)carWins/raceCount * 100);
        System.out.printf("Tesla wins   : %d (%.1f%%)\n",
                teslaWins, (double)teslaWins/raceCount * 100);
        System.out.printf("Truck wins   : %d (%.1f%%)\n",
                truckWins, (double)truckWins/raceCount * 100);
    }

    public static void main(String[] args) {
        runMultipleRaces(100, 500);
    }
}