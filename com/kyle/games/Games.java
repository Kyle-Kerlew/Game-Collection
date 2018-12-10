package com.kyle.games;

import java.util.Scanner;

import com.kyle.initialization.Main;

public abstract class Games {

	private String name;

	protected Games(String name) {
		this.name = name;
	}

	/**
	 * This function takes in all the data prior to beginning gameplay. What is the
	 * user's name? Does the user know how to play? Will the user play against
	 * another user or a computer? What is the other user's name? After that, the
	 * game is initialized with that data.
	 */

	protected void initializeGame() {
		System.out.println("Welcome to the game " + getName() + ".\nWhat would you like your username to be?");
		Scanner sc = new Scanner(System.in);
		String playerOneUser = sc.nextLine();
		System.out.println("Do you know how to play? Yes or no.");
		String help = sc.next();
		if (!help.equalsIgnoreCase("Yes")) {
			howToPlay();
		}
		System.out.println(
				"\nWould you like to play against a computer or another player?\nPress 1 for computer and 2 for another player.");
		int playerMode = sc.nextInt();
		if (playerMode == 1) {
			gameController(1, playerOneUser, "Computer");
		} else if (playerMode == 2) {
			System.out.println("What is the other player's name?");
			String playerTwoUser = sc.next();
			gameController(2, playerOneUser, playerTwoUser);
		} else {
			System.out.println("Invalid selection.");
		}
		sc.close();
	}

	/**
	 * This method will be the game driver for each game.
	 * 
	 * @param playerMode - Against a computer or other players?
	 * @param username   - What are these player's names?
	 */
	protected abstract void gameController(int playerMode, String playerOneUser, String playerTwoUser);

	/**
	 * 
	 * @return whether or not the game is active
	 * @param Whatever params the boolean may need to pass
	 */
	protected abstract boolean gameActive(Object... objects);

	/**
	 * This method handles ending the game process after a winner has been
	 * determined
	 */
	protected void endGame() {
		if (Player.winner == null) {
			System.out.println("It's a draw!");
		} else {
			System.out.printf("Congratulations %s!\n", Player.winner.getUsername());
		}
		System.out.println("Would you like to play another game of " + name + "? Yes or no?");
		Scanner sc = new Scanner(System.in);
		String decision = sc.nextLine();
		if (decision.equalsIgnoreCase("Yes")) {
			switch (name) {
			case "war":
				new War();
				break;
			case "tic tac toe":
				new TicTacToe();
				break;
			default:
				break;
			}
		} else if (decision.equalsIgnoreCase("No")) {
			System.out.println("Would you like to play a different game? Yes or no.");
			if (sc.nextLine().equalsIgnoreCase("Yes")) {
				Main.initializeGameSystem();
			}
			System.exit(0);
		} else {
			System.out.println("Invalid choice.");
		}
		sc.close();
	}

	/**
	 * This method is solely responsible for teaching the user how to play the game
	 * if they don't know.
	 */
	protected abstract void howToPlay();

	/**
	 * This method exists to simulate pauses in gameplay.
	 * 
	 * @param length
	 */
	protected void pause(int length) {
		try {
			Thread.sleep(length);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected String getName() {
		return this.name;
	}
}
