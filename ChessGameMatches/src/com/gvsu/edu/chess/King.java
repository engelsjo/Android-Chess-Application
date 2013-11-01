package com.gvsu.edu.chess;

/**
 * This class contains different information about the ChessPiece King,
 * including the name of the piece, the player that owns it, and the moves this
 * piece can legally make.
 * 
 * @author Joshua Engelsma
 * @version March 18, 2013
 * 
 */
public class King extends ChessPiece {
	/**
	 * The constructor for the king, which calls the constructor of the parent
	 * class setting the owner of the player to either black or white.
	 * 
	 * @param player
	 *            the owner either black or white of this king piece.
	 */
	protected King(Player player) {
		super(player);
	}

	/**
	 * The method that tells the user which type of chess piece this object is.
	 * 
	 * @return King the string name of the chess piece.
	 */
	public String type() {
		return "King";
	}

	/**
	 * This method determines how the King can move. If the piece is moving
	 * diagonally or laterally one spot, and the path is clear, then the King
	 * can move.
	 * 
	 * @param move
	 *            The move the King is trying to make.
	 * @param board
	 *            The board containing all the data for the chess game.
	 * @return true if and only if the conditions pertaining to that chess piece
	 *         are met.
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean check = super.isValidMove(move, board);
		if (isClearPath(move, board) == false) {
			return false;
		} else if (move.toRow == move.fromRow + 1
				&& move.toColumn == move.fromColumn
				|| move.toRow == move.fromRow - 1
				&& move.toColumn == move.fromColumn) {
			if (check == true) {
				return true;
			} else {
				return false;
			}
		} else if (move.toColumn == move.fromColumn + 1
				&& move.toRow == move.fromRow
				|| move.toColumn == move.fromColumn - 1
				&& move.toRow == move.fromRow) {
			if (check == true) {
				return true;
			} else {
				return false;
			}
		} else if (Math.abs(move.toRow - move.fromRow) == 1
				&& Math.abs(move.toColumn - move.fromColumn) == 1) {
			if (check == true) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
