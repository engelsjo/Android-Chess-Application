package com.gvsu.edu.chess;

/**
 * A player of a chess game.
 * 
 * @author Zachary Kurmas
 */
public enum Player {
	BLACK, WHITE;

	/**
	 * Return the {@code Player} whose turn is next.
	 * 
	 * @return the {@code Player} whose turn is next
	 */
	public Player next() {
		return this == BLACK ? WHITE : BLACK;
	}
}
