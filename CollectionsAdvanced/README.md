## User Management System
A Java console application for managing user data with efficient data structures and command-line interface.

## 📋 Description
This program allows users to manage a collection of people by storing their passport numbers, names, and ages. It implements various commands to add, delete, query, and analyze user data with specific time complexity requirements.

## 🚀 Features
- Add users - Store passport number, name, and age
- Delete users - Remove users by passport number
- Count users - Display total number of users
- Statistics - Calculate average and median age
- Find extremes - Locate youngest and oldest users
- Display all - Print all users sorted by age
- Help system - Built-in command reference

## ⚙️ Time Complexities
| Command   | Complexity | 	    Implementation      |
|-----------|------------|---------------------------|
| in	    | O(1)	      |HashMap for passport lookup
|del	| O(1)	      |HashMap for direct access
|count	| O(1)	|Direct size retrieval
|young	|O(log n)	|TreeSet for ordered access
|old	|O(log n)	|TreeSet for ordered access
|avg	|O(n)	|Iterates through all users
|median	|O(n)	|Requires sorting/iteration
|print	|O(n)	|Iterates through sorted set
## 🎮 Available Commands
| Command  |	Description |
|----------|----------------|
| in	   | Add a new user (checks for duplicate passport first)
|del	   | Delete a user by passport number
|count	   | Show total number of users
|avg	   | Calculate and display average age
|median	   | Calculate and display median age
|young	   | Show the youngest user
|old	   | Show the oldest user
|print	   | Display all users sorted by age
|help	   | Show available commands
|exit	   | Exit the program
## 📁 Project Structure
```
src/
    main/
        java/
            ├── util/
            │   ├── User.java                   # User data model
            │   ├── UserManager.java            # Core business logic and data storage
            │   ├── UserInputHandler.java       # Handles all user input operations
            │   ├── CommandHandler.java         # Processes commands and coordinates actions
            └── Main.java   # Main application orchestrator
```

## 🔧 Installation & Setup
### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Command-line interface

## Compilation
```bash
# Navigate to the src directory
cd src

# Compile all Java files
javac *.java
```

## Running the Program
```bash
# Run the main class
java UserManagementSystem
```

## 💻 Usage Example
```text
Available commands:
  in     - Add a new user
  del    - Delete a user
  count  - Show number of users
  avg    - Calculate average age
  median - Calculate median age
  young  - Show youngest user
  old    - Show oldest user
  print  - Print all users sorted by age
  help   - Show this help
  exit   - Exit the program

Enter command: in
Enter passport number: AB123456
Enter name: John Doe
Enter age: 25
User added successfully

Enter command: in
Enter passport number: CD789012
Enter name: Jane Smith
Enter age: 30
User added successfully

Enter command: count
Number of users: 2

Enter command: avg
Average age: 27.5

Enter command: print
All users (sorted by age):
Passport: AB123456, Name: John Doe, Age: 25
Passport: CD789012, Name: Jane Smith, Age: 30

Enter command: exit
Program terminated
```

## 🧠 Design Decisions
### Data Structures
- HashMap (usersByPassport): Provides O(1) access for passport-based operations
- TreeSet (usersByAge): Maintains sorted order for O(log n) youngest/oldest access

## Class Design
- User: Immutable data carrier with proper toString() implementation
- UserManager: Encapsulates all data operations with complexity guarantees
- UserInputHandler: Separates input logic for better testability
- CommandHandler: Processes commands and handles user interactions
- UserManagementSystem: Main application coordinator

## 🔍 Error Handling
The program handles various error cases:
- Invalid commands
- Duplicate passport numbers
- Non-existent users for deletion
- Invalid age input (non-numeric or negative)
- Empty system state for statistics operations

## 📊 Sample Outputs
Adding Duplicate User
```text
Enter command: in
Enter passport number: AB123456
User with this passport number already exists
```
## Invalid Age Input
```text
Enter command: in
Enter passport number: XY789012
Enter name: Test User
Enter age: -5
Age must be non-negative
```
## Empty System Operations
```text
Enter command: avg
No users in the system
```
## 🤝 Contributing
Feel free to submit issues or enhancement requests. Some ideas for improvements:
- Add persistent storage (file/database)
- Implement more statistical functions
- Add search by name functionality
- Create GUI version

## 📝 License
This project is created for educational purposes as part of a programming practice exercise.

## ✨ Acknowledgments
- Skillbox for the practical assignment requirements
- Java Collections Framework for efficient data structures
