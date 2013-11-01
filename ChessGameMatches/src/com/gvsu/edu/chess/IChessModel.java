package com.gvsu.edu.chess;

/**
 * Objects implementing this interface represent the state of a chess game.
 * Notice that this interface is designed to maintain the game state only, it
 * does not provide any methods to control the flow of the game.
 * 
 * @author Zachary Kurmas
 */
// Created 12/3/12 at 3:15 PM
// (C) Zachary Kurmas 2012

public interface IChessModel {

	/**
	 * Returns whether the game is complete.
	 * 
	 * @return {@code true} if complete, {@code false} otherwise.
	 */
	boolean isComplete();

	/**
	 * Returns whether the piece at location
	 * {@code [move.fromRow, move.fromColumn]} is allowed to move to location
	 * {@code [move.fromRow, move.fromColumn]}.
	 * 
	 * @param move
	 *            a {@link Move} object describing the move to be made.
	 * @return {@code true} if the proposed move is valid, {@code false}
	 *         otherwise.
	 * @throws IndexOutOfBoundsException
	 *             if either {@code [move.fromRow, move.fromColumn]} or
	 *             {@code [move.toRow,
	 *                                   move.toColumn]} don't represent valid
	 *             locations on the board.
	 */
	boolean isValidMove(Move move);

	/**
	 * Moves the piece from location {@code [move.fromRow, move.fromColumn]} to
	 * location {@code [move.fromRow,
	 * move.fromColumn]}.
	 * 
	 * @param move
	 *            a {@link Move} object describing the move to be made.
	 * @throws IndexOutOfBoundsException
	 *             if either {@code [move.fromRow, move.fromColumn]} or
	 *             {@code [move.toRow,
	 *                                   move.toColumn]} don't represent valid
	 *             locations on the board.
	 */
	void move(Move move);

	/**
	 * Report whether the current player is in check.
	 * 
	 * @return {@code true} if the current player is in check, {@code false}
	 *         otherwise.
	 */
	boolean inCheck();

	/**
	 * Return the current player.
	 * 
	 * @return the current player
	 */
	Player currentPlayer();

	/**
	 * Return the {@code ChessPiece} object at location {@code [row, column]}.
	 * 
	 * @param row
	 *            the row (numbered {@code 0} through {@code numRows -1}
	 * @param column
	 *            the row (numbered {@code 0} through {@code numColumns -1}
	 * @return the {@code ChessPiece} object at location {@code [row, column]}.
	 * @throws IndexOutOfBoundsException
	 *             if {@code [row, column]} is not a valid location on the
	 *             board.
	 */
	IChessPiece pieceAt(int row, int column);

}
