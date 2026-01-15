# Arena Project: Tactical Console RPG

A structured console-based RPG developed in Java, emphasizing clean separation of concerns and object-oriented principles. The project implements a turn-based combat system with AI integration and state-driven navigation.

## 🏗 Project Structure

The application is organized into specialized packages to ensure maintainability:

arena/
├── core/                # Game orchestration and loop management
├── logic/               # Core battle mechanics, state (GameContext), and AI
├── characters/          # OOP Hierarchy (Mage, Warrior, Assassin)
├── config/              # Static constants, game settings, and text templates
├── ui/                  # View layer (frame rendering)
├── helpers/             # Utility classes (Input, Random, Delays)
├── dialogs/             # Localization and system messages
└── system/              # Persistence (Save/Load)

## ⚙️ Technical Highlights

* **State Machine Navigation**: Uses a centralized `GameContext` to handle menu transitions without deep method nesting (avoiding "Recursion Hell").
* **Encapsulated Combat Logic**: Battle mechanics are decoupled from UI, using `CombatIntent` and `RoundResult` objects to pass data between the engine and the display layer.
* **AI Simulation**: Bots evaluate the current combat state to choose between attacking, defending, or using specialized abilities.
* **Data Persistence**: Integrated Save/Load system using Java Object Serialization.

## 🚀 Execution
The entry point of the application is `Game.java`

## 🛠 Tech Stack
* **Language:** Java 21 (or your version)
* **Architecture:** Model-View-Controller (MVC) pattern.
* **Patterns:** State Pattern, Factory Pattern (Character Creation).

## 📖 How to Run
1. Ensure you have JDK 17+ installed.
2. Clone the repository.
3. Run `Game.java` from your IDE or use:
   ```bash
   javac -d bin src/*.java
   java -cp bin Main

