# Number Processor - Unique and Duplicate Number Analyzer

A Java application that processes user-input integers to calculate the sum of unique numbers and identify duplicate numbers.

## 📋 Description

This program reads integers from the console, analyzes them, and provides two key pieces of information:
- The sum of all numbers that appear exactly once (unique numbers)
- A list of numbers that appear more than once (duplicates)

The program is built with clean architecture principles, separating concerns into logical modules for better maintainability and testability.

## ✨ Features

- **Interactive Console Input**: Users can enter integers line by line until an empty line is entered
- **Robust Error Handling**: Gracefully handles invalid inputs (non-integer values) with user-friendly messages
- **Efficient Analysis**: Uses Java Collections (HashMap) for optimal performance
- **Clean Output**: Well-formatted results without duplicate entries in the duplicate list
- **Modular Design**: Separated into logical components following Single Responsibility Principle

## 🚀 How It Works

1. The program prompts the user to enter integers
2. Users can enter numbers one per line
3. Entering an empty line finishes the input process
4. The program then:
    - Calculates the sum of numbers that appear only once
    - Identifies numbers that appear multiple times
    - Displays the results in a formatted output

### Example
| Enter integers (empty line to finish): | 
---------------------------------------|
| 5                                     
| 3                                                                         
| 5                                                                       
| 8                                                                       
| 3                                     
| 2
| Sum of unique numbers: 10             |
| Duplicate numbers: [3, 5]             |


## 🏗️ Project Structure

The application is organized into logical modules:

    src/
        main/
             java/
                ├── util/
                │   ├── InputHandler.java # Handles console input
                │   ├── NumberAnalyzer.java # Core analysis logic
                │   ├── AnalysisResult.java # Data container for results    
                │   └── OutputFormatter.java # Formats and displays results
                └── Main.java # Main application orchestrator


## 🛠️ Technologies Used

- **Java 17+** (or Java 8+ with minor modifications)
- **JUnit 5** for unit testing
- **Java Collections Framework** (HashMap, List, etc.)

## 📦 Installation and Usage

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- (Optional) Maven for building and testing

### Compilation

```bash
# Navigate to the src directory
cd src

# Compile all Java files
javac main/java/*.java
```

## Running the Application
# From the src directory
java uniqueandduplicate.main.java.Main

## 🧪 Test Coverage
- The application includes comprehensive unit tests covering:
- Mixed unique and duplicate numbers
- All unique numbers
- All duplicate numbers
- Empty input list
- Single number input

## 🔍 Key Implementation Details
### Collections Used
- HashMap: For frequency counting of numbers (O(1) access time)
- List: For storing input numbers and duplicate results
- Immutable Collections: For thread-safe result objects

### Error Handling
- Catches NumberFormatException for non-integer inputs
- Provides clear error messages without crashing
- Continues execution after invalid input

## 📝 Requirements Fulfilled
✅ Program compiles successfully

✅ Correctly calculates sum of unique numbers

✅ Correctly identifies duplicate numbers (no duplicates in output list)

✅ Handles non-integer inputs gracefully

✅ Uses Java Collections appropriately

✅ Clean separation of concerns with logical modules


## 🤝 Contributing
#### Feel free to fork this project and submit pull requests with improvements. Areas for potential enhancement:

- Adding support for reading from files
- Implementing different output formats (JSON, CSV)
- Adding more statistical analysis
- Creating a GUI version

## 📄 License
This project is open source and available under the MIT License.

## 👤 Author
Created as a practical exercise for Skillbox educational platform.

---
Note: This application is designed for educational purposes to demonstrate proper use of Java Collections and modular programming principles.