# Vehicle Racing Competition 🏁

A Java-based simulation program for conducting races between different types of vehicles, each with unique characteristics and behaviors.

## 📋 Overview

This project demonstrates object-oriented programming concepts through a racing simulation. Different vehicle types implement various interfaces that define their specific behaviors such as refueling, breaking down, limited resources, and cargo capacity.

## 🚗 Vehicle Types

| Vehicle | Speed | Special Characteristics | Interfaces          |
|---------|-------|------------------------|---------------------|
| **Bicycle** | 15 km/turn | High breakdown probability (15%) | `Breakable`         |
| **Car** | 60 km/turn | Requires fuel, medium breakdown (10%) | `VehicleSimpleCar`  |
| **Electric Car** | 70 km/turn | Battery-powered, low breakdown (5%) | `VehicleBatteryCar` |
| **Truck** | 50 km/turn | Cargo affects speed, high fuel consumption | `VehicleTruck`      |

## 🎯 Features

- **Multiple vehicle types** with distinct behaviors
- **Various interfaces** representing different capabilities
- **Breakdown system** with probability-based failures
- **Fuel/resource management** for powered vehicles
- **Cargo system** affecting vehicle performance
- **Race simulation** with turn-by-turn progress
- **Winner determination** based on distance completion
- **Statistics tracking** for multiple races

## 🏗️ Project Structure

    src/
        main/
            java/
            ├── interfaces/ # Interface definitions
            │   ├── Refuelled.java # Fuel management
            │   ├── Breakable.java # Breakdown functionality
            │   ├── LimitedResource.java # Resource management
            │   ├── VehicleBatteryCar.java # Combines LimitedResource, Breakable into one
            │   ├── VehicleSimpleCar.java # Combines Refuelled, Breakable into one
            │   ├── VehicleTruck.java # Combines Refuelled, CargoCapable into one
            │   └── CargoCapable.java # Cargo handling
            ├── vehicles/ # Vehicle implementations
            │   ├── Vehicle.java # Abstract base class
            │   ├── Bicycle.java # Bicycle implementation
            │   ├── Car.java # Car implementation
            │   ├── ElectricCar.java # Electric car implementation
            │   └── Truck.java # Truck implementation
            ├── competition/ # Competition logic
            │   └── Competition.java # Race management
            ├── test/ # Testing utilities (optional)
            │   └── RaceSimulator.java # Multi-race simulator
            └── Main.java # Main application entry

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Any Java IDE (Eclipse, IntelliJ IDEA, VS Code) or command line tools

### Installation

1. Clone the repository:
```bash
  git clone https://github.com/yourusername/vehicle-racing-competition.git
  cd vehicle-racing-competition
```

2. Compile the Java fiels
```bash
  java -cp bin Main
```

3. Run the main program
```bash
java -cp bin Main
```

## 💻 Usage Example
```java
// Create vehicles
Vehicle bicycle = new Bicycle("GT Avalanche", "Bicycle");
Vehicle car = new Car("Toyota Camry", "Car");
Vehicle electricCar = new ElectricCar("Tesla Model 3", "Tesla");
Vehicle truck = new Truck("Volvo FH", "Truck");

// Load truck with cargo
((Truck) truck).loadCargo(1200);

// Array of participants
Vehicle[] participants = {bicycle, car, electricCar, truck};

// Create competition (500 km distance)
Competition competition = new Competition("Grand Prix", 500);

// Run the race
Vehicle winner = competition.race(participants);

// Display results
competition.printResults(participants, winner);
```

## 🎲 Vehicle Characteristics
### Bicycle

- **Speed:** 15 km per turn

- **Breakdown probability:** 15%

- **Special:** No fuel needed, simple but unreliable

### Car
- **Speed:** 60 km per turn

- **Fuel capacity:** 100 units

- **Fuel consumption:** 10 units per turn

- **Breakdown probability:** 10%

- **Special:** Balanced performance, requires refueling

### Electric Car
- **Speed:** 70 km per turn

- **Battery capacity:** 100 units

- **Energy consumption:** 8 units per turn

- **Breakdown probability:** 5%

- **Special:** Fast but limited range, needs recharging

### Truck
- **Base speed:** 50 km per turn

- **Fuel capacity:** 200 units

- **Fuel consumption:** 20 units per turn

- **Cargo:** Speed decreases with load

- **Special:** Can carry cargo, high fuel consumption

###  🏁 Race Mechanics
1. Each vehicle moves according to its speed per turn

2. Vehicles may break down with certain probability

3. Fuel-powered vehicles need refueling when empty

4. Electric vehicles need recharging when battery is low

5. Trucks move slower when carrying cargo

6. First vehicle to reach or exceed the distance wins

7. Vehicles that break down or run out of resources skip turns

## 📊 Sample Output
```text
=== GRAND PRIX RACE START ===
Distance: 500 km

--- Turn 1 ---
Bicycle moved 15 km. Total: 15 km
Car moved 60 km. Total: 60 km. Remaining fuel: 90%
Tesla moved 70 km. Total: 70 km. Remaining battery: 92%
Truck moved 30 km. Total: 30 km. Remaining fuel: 180%

...

=== RACE FINISHED ===

RACE RESULTS:
==============
Bicycle       : 495 km
Car           : 500 km
Tesla         : 490 km
Truck         : 480 km

🏆 WINNER: Car (Toyota Camry)
   Final position: 500 km
```

## 🧪 Testing

### Run multiple races to see statistics:
```bash
java -cp bin test.RaceSimulator
```

### This will simulate 100 races and show win statistics:
```text
Bicycle wins : 12 (12.0%)
Car wins     : 45 (45.0%)
Tesla wins   : 38 (38.0%)
Truck wins   : 5 (5.0%)
```

## 🔧 Customization

### You can easily:

- **Add new vehicle types**
- **Create new interfaces for additional behaviors**
- **Adjust speeds, probabilities, and consumptions**
- **Modify race distance**
- **Add new competition rules**

## 📚 Learning Objectives

### This project demonstrates:

- **Inheritance -** Vehicle base class

- **Interfaces -** Multiple behavior contracts

- **Polymorphism -** Different move() implementations

- **Encapsulation -** Private fields with public methods

- **Abstraction -** Abstract class and interfaces

- **Object-oriented design -** Clean separation of concerns

## 🤝 Contributing

### Feel free to:

- **Add new vehicle types**

- **Implement additional interfaces**

- **Improve the racing algorithm**

- **Add GUI or more advanced features**

- **Write additional tests**

## 📝 License

### This project is open source and available for educational purposes.

## ✨ Acknowledgments

- **Inspired by object-oriented programming concepts**
- **Designed for learning Java interfaces and inheritance**
- **Created for educational demonstrations**

---

#### Happy Racing! 🏎️💨