package com.gvsu.edu.chess;

/**
 * This class contains different information about the ChessPiece Bishop,
 * including the name of the piece, the player that owns it, and the moves this
 * piece can legally make.
 * 
 * @author Joshua Engelsma
 * @version March 18, 2013
 * 
 */
public class Bishop extends ChessPiece {
	/**
	 * The constructor for the bishop, which calls the constructor of the parent
	 * class setting the owner of the player to either black or white.
	 * 
	 * @param player
	 *            the owner either black or white of this bishop piece.
	 */
	protected Bishop(Player player) {
		super(player);
	}

	/**
	 * The method that tells the user which type of chess piece this object is.
	 * 
	 * @return Bishop the string name of the chess piece.
	 */
	public String type() {
		return "Bishop";
	}

	/**
	 * This method determines how the Bishop can move. If the piece is moving
	 * diagonally, and the path is clear, Then the Bishop can move.
	 * 
	 * @param move
	 *            The move the Bishop is trying to make.
	 * @param board
	 *            The board containing all the data for the chess game.
	 * @return true if and only if the conditions pertaining to that chess piece
	 *         are met.
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		return super.isValidMove(move, board) && isClearPath(move, board)
				&& isDiagonal(move);

	}

}
