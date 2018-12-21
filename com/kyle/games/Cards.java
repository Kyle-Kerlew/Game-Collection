package com.kyle.games;

import java.util.Random;
import java.util.ArrayList;

/**
 * A standard deck contains 52 cards.
 */
public class Cards {

	private static final String[] SUITS = { "Hearts", "Diamonds", "Spades", "Clubs" };
	private static final String[][] STANDARD_DECK = { { "2 of ", "2" }, { "3 of ", "3" }, { "4 of ", "4" },
			{ "5 of ", "5" }, { "6 of ", "6" }, { "7 of ", "7" }, { "8 of ", "8" }, { "9 of ", "9" }, { "10 of ", "10" },
			{ "Jack of ", "11" }, { "Queen of ", "12" }, { "King of ", "13" }, { "Ace of ", "14" } };

	protected static void createDeck(ArrayList<String> deck) {
		for (int i = 0; i < SUITS.length; i++) {
			for (int j = 0; j < STANDARD_DECK.length; j++) {
				deck.add(STANDARD_DECK[j][0].concat(SUITS[i]));
			}
		}
	}

	/**
	 * This method fills the players hands with cards in a random order.
	 * 
	 * @param numOfCards - how many cards are being drawn
	 * @param deck       - the deck the cards are being pulled from
	 * @param hand       - Whose hand are these cards being dealt to
	 */
	protected static void drawCard(int numOfCards, ArrayList<String> deck, ArrayList<String> hand) {
		Random rand = new Random();
		int n1, n2, counter = 0;
		while (counter < numOfCards) {
			n1 = rand.nextInt(4);
			n2 = rand.nextInt(13);
			if (!hand.contains(STANDARD_DECK[n2][0].concat(SUITS[n1]))
					&& deck.contains(STANDARD_DECK[n2][0].concat(SUITS[n1]))) {
				deck.remove(STANDARD_DECK[n2][0].concat(SUITS[n1]));
				hand.add(STANDARD_DECK[n2][0].concat(SUITS[n1]));
				counter++;
			}
		}
	}

	/**
	 * 
	 * @param card
	 * @returns value of the card by parsing the string of it has its value in the
	 *          card name e.g. "1 of hearts" value is 1. and checks and returns
	 *          values for jack, queen, king, and ace.
	 */
	protected static int getValue(String card) {
		int value = 0;
		if (Character.isDigit(card.charAt(0))) {
			value = Integer.parseInt(Character.toString(card.charAt(0)));
		} else if (Character.isAlphabetic(card.charAt(0))) {
			switch (card.charAt(0)) {
			case 'J':
				value = 10;
				break;
			case 'Q':
				value = 11;
				break;
			case 'K':
				value = 12;
				break;
			case 'A':
				value = 13;
				break;
			default:
				System.out.println("Invalid.");
				break;
			}
		}
		return value;
	}
}