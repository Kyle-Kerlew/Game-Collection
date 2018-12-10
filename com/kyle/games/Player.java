package com.kyle.games;

import java.util.Scanner;

public class Player {

	protected static Player winner; 
	private String username;
	

	protected Player(String username) {
		this.username = username;
	}

	protected String getUsername() {
		return username;
	}

	protected void setUsername() {
		Scanner sc = new Scanner(System.in);
		System.out.println("What would you like your username to be?");
		this.username = sc.nextLine();
		sc.close();
	}

	/**
	 * TODO: Move player deck to this class for encapsulation
	 */

}
