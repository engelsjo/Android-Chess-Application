package com.gvsu.edu.chess;

/**
 * This class holds all the information to the game as it runs. Simply put, this
 * is the controller to the game. It stores the data as it were and is updated
 * as the user interacts with the GUI.
 * 
 * @author Joshua Engelsma
 * @version March 18, 2013
 */
public class ChessModel implements IChessModel {
	//instance variables
	private IChessPiece[][] board;
	private Player player;
	private int bKingRow;
	private int bKingCol;
	private int wKingRow;
	private int wKingCol;
	private int tWKingCol;
	private int tWKingRow;
	private int tBKingCol;
	private int tBKingRow;
	private int wKingCount;
	private int bKingCount;
	private int tBKing;
	private int tWKing;
	private IChessPiece[][] testBoard;

	/**
	 * This is the constructor to the model. This method sets up all the data
	 * for a chess game such as player turn and starting positions as they
	 * should be at the start of a game.
	 * 
	 */
	public ChessModel() {
		player = Player.BLACK;
		startGame();
	}

	/**
	 * This method determines whether or not a player who is in check is able to
	 * move out of check. If that player cannot move anywhere without being in
	 * check, the game is over.
	 * 
	 * @return true if and only if a player is in check, and is not able to move
	 *         the king any other place without remaining in check.
	 */
	public boolean isComplete() {
		testBoard();
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				testBoard();
				IChessPiece piece = testBoard[row][col];
				for (int r = 0; r < 8; r++) {
					for (int c = 0; c < 8; c++) {
						testBoard();
						if (testBoard[row][col] == null) {

						} else if (piece.isValidMove(new Move(row, col, r, c),
								testBoard) == true && piece.player() == player) {
							testBoard[r][c] = testBoard[row][col];
							testBoard[row][col] = null;
							if (testCheck() == false) {
								return false;
							}
						}
					}
				}
			}
		}
		return true;

	}

	/**
	 * This method looks at the specific piece that you are moving from, and
	 * determines if it is legal for you to make a move to the place the user
	 * requests.
	 * 
	 * @param move
	 *            The object holding the coordinates where you will be moving
	 *            from, and where you will be moving too.
	 * @return true if and only if the the move as specified by each piece class
	 *         is a valid move, else false.
	 */
	public boolean isValidMove(Move move) {
		if (pieceAt(move.fromRow, move.fromColumn) == null
				|| pieceAt(move.fromRow, move.fromColumn).player() != player) {
			return false;
		} else {
			boolean check = pieceAt(move.fromRow, move.fromColumn).isValidMove(
					move, board);
			return check;
		}
	}

	/**
	 * This method will move the object that the user desires to be moved to the
	 * correct place on the 2-D array of pieces, and set the previous location
	 * of that piece to null only if the move is valid.
	 * 
	 * @param move
	 *            The object holding the coordinates where you will be moving
	 *            from, and where you will be moving too.
	 * 
	 */
	public void move(Move move) {

		if (isValidMove(move) == true) {
			board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];
			board[move.fromRow][move.fromColumn] = null;
			player = player.next();
		} else {
			board[move.toRow][move.toColumn] = board[move.toRow][move.toColumn];
			board[move.fromRow][move.fromColumn] = board[move.fromRow][move.fromColumn];

		}
	}

	/**
	 * This method navigates the 2-D array and returns true if any of the
	 * opposing players can currently threaten the opposing king. If they can, a
	 * king is in check, and the method results in true.
	 * 
	 * @return true if and only if a kings opposing players have a valid path to
	 *         him otherwise false.
	 */

	public boolean inCheck() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (board[row][col] == null) {

				} else if (board[row][col].type().equals("King")
						&& board[row][col].player() == Player.BLACK) {
					bKingRow = row;
					bKingCol = col;
				} else if (board[row][col].type().equals("King")
						&& board[row][col].player() == Player.WHITE) {
					wKingRow = row;
					wKingCol = col;
				} else {

				}
			}
		}

		bKingCount = 0;
		wKingCount = 0;
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				Move bCheckMove = new Move(row, col, bKingRow, bKingCol);
				Move wCheckMove = new Move(row, col, wKingRow, wKingCol);
				if (board[row][col] == null) {

				} else if (pieceAt(row, col).isValidMove(bCheckMove, board) == true) {
					bKingCount++;
					return true;
				} else if (pieceAt(row, col).isValidMove(wCheckMove, board) == true) {
					wKingCount++;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * returns the player whose turn it currently is.
	 * 
	 * @return player The enum type of the current player, either BLACK or
	 *         WHITE.
	 */

	public Player currentPlayer() {
		return player;
	}

	/**
	 * Method returns which piece is at a certain index in the 2-D array of
	 * IChessPieces.
	 * 
	 * @param row
	 *            the row of the piece you are returning
	 * @param col
	 *            the column of the piece that you are returning.
	 * @return the IChessPiece located in the 2-D array of IChessPieces at
	 *         location row, col.
	 */

	public IChessPiece pieceAt(int row, int column) {
		return board[row][column];
	}

	/**
	 * This is a Helper method that is used by the constructor to initialize the
	 * 2-D array of IChessPieces with the correct pieces at each index as they
	 * would be at the start of a game of chess.
	 * 
	 * @return board The 2-D array of IChessPieces with the different Chess
	 *         pieces located as they should be at the start of every chess
	 *         game.
	 */

	public IChessPiece[][] startGame() {
		board = new IChessPiece[8][8];
		board[0][0] = new Rook(Player.WHITE);
		board[0][7] = new Rook(Player.WHITE);
		board[0][1] = new Knight(Player.WHITE);
		board[0][6] = new Knight(Player.WHITE);
		board[0][2] = new Bishop(Player.WHITE);
		board[0][5] = new Bishop(Player.WHITE);
		board[0][3] = new Queen(Player.WHITE);
		board[0][4] = new King(Player.WHITE);
		// black buttons added...
		board[7][0] = new Rook(Player.BLACK);
		board[7][7] = new Rook(Player.BLACK);
		board[7][1] = new Knight(Player.BLACK);
		board[7][6] = new Knight(Player.BLACK);
		board[7][2] = new Bishop(Player.BLACK);
		board[7][5] = new Bishop(Player.BLACK);
		board[7][3] = new Queen(Player.BLACK);
		board[7][4] = new King(Player.BLACK);
		// add pawns/null...
		for (int row = 2; row < 6; row++) {
			for (int col = 0; col < 8; col++) {
				board[row][col] = null;
			}
		}
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (row == 1 || row == 6) {
					if (row == 1) {
						board[row][col] = new Pawn(Player.WHITE);
					} else {
						board[row][col] = new Pawn(Player.BLACK);
					}
				}
			}
		}
		return board;
	}

	/**
	 * This method is a helper method for the isComplete() method. It sets up a
	 * virtual board and allows a virtual move or every possible move to be made
	 * every time. The board in this method copies the data from the 2-D array
	 * that is updated as the user interacts with the GUI.
	 * 
	 * @return testBoard the board with the data copied over exactly from the
	 *         2-D array board that is updated as the user interacts with the
	 *         GUI.
	 */
	public IChessPiece[][] testBoard() {
		testBoard = new IChessPiece[8][8];
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				IChessPiece piece = board[row][col];
				if (piece == null) {
					testBoard[row][col] = null;
				} else if (piece.type().equals("Pawn")) {
					if (piece.player() == Player.WHITE) {
						testBoard[row][col] = new Pawn(Player.WHITE);
					} else {
						testBoard[row][col] = new Pawn(Player.BLACK);
					}
				} else if (piece.type().equals("Rook")) {
					if (piece.player() == Player.WHITE) {
						testBoard[row][col] = new Rook(Player.WHITE);
					} else {
						testBoard[row][col] = new Rook(Player.BLACK);
					}
				} else if (piece.type().equals("Bishop")) {
					if (piece.player() == Player.WHITE) {
						testBoard[row][col] = new Bishop(Player.WHITE);
					} else {
						testBoard[row][col] = new Bishop(Player.BLACK);
					}
				} else if (piece.type().equals("Knight")) {
					if (piece.player() == Player.WHITE) {
						testBoard[row][col] = new Knight(Player.WHITE);
					} else {
						testBoard[row][col] = new Knight(Player.BLACK);
					}
				} else if (piece.type().equals("King")) {
					if (piece.player() == Player.WHITE) {
						testBoard[row][col] = new King(Player.WHITE);
					} else {
						testBoard[row][col] = new King(Player.BLACK);
					}
				} else if (piece.type().equals("Queen")) {
					if (piece.player() == Player.WHITE) {
						testBoard[row][col] = new Queen(Player.WHITE);
					} else {
						testBoard[row][col] = new Queen(Player.BLACK);
					}
				}
			}
		}
		return testBoard;
	}

	/**
	 * This check method checks the virtual board set up in the testBoard
	 * method. If a potential move puts a king in check, then true is returned.
	 * This is used to determine is a King is in check-mate, because all
	 * potential moves must return false, otherwise the king is in check-mate.
	 * 
	 * @return true if and only if a potential move puts a king in check.
	 */
	public boolean testCheck() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (testBoard[row][col] == null) {

				} else if (testBoard[row][col].type().equals("King")
						&& testBoard[row][col].player() == Player.BLACK) {
					tBKingRow = row;
					tBKingCol = col;
				} else if (testBoard[row][col].type().equals("King")
						&& testBoard[row][col].player() == Player.WHITE) {
					tWKingRow = row;
					tWKingCol = col;
				} else {

				}
			}
		}
		tBKing = 0;
		tWKing = 0;
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				Move bCheckMove = new Move(row, col, tBKingRow, tBKingCol);
				Move wCheckMove = new Move(row, col, tWKingRow, tWKingCol);
				if (board[row][col] == null) {

				} else if (pieceAt(row, col).isValidMove(bCheckMove, testBoard) == true) {
					tBKing++;
					return true;
				} else if (pieceAt(row, col).isValidMove(wCheckMove, testBoard) == true) {
					tWKing++;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * returns an integer which helps determine which king is in check.
	 * 
	 * @return bKingCount the integer which indicates which king is in check.
	 */
	public int getbCheck() {
		return bKingCount;
	}

	/**
	 * returns an integer which helps determine which king is in check.
	 * 
	 * @return wKingCount the integer which indicates which king is in check.
	 */
	public int getwCheck() {
		return wKingCount;
	}

	/**
	 * Method which will determine if a player is moving into check. Because in
	 * chess a player cannot move into check, this method will force the player
	 * to make a move that will not result in check by using the virtual board
	 * set up, and checking a possible move.
	 * 
	 * @param move
	 *            the potential move that needs to be checked to determine if
	 *            the move results in a player being in check.
	 * @return false if and only if a player is moving themselves into check.
	 */
	public boolean blockCheckMove(Move move) {
		testBoard();
		if (testBoard[move.fromRow][move.fromColumn] == null) {

		} else if (testBoard[move.fromRow][move.fromColumn].player() == Player.WHITE) {
			testBoard[move.toRow][move.toColumn] = testBoard[move.fromRow][move.fromColumn];
			testBoard[move.fromRow][move.fromColumn] = null;
			if (testCheck() == true && tWKing == 1) {
				return false;
			}
		} else {
			testBoard[move.toRow][move.toColumn] = testBoard[move.fromRow][move.fromColumn];
			testBoard[move.fromRow][move.fromColumn] = null;
			if (testCheck() == true && tBKing == 1) {
				return false;
			}
		}
		return true;

	}

}
