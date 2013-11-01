package com.gvsu.edu.chess;

/**
 * Describes a piece for a game of chess (King, Queen, Rook, etc.)
 * 
 * @author Zachary Kurmas
 */
// Created 12/3/12 at 3:29 PM
// (C) Zachary Kurmas 2012

public interface IChessPiece {

	/**
	 * Return the player that owns this piece.
	 * 
	 * @return the player that owns this piece.
	 */
	Player player();

	/**
	 * Return the type of this piece ("King", "Queen", "Rook", etc.). Note: In
	 * this case "type" refers to the game of chess, not the type of the Java
	 * class.
	 * 
	 * @return the type of this piece
	 */
	String type();

	/**
	 * Returns whether the piece at location
	 * {@code [move.fromRow, move.fromColumn]} is allowed to move to location
	 * {@code [move.fromRow, move.fromColumn]}.
	 * 
	 * Note: Pieces don't store their own location (because doing so would be
	 * redundant). Therefore, the {@code [move.fromRow, move.fromColumn]}
	 * component of {@code move} is necessary. {@code this} object must be the
	 * piece at location {@code [move.fromRow, move.fromColumn]}. (This method
	 * makes no sense otherwise.)
	 * 
	 * @param move
	 *            a {@link Move} object describing the move to be made.
	 * @param board
	 *            the {@link ChessBoard} in which this piece resides.
	 * @return {@code true} if the proposed move is valid, {@code false}
	 *         otherwise.
	 * @throws IndexOutOfBoundsException
	 *             if either {@code [move.fromRow, move.fromColumn]} or
	 *             {@code [move.toRow,
	 *                                   move.toColumn]} don't represent valid
	 *             locations on the board.
	 * @throws IllegalArgumentException
	 *             if {@code this} object isn't the piece at location
	 *             {@code [move.fromRow, move.fromColumn]}.
	 */
	boolean isValidMove(Move move, IChessPiece[][] board);
}
