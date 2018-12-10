package com.kyle.initialization;

import com.kyle.games.*;

import java.util.Scanner;

/**
 * Project task: Create a number of games to play all in one application. Game
 * 1: Card game. War. TODO: Implement VS computer and VS another player.
 * 
 * @author Kyle Kerlew
 * @version 1.0
 * @date 11/28
 * @projectPurpose to implement games
 */

public class Main {

	public static void main(String[] args) {
		initializeGameSystem();
	}

	public static void initializeGameSystem() {
		System.out.println("Hello, please select a game below by typing the corresponding number: ");
		System.out.printf("%d War\t\t  %d Tic Tac Toe\t\t  %d More coming\t\t", 1, 2, 3);
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			new War();
			sc.close();
			break;
		case 2:
			new TicTacToe();
			sc.close();
			break;
		default:
			System.out.println("Invalid game choice.");
			sc.close();
			break;
		}
	}
}
