package com.gvsu.edu.chess;

/**
 * Packages the four components of a move into a single object. (Instance
 * variables are public because this object is a simple container.)
 * 
 * @author Zachary Kurmas
 */
// Created 12/3/12 at 2:11 PM
// (C) Zachary Kurmas 2012

public class Move {

	public int fromRow, fromColumn, toRow, toColumn;

	/**
	 * Constructor for the move object, which instantiates all of the four
	 * components of a move.
	 * 
	 * @param fromRow
	 *            the row number a piece is sitting at.
	 * @param fromColumn
	 *            the column number a piece is sitting at.
	 * @param toRow
	 *            the row number a piece is moving too.
	 * @param toColumn
	 *            the column number a piece is moving too.
	 */
	public Move(int fromRow, int fromColumn, int toRow, int toColumn) {
		this.fromRow = fromRow;
		this.fromColumn = fromColumn;
		this.toRow = toRow;
		this.toColumn = toColumn;
	}
}
