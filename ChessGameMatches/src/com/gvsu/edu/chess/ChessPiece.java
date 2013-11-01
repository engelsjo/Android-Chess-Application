package com.gvsu.edu.chess;

/**
 * This class acts as the parent for all of the Chess Pieces. All of the chess
 * pieces have certain attributes that are the same. For instance, none of the
 * chess pieces can move out of bounds. The class manages data that is similar
 * for all chess pieces.
 * 
 * @author Joshua Engelsma
 * @version March 18, 2013
 * 
 */
public abstract class ChessPiece implements IChessPiece {

	private Player owner;

	/**
	 * This is the constructor. In this method, the ChessPiece is assigned an
	 * owner, either black or white.
	 * 
	 * @param player
	 *            the player of this piece, either black or white.
	 */
	protected ChessPiece(Player player) {
		this.owner = player;
	}

	/**
	 * Abstract method that will return a string containing the pieces name.
	 * 
	 * @return the string name of the piece.
	 */
	public abstract String type();

	/**
	 * Provides the player of this ChessPiece.
	 * 
	 * @return owner The owner either black or white of this ChessPiece.
	 */
	public Player player() {
		return owner;
	}

	/**
	 * This helper method determines if a move that is made is a diagonal
	 * movement by making sure that the difference between both row and column
	 * remains the same as you move.
	 * 
	 * @param move
	 *            the move that the player intends to make.
	 * @return true if and only if the move that is made is a diagonal move,
	 *         otherwise false is returned.
	 */
	public boolean isDiagonal(Move move) {
		if (move.toRow != move.fromRow && move.toColumn != move.fromColumn) {
			if (Math.abs(move.toRow - move.fromRow) == Math.abs(move.toColumn
					- move.fromColumn) == true) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * This helper method determines if a move that is made is a lateral
	 * movement by making sure that if the row is changing, the column stays the
	 * same, or vice versa.
	 * 
	 * @param move
	 *            the move that the player intends to make.
	 * @return true if and only if the move that is made is a lateral move,
	 *         otherwise false is returned.
	 */
	public boolean isStraight(Move move) {
		if (move.toRow == move.fromRow == true) {
			return true;
		} else if (move.toColumn == move.fromColumn == true) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This helper method determines if the path that the piece intends to
	 * travel is clear. In other words, no other obstacle or piece can stand in
	 * its path.
	 * 
	 * @param move
	 *            the move that the player intends to make.
	 * @param board
	 *            the board that contains the data for where all the chess
	 *            pieces are sitting.
	 * @return true if and only if all the spaces between you and where you are
	 *         moving too are empty.
	 */
	public boolean isClearPath(Move move, IChessPiece[][] board) {
		int rowIncrement = (move.fromRow < move.toRow) ? 1 : -1;
		int colIncrement = (move.fromColumn < move.toColumn) ? 1 : -1;

		int row = move.fromRow;
		int col = move.fromColumn;

		if (move.toRow == move.fromRow) {
			rowIncrement = 0;
		}
		if (move.toColumn == move.fromColumn) {
			colIncrement = 0;
		}

		while (true) {
			row += rowIncrement;
			col += colIncrement;

			if (row < 0 || col < 0 || row > 7 || col > 7) {
				return false;
			}
			if (row == move.toRow && col == move.toColumn) {
				return true;
			}
			if (board[row][col] != null) {
				return false;
			}
		}
	}

	/**
	 * Method contains all the valid parameters that pertain to all chess
	 * pieces, such as making sure that the piece doesn't try to move off the
	 * board or that you don't eat your own piece.
	 * 
	 * @return true if and only if the move desired is valid.
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		if (move.fromRow > 7 || move.toRow > 7 || move.fromColumn > 7
				|| move.toColumn > 7) {
			return false;
		} else if (move.fromRow < 0 || move.toRow < 0 || move.fromColumn < 0
				|| move.toColumn < 0) {
			return false;
		} else if (board[move.fromRow][move.fromColumn] == null) {
			return false;
		} else if (board[move.toRow][move.toColumn] == null) {
			return true;
		} else if (board[move.toRow][move.toColumn].player() == board[move.fromRow][move.fromColumn]
				.player()) {
			return false;
		} else {
			return true;
		}
	}
}
