package com.example.kevin.portandstarboard;

import java.util.Arrays;
import java.util.Collections;

/**
 * Immutable game class which randomly picks a side of the ship (port or starboard)
 * for the user to respond with an answer.
 *
 * Supports getting the name of the chosen side, and checking if an answer is correct.
 */

public class Game {
    public enum Side {
        PORT("Port"),
        STARBOARD("Starboard");

        private String name;
        private Side(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    };


    private Side winner = Side.PORT;

    public Game() {
        // Pick a winner:
        Side buttons[] = {Side.PORT, Side.STARBOARD};
        Collections.shuffle(Arrays.asList(buttons));
        winner = buttons[0];
    }

    public String getChosenSideName() {
        return winner.getName();
    }

    public boolean checkIfCorrect(Side side) {
        return (winner == side);
    }


    /**
     * If you want to see how to use this class, run this main()
     *   (Not used when running under Android, but you can still run
     *    this through Android Studios)
     * @param args
     */
    public static void main(String[] args) {
        // Create a "game"
        Game game = new Game();

        // Display what's happening:
        String prompt = "Which side is '" + game.getChosenSideName() + "'?";
        System.out.println(prompt);

        // This would be read in from the user (or button press)
        Game.Side userGuess = Game.Side.PORT;

        // Check the answer:
        String endMessage;
        if (game.checkIfCorrect(userGuess)) {
            endMessage = "Correct!";
        } else {
            endMessage = "Incorrect guess; please try again.";
        }
        System.out.println(endMessage);
    }
}