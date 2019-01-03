package com.kyle.games;

import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * @author kylek TODO: Change to 2D array
 */
public class TicTacToe extends Games {

	public TicTacToe() {
		super("Tic Tac Toe");
		initializeGame();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kyle.games.Games#gameController(int, java.lang.String[])
	 */
	public void gameController(int playerMode, String playerOneUser, String playerTwoUser) {

		LinkedHashMap<Integer, String> table = new LinkedHashMap<>();

		Player playerOne = new Player();
		Player playerTwo = new Player();
		playerOne.setUsername(playerOneUser);
		playerTwo.setUsername(playerTwoUser);
		Scanner sc = new Scanner(System.in);

		setTable(table);
		displayTable(table);

		while (gameActive(table, playerOne, playerTwo)) {
			System.out.println(
					playerOne.getUsername() + ", make your move by typing in the number of where you'd like to move.");
			int move;
			move = sc.nextInt();
			if (move > 9 || move < 1) {
				System.out.println("Invalid number.");
			} else if (table.containsValue(Integer.toString(move))) {
				table.replace(move, "X");
				displayTable(table);
			} else {
				System.out.printf("%s has already moved there. Select another space.\n",
						table.get(move).equalsIgnoreCase("X") ? playerOne.getUsername() : playerTwo.getUsername());
			}
			if (gameActive(table, playerOne, playerTwo)) {
				System.out.println(playerTwo.getUsername()
						+ ", make your move by typing in the number of where you'd like to move.");
				move = sc.nextInt();
				if (move > 9 || move < 1) {
					System.out.println("Invalid number.");
				} else if (table.containsValue(Integer.toString(move))) {
					table.replace(move, "O");
					displayTable(table);
				} else {
					System.out.printf("%s has already moved there. Select another space.",
							table.get(move).equalsIgnoreCase("X") ? playerOne.getUsername() : playerTwo.getUsername());
				}
			}
		}
		endGame();
		sc.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kyle.games.Games#howToPlay()
	 */
	public void howToPlay() {
		System.out.println("1. The game is played on a grid that's 3 squares by 3 squares.\r\n" + "\r\n"
				+ "2. You are X, your friend (or the computer in this case) is O. Players take turns putting their marks in empty squares.\r\n"
				+ "\r\n"
				+ "3. The first player to get 3 of her marks in a row (up, down, across, or diagonally) is the winner.\r\n"
				+ "\r\n"
				+ "4. When all 9 squares are full, the game is over. If no player has 3 marks in a row, the game ends in a tie.");
	}

	private void setTable(LinkedHashMap<Integer, String> table) {
		for (int i = 1; i < 10; i++) {
			table.put(i, Integer.toString(i));
		}
	}

	private void displayTable(LinkedHashMap<Integer, String> table) {
		int counter = 1;
		for (int j = 0; j < 3; j++) {
			System.out.printf("\n %s | %s | %s \n", table.get(counter++), table.get(counter++), table.get(counter++));
			for (int i = 0; i < 13; i++) {
				if (j == 2)
					break;
				System.out.print("-");
			}
		}
	}

	public boolean gameActive(Object... objects) {
		@SuppressWarnings("unchecked")
		LinkedHashMap<Integer, String> table = (LinkedHashMap<Integer, String>) objects[0]; // TODO: Convert to 2d
																							// array?
		Player playerOne = (Player) objects[1];
		Player playerTwo = (Player) objects[2];
		if (!table.values().toString().matches(".*\\d+.*")) { // checks table for any digits to see if it's full.
			System.out.println("There are no spaces left.");
			super.winner = null;
			return false;
			/*
			 * Update to be more succinct
			 */

		} else if ((table.get(3).equalsIgnoreCase("X") && table.get(6).equalsIgnoreCase("X")
				&& table.get(9).equalsIgnoreCase("X"))
				|| (table.get(1).equalsIgnoreCase("X") && table.get(2).equalsIgnoreCase("X")
						&& table.get(3).equalsIgnoreCase("X"))
				|| (table.get(4).equalsIgnoreCase("X") && table.get(5).equalsIgnoreCase("X")
						&& table.get(6).equalsIgnoreCase("X"))
				|| (table.get(7).equalsIgnoreCase("X") && table.get(8).equalsIgnoreCase("X")
						&& table.get(9).equalsIgnoreCase("X"))
				|| (table.get(1).equalsIgnoreCase("X") && table.get(4).equalsIgnoreCase("X")
						&& table.get(7).equalsIgnoreCase("X"))
				|| (table.get(2).equalsIgnoreCase("X") && table.get(5).equalsIgnoreCase("X")
						&& table.get(8).equalsIgnoreCase("X"))
				|| (table.get(1).equalsIgnoreCase("X") && table.get(5).equalsIgnoreCase("X")
						&& table.get(9).equalsIgnoreCase("X"))
				|| (table.get(3).equalsIgnoreCase("X") && table.get(5).equalsIgnoreCase("X")
						&& table.get(7).equalsIgnoreCase("X"))) {
			super.winner = playerOne;
			return false;
		} else if ((table.get(3).equalsIgnoreCase("O") && table.get(6).equalsIgnoreCase("O")
				&& table.get(9).equalsIgnoreCase("O"))
				|| (table.get(1).equalsIgnoreCase("O") && table.get(2).equalsIgnoreCase("O")
						&& table.get(3).equalsIgnoreCase("O"))
				|| (table.get(4).equalsIgnoreCase("O") && table.get(5).equalsIgnoreCase("O")
						&& table.get(6).equalsIgnoreCase("O"))
				|| (table.get(7).equalsIgnoreCase("O") && table.get(8).equalsIgnoreCase("O")
						&& table.get(9).equalsIgnoreCase("O"))
				|| (table.get(1).equalsIgnoreCase("O") && table.get(4).equalsIgnoreCase("O")
						&& table.get(7).equalsIgnoreCase("O"))
				|| (table.get(2).equalsIgnoreCase("O") && table.get(5).equalsIgnoreCase("O")
						&& table.get(8).equalsIgnoreCase("O"))
				|| (table.get(1).equalsIgnoreCase("O") && table.get(5).equalsIgnoreCase("O")
						&& table.get(9).equalsIgnoreCase("O"))
				|| (table.get(3).equalsIgnoreCase("O") && table.get(5).equalsIgnoreCase("O")
						&& table.get(7).equalsIgnoreCase("O"))) {
			super.winner = playerTwo;
			return false;
		}
		return true;
	}
}
