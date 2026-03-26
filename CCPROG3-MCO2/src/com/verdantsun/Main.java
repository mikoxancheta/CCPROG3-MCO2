package com.verdantsun;
import java.util.*;

/**
 * Main class for Verdant Sun Farming Simulator.
 * <p>
 * This class contains the entry point of the game. It prompts the user
 * for their name, creates a new Game instance, and starts the game loop.
 * </p>
 */
public class Main {

    /**
     * The entry point of the application.
     * <p>
     * Prompts the player to enter their name, initializes the game,
     * and starts the game loop.
     * </p>
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        Game game = new Game(name);
        game.startGame();
    }
}
