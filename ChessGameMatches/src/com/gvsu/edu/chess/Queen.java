package com.gvsu.edu.chess;

/**
 * This class contains different information about the ChessPiece Queen,
 * including the name of the piece, the player that owns it, and the moves this
 * piece can legally make.
 * 
 * @author Joshua Engelsma
 * @version March 18, 2013
 * 
 */
public class Queen extends ChessPiece {
	/**
	 * The constructor for the queen, which calls the constructor of the parent
	 * class setting the owner of the player to either black or white.
	 * 
	 * @param player
	 *            the owner either black or white of this queen piece.
	 */
	protected Queen(Player player) {
		super(player);
	}

	/**
	 * The method that tells the user which type of chess piece this object is.
	 * 
	 * @return Queen the string name of the chess piece.
	 */
	public String type() {
		return "Queen";
	}

	/**
	 * This method determines how the Queen can move. If the piece is moving
	 * diagonally or laterally any amount of squares, and the path is clear,
	 * then the Queen can move.
	 * 
	 * @param move
	 *            The move the Queen is trying to make.
	 * @param board
	 *            The board containing all the data for the chess game.
	 * @return true if and only if the conditions pertaining to that chess piece
	 *         are met.
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		return super.isValidMove(move, board)
				&& (isDiagonal(move) || isStraight(move))
				&& isClearPath(move, board);
	}

}
