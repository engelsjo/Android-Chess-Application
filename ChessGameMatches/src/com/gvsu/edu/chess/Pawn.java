package com.gvsu.edu.chess;

/**
 * This class contains different information about the ChessPiece Pawn,
 * including the name of the piece, the player that owns it, and the moves this
 * piece can legally make.
 * 
 * @author Joshua Engelsma
 * @version March 18, 2013
 * 
 */
public class Pawn extends ChessPiece {
	/**
	 * The constructor for the pawn, which calls the constructor of the parent
	 * class setting the owner of the player to either black or white.
	 * 
	 * @param player
	 *            the owner either black or white of this pawn piece.
	 */
	protected Pawn(Player player) {
		super(player);
	}

	/**
	 * The method that tells the user which type of chess piece this object is.
	 * 
	 * @return Pawn the string name of the chess piece.
	 */
	public String type() {
		return "Pawn";
	}

	/**
	 * This method determines how the Pawn can move. If the piece is moving
	 * diagonally into an opponent, or up 2 from the first row or up one
	 * anywhere else , and the path is clear, then the Pawn can move.
	 * 
	 * @param move
	 *            The move the Pawn is trying to make.
	 * @param board
	 *            The board containing all the data for the chess game.
	 * @return true if and only if the conditions pertaining to that chess piece
	 *         are met.
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean check = super.isValidMove(move, board);
		if (isClearPath(move, board) == false) {
			return false;
		} else if (board[move.fromRow][move.fromColumn] == null) {
			return false;
		} else if (board[move.fromRow][move.fromColumn].player() == Player.BLACK) {
			if (board[move.toRow][move.toColumn] == null) {
				if (move.toRow == move.fromRow - 1
						&& move.toColumn == move.fromColumn && check == true
						|| move.toRow == move.fromRow - 2
						&& move.toColumn == move.fromColumn && check == true
						&& move.fromRow == 6) {
					return true;
				} else {
					return false;
				}
			} else {
				if (move.toRow == move.fromRow - 1
						&& move.toColumn == move.fromColumn - 1
						&& check == true || move.toRow == move.fromRow - 1
						&& move.toColumn == move.fromColumn + 1
						&& check == true) {
					return true;
				} else {
					return false;
				}
			}
		} else {
			if (board[move.toRow][move.toColumn] == null) {
				if (move.toRow == move.fromRow + 1
						&& move.toColumn == move.fromColumn && check == true
						|| move.toRow == move.fromRow + 2
						&& move.toColumn == move.fromColumn && check == true
						&& move.fromRow == 1) {
					return true;
				} else {
					return false;
				}
			} else {
				if (move.toRow == move.fromRow + 1
						&& move.toColumn == move.fromColumn - 1
						&& check == true || move.toRow == move.fromRow + 1
						&& move.toColumn == move.fromColumn + 1
						&& check == true) {
					return true;
				} else {
					return false;
				}
			}
		}
	}

}
