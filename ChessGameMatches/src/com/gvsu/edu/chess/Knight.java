package com.gvsu.edu.chess;

/**
 * This class contains different information about the ChessPiece Knight,
 * including the name of the piece, the player that owns it, and the moves this
 * piece can legally make.
 * 
 * @author Joshua Engelsma
 * @version March 18, 2013
 * 
 */
public class Knight extends ChessPiece {
	/**
	 * The constructor for the knight, which calls the constructor of the parent
	 * class setting the owner of the player to either black or white.
	 * 
	 * @param player
	 *            the owner either black or white of this pawn piece.
	 */
	protected Knight(Player player) {
		super(player);
	}

	/**
	 * The method that tells the user which type of chess piece this object is.
	 * 
	 * @return Knight the string name of the chess piece.
	 */
	public String type() {
		return "Knight";
	}

	/**
	 * This method determines how the Knight can move. If the piece is moving in
	 * a 3 spare "L" shape, and the path is clear, Then the Knight can move.
	 * 
	 * @param move
	 *            The move the Knight is trying to make.
	 * @param board
	 *            The board containing all the data for the chess game.
	 * @return true if and only if the conditions pertaining to that chess piece
	 *         are met.
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean check = super.isValidMove(move, board);
		if (move.toRow == move.fromRow + 2 || move.toRow == move.fromRow - 2) {
			if (move.toColumn == move.fromColumn + 1 && check == true
					|| move.toColumn == move.fromColumn - 1 && check == true) {
				return true;
			} else {
				return false;
			}
		} else if (move.toRow == move.fromRow + 1
				|| move.toRow == move.fromRow - 1) {
			if (move.toColumn == move.fromColumn + 2 && check == true
					|| move.toColumn == move.fromColumn - 2 && check == true) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
