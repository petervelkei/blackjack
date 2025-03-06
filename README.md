# Blackjack Game

A comprehensive, Java-based Blackjack game that combines classic casino gameplay with a professional graphical user interface. This project features a full betting system, standard Blackjack rules (including options to hit, stand, split, and double), and an engaging Swing-based UI.

---

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Game Rules](#game-rules)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

---

## Introduction

This project is a full implementation of Blackjack in Java, designed to offer both an enjoyable game experience and a learning tool for software developers. The game is built using Java Swing for its GUI, and it adheres to traditional Blackjack rules with added functionalities like a betting system and card splitting.

---

## Features

- **Standard Blackjack Gameplay:**  
  Play a classic game of Blackjack with rules such as hitting, standing, and doubling down.
  
- **Betting System:**  
  Manage your in-game money using the `CoinPurse` class. Place bets, win payouts, and track your balance as you progress through rounds.

- **Graphical User Interface:**  
  Enjoy an interactive gaming experience through a well-designed Swing-based UI (`GameUI`), featuring card animations and real-time updates.

- **Game Logic & Management:**  
  The project is modular with clear separation of concerns:
  - **Card and Deck Management:** Classes such as `Card`, `Deck`, `Rank`, and `Suit` manage the representation and shuffling of cards.
  - **Game Mechanics:** The `Dealer` and `Hand` classes handle the gameplay, including dealing cards, checking for Blackjack, and managing split hands.
  - **Game Entry Point:** The `Main` class initializes and launches the game.

---

## Installation

1. **Prerequisites:**  
   Ensure you have **Java 8** (or later) installed on your system.

2. **Clone the Repository:**

   ```bash
   git clone https://github.com/petervelkei/blackjack.git
   cd blackjack
   

3. **Compile the Source Code:**  
   The source files are located in the project's root or organized within packages. Compile using:

   ```bash
   javac -d bin src/blackjack/*.java
   ```

4. **Run the Game:**  
   Launch the game by executing the `Main` class:

   ```bash
   java -cp bin blackjack.Main
   ```

---

## Usage

When you start the game, the following sequence occurs:

1. **Betting:**  
   A betting system is in place where the player starts with a preset balance (managed by `CoinPurse`). Adjust your bet using the provided slider and press the **Bet** button.

2. **Dealing:**  
   The dealer (`Dealer` class) deals two cards each to the player and itself. One of the dealer’s cards is hidden.

3. **Gameplay Options:**  
   Use the action buttons to:
   - **Hit:** Draw another card.
   - **Stay:** End your turn.
   - **Double:** Double your bet and receive one additional card.
   - **Split:** If eligible, split your hand into two separate hands.
   
4. **Game Outcome:**  
   The game automatically checks for busts, Blackjacks, and compares hands to determine the winner. The UI updates your balance and displays the outcome of each round.

---

## Game Rules

- **Objective:** Get as close to 21 as possible without exceeding it, and beat the dealer’s hand.
- **Card Values:**
  - Number cards count as their face value.
  - Face cards (King, Queen, Jack) are worth 10 points.
  - Aces can count as either 1 or 11, depending on what benefits your hand most.
- **Gameplay:**
  - **Initial Deal:** Both the player and the dealer receive two cards.
  - **Player Turn:** Choose to **hit** (draw a card), **stay** (end your turn), **double down** (double your bet and receive one card), or **split** (if you have a pair).
  - **Dealer Turn:** The dealer reveals its hidden card and must hit until the hand value is 17 or higher.
- **Betting:**  
  Bets are placed at the beginning of each round. Winning pays out 1:1, while a Blackjack payout is 3:2. The game updates your balance after each round based on the outcome.

---

## Project Structure

- **Card.java, Rank.java, Suit.java:**  
  Represent the fundamental properties of a playing card.
  
- **Deck.java:**  
  Manages the collection and shuffling of cards.

- **Hand.java:**  
  Represents a player’s or dealer’s hand and supports operations like splitting and calculating hand values.

- **CoinPurse.java:**  
  Handles the betting system and player’s balance.

- **Dealer.java:**  
  Contains the core game logic, including dealing cards, handling player actions, and determining round outcomes.

- **GameUI.java:**  
  Implements the graphical user interface using Swing, displaying cards, buttons, and player information.

- **Main.java:**  
  Entry point for the application that initializes the game by launching the dealer and the UI.

---

## Contributing

Contributions are highly welcome! If you have ideas to improve the game, fix bugs, or add new features, please follow these steps:

1. **Fork the Repository:**  
   Click the **Fork** button on GitHub to create a personal copy of the repository.

2. **Create a New Branch:**  
   Use a branch name that describes your feature or fix, for example:
   ```bash
   git checkout -b feature/new-gui-improvements
   ```

3. **Make Your Changes:**  
   Follow clean coding practices. Make sure your changes adhere to the project’s style and structure.

4. **Commit Your Changes:**  
   Provide clear, descriptive commit messages.
   ```bash
   git commit -m "Added new GUI enhancements for smoother animations"
   ```

5. **Push and Submit a Pull Request:**  
   Push your branch to GitHub and create a pull request detailing your changes and their benefits.

---

## License

This project is licensed under the **MIT License**. You are free to use, modify, and distribute this software according to the terms of the license.

---

*Enjoy playing Blackjack and happy coding!*
