package com.kyle.games;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author Kyle Kerlew This class is meant to handle War and all of its related
 *         functions
 * 
 *
 */

public class War extends Games {

	/**
	 * When an instance of War is created, the game is initialized.
	 */

	public War() {
		super("War");
		initializeGame();
	}

	/**
	 * Game Controller for War Inherits @Params playermode and playerName from
	 * superclass Games Player mode 1 automatically names the other player
	 * "computer" Player mode 2 Allows the user to give the computer a name to
	 * simulate playing with another player . Makes use of ArrayLists to have
	 * precise control of indexes
	 */
	@Override
	protected void gameController(int playerMode, String playerOneUser, String playerTwoUser) {

		ArrayList<String> deck = new ArrayList<>();
		ArrayList<String> playerTwoHand = new ArrayList<>();
		ArrayList<String> playerOneHand = new ArrayList<>();

		Cards.createDeck(deck);
		Cards.drawCard(52 / 2, deck, playerTwoHand);
		Cards.drawCard(52 / 2, deck, playerOneHand);

		Player playerOne = new Player(playerOneUser);
		Player playerTwo = new Player(playerTwoUser);

		while (gameActive(playerTwoHand, playerOneHand)) {
			System.out.printf("%s puts down a card...\nit's a(n) %s\n", playerTwo.getUsername(), playerTwoHand.get(0));
			pause(1500);
			System.out.printf("%s puts down a card...\nit's a(n) %s\n", playerOne.getUsername(), playerOneHand.get(0));
			pause(1500);
			if (Cards.getValue(playerTwoHand.get(0)) > Cards.getValue(playerOneHand.get(0))) {
				System.out.println(playerTwo.getUsername() + " wins this round.");
				handleWin(playerTwoHand, playerOneHand, false);
			} else if (Cards.getValue(playerTwoHand.get(0)) < Cards.getValue(playerOneHand.get(0))) {
				System.out.println(playerOne.getUsername() + " wins this round.");
				handleWin(playerOneHand, playerTwoHand, false);
			} else if (Cards.getValue(playerTwoHand.get(0)) == Cards.getValue(playerOneHand.get(0))) {
				if (playerTwoHand.size() < 3 || playerOneHand.size() < 3) {
					Collections.shuffle(playerTwoHand);
					Collections.shuffle(playerOneHand);
					System.out.println("Not enough cards for war ... shuffling both decks.");
				} else {
					ArrayList<String> table = new ArrayList<>();
					boolean war = true;
					System.out.println("War!");

					table.add(playerOneHand.get(0)); // original card that initiated war. "Facing up".
					playerOneHand.remove(0);

					table.add(playerTwoHand.get(0)); // original card that initiated war. "Facing up".
					playerTwoHand.remove(0);

					while (war) {
						pause(1500);
						table.add(playerOneHand.get(0)); // unknown value Face down card
						playerOneHand.remove(0);

						table.add(playerTwoHand.get(0));// unknown value Face down card
						playerTwoHand.remove(0);

						table.add(playerOneHand.get(0)); // face up card
						playerOneHand.remove(0);

						table.add(playerTwoHand.get(0)); // face up card
						playerTwoHand.remove(0);

						System.out.printf(
								"Each player places one card face down and one face up...\r the face up cards are %s and %s ",
								table.get(table.size() - 1), table.get(table.size() - 2));
						pause(1500);
						if (Cards.getValue(table.get(table.size() - 1)) > Cards.getValue(table.get(table.size() - 2))) {
							System.out.printf("%s won this round, %s loses both their \n", playerTwo.getUsername(),
									playerOne.getUsername());
							handleWin(playerTwoHand, table, true);
							war = false;
						} else if (Cards.getValue(table.get(table.size() - 1)) < Cards
								.getValue(table.get(table.size() - 2))) {
							System.out.printf("%s won this round, %s loses both their \n", playerOne.getUsername(),
									playerTwo.getUsername());
							handleWin(playerOneHand, table, true);
							war = false;
						} else if (Cards.getValue(table.get(table.size() - 1)) == Cards
								.getValue(table.get(table.size() - 2))) {
							System.out.println("The war continues!");
							war = true;
						}
					}
				}
			}
			System.out.printf("Cards left\n------------ \n%s: %d\n%s: %d\n", playerOne.getUsername(),
					playerOneHand.size(), playerTwo.getUsername(), playerTwoHand.size());
		}
		if (playerOneHand.isEmpty()) {
			Player.winner = playerTwo;
			playerTwo.addWin();
			playerOne.addLoss();
		} else if (playerTwoHand.isEmpty()) {
			Player.winner = playerOne;
			playerOne.addWin();
			playerTwo.addLoss();
		}
		endGame();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected boolean gameActive(Object... hands) {
		ArrayList<String> hand1 = ((ArrayList<String>) hands[0]);
		ArrayList<String> hand2 = ((ArrayList<String>) hands[1]);
		return !hand1.isEmpty() && !hand2.isEmpty() ? true : false;
	}

	@Override
	protected void howToPlay() {
		System.out.println("The goal is to be the first player to win all 52 cards\r\n" + "\r\n" + "THE DEAL\r\n"
				+ "The deck is divided evenly, with each player receiving 26 cards, dealt one at a time, face down. Anyone may deal first. Each player places his stack of cards face down, in front of him.\r\n"
				+ "\r\n" + "THE PLAY\r\n"
				+ "Each player turns up a card at the same time and the player with the higher card takes both cards and puts them, face down, on the bottom of his stack.\r\n"
				+ "\r\n"
				+ "If the cards are the same rank, it is War. Each player turns up one card face down and one card face up. The player with the higher cards takes both piles (six cards). If the turned-up cards are again the same rank, each player places another card face down and turns another card face up. The player with the higher card takes all 10 cards, and so on.\r\n"
				+ "\r\n" + "HOW TO KEEP SCORE\r\n" + "The game ends when one player has won all the ");
	}

	private void handleWin(ArrayList<String> winningHand, ArrayList<String> losingHand, boolean isWar) {
		if (!isWar) {
			winningHand.add(winningHand.size(), winningHand.get(0));
			winningHand.remove(winningHand.get(0));
			winningHand.add(winningHand.size(), losingHand.get(0));
			losingHand.remove(losingHand.get(0));
		} else {
			winningHand.addAll(losingHand); // losingHand represents the table, in this case.
			losingHand.clear();
		}
	}
}
