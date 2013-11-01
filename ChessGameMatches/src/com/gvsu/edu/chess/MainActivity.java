package com.gvsu.edu.chess;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

	private Button[][] board;
	private ChessModel model;
	private int fromRow, fromCol;
	private static int count = 0;
	private Move move;
	private TextView gameStatus;
	private Button menu;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		startGame();
	}

	private void startGame() {
		//set up game status
		gameStatus = new TextView(this);
		gameStatus.setText("Welcome to Chess! Black's Turn");
		gameStatus.setTypeface(null, Typeface.BOLD_ITALIC);
		gameStatus.setTextColor(Color.BLACK);
	    gameStatus.setTextSize(20); 
	    //set up menu button
	    menu = new Button(this);
	    menu.setText("Menu");
	    menu.setTextSize(20);
	    
		TableLayout layout = new TableLayout(this);
		layout.setLayoutParams(new TableLayout.LayoutParams(4, 5));
		//work on fixing layout to fit phones of different size...
//	    LinearLayout layout = new LinearLayout(this);
//	    LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(
//	    		LinearLayout.LayoutParams.MATCH_PARENT,
//	    		LinearLayout.LayoutParams.MATCH_PARENT);
//	    layout.setLayoutParams(ll);
		layout.setPadding(1, 1, 1, 1);
		layout.setBackgroundResource(R.drawable.background);

		board = new Button[8][8];
		model = new ChessModel();
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				board[row][col] = new Button(this);
				board[row][col].setOnClickListener(this);
			}
		}
		
		displayBoard();
		
		
		for (int row = 0; row < 8; row++) {
			TableRow tableRow = new TableRow(this);
			for (int col = 0; col < 8; col++) {
				tableRow.addView(board[row][col], 60, 75);
			}
			layout.addView(tableRow);
		}
		layout.addView(gameStatus);
		//layout.addView(menu, 160, 100);
		
		checkerTheBoard();

		super.setContentView(layout);
	}
	
	private void displayBoard(){
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (model.pieceAt(row, col) == null) {
					board[row][col].setBackgroundResource(R.drawable.emptyblack);
				} else if (model.pieceAt(row, col).type().equals("Pawn")) {
					if (model.pieceAt(row, col).player() == Player.BLACK) {
						if (row%2 == col%2){
							board[row][col].setBackgroundResource(R.drawable.blackpawnb);
						} else{
							board[row][col].setBackgroundResource(R.drawable.blackpawnw);
						}
					} else if(row%2 == col%2) {
						board[row][col].setBackgroundResource(R.drawable.whitepawnb);
					} else{
						board[row][col].setBackgroundResource(R.drawable.whitepawnw);
					}
				} else if (model.pieceAt(row, col).type().equals("Rook")) {
					if (model.pieceAt(row, col).player() == Player.BLACK) {
						if (row%2 == col%2){
							board[row][col].setBackgroundResource(R.drawable.blackrookb);
						} else{
							board[row][col].setBackgroundResource(R.drawable.blackrookw);
						}
					} else if(row%2 == col%2) {
						board[row][col].setBackgroundResource(R.drawable.whiterookb);
					} else{
						board[row][col].setBackgroundResource(R.drawable.whiterookw);
					}
				} else if (model.pieceAt(row, col).type().equals("Bishop")) {
					if (model.pieceAt(row, col).player() == Player.BLACK) {
						if (row%2 == col%2){
							board[row][col].setBackgroundResource(R.drawable.blackbishopb);
						} else{
							board[row][col].setBackgroundResource(R.drawable.blackbishopw);
						}
					} else if(row%2 == col%2) {
						board[row][col].setBackgroundResource(R.drawable.whitebishopb);
					} else{
						board[row][col].setBackgroundResource(R.drawable.whitebishopw);
					}
				} else if (model.pieceAt(row, col).type().equals("King")) {
					if (model.pieceAt(row, col).player() == Player.BLACK) {
						if (row%2 == col%2){
							board[row][col].setBackgroundResource(R.drawable.blackkingb);
						} else{
							board[row][col].setBackgroundResource(R.drawable.blackkingw);
						}
					} else if(row%2 == col%2) {
						board[row][col].setBackgroundResource(R.drawable.whitekingb);
					} else{
						board[row][col].setBackgroundResource(R.drawable.whitekingw);
					}
				} else if (model.pieceAt(row, col).type().equals("Knight")) {
					if (model.pieceAt(row, col).player() == Player.BLACK) {
						if (row%2 == col%2){
							board[row][col].setBackgroundResource(R.drawable.blackknightb);
						} else{
							board[row][col].setBackgroundResource(R.drawable.blackknightw);
						}
					} else if(row%2 == col%2) {
						board[row][col].setBackgroundResource(R.drawable.whiteknightb);
					} else{
						board[row][col].setBackgroundResource(R.drawable.whiteknightw);
					}
				} else if (model.pieceAt(row, col).type().equals("Queen")) {
					if (model.pieceAt(row, col).player() == Player.BLACK) {
						if (row%2 == col%2){
							board[row][col].setBackgroundResource(R.drawable.blackqueenb);
						} else{
							board[row][col].setBackgroundResource(R.drawable.blackqueenw);
						}
					} else if(row%2 == col%2) {
						board[row][col].setBackgroundResource(R.drawable.whitequeenb);
					} else{
						board[row][col].setBackgroundResource(R.drawable.whitequeenw);
					}
				}  else {
					board[row][col].setBackgroundResource(R.drawable.emptyblack);
				}
			}
		}
		checkerTheBoard();
	}
	
	private void checkerTheBoard(){
		for(int row = 0; row < 8; row++){
			for(int col =0; col<8; col++ ){
				if(row%2 == col%2){
					if(model.pieceAt(row, col)==null){
						board[row][col].setBackgroundResource(R.drawable.emptywhite);
					}
				}
			}
		}
	}
	
	private void gameStatus(){
		if (model.isValidMove(move) == false) {
			if (model.currentPlayer() == Player.BLACK) {
				gameStatus.setText("Invalid Move "
						+ "	Remains Black's Turn!");
			} else {
				gameStatus.setText("Invalid Move "
						+ "	Remains White's Turn!");
			}
		} else {
			if (model.currentPlayer() == Player.BLACK) {
				gameStatus.setText("Game in Progress! "
						+ "White's Turn!");
			} else {
				gameStatus.setText("Game in Progress! "
						+ "Black's Turn!");
			}

		}
	}
	
	private void complete(){
//		if (model.isComplete() == true) {
//			for (int row = 0; row < 8; row++) {
//				for (int col = 0; col < 8; col++) {
//					board[row][col].setEnabled(false);
//				}
//			}
//			if (model.currentPlayer() == Player.WHITE) {
//				gameStatus.setText("Black Wins!");
//			} else {
//				gameStatus.setText("White Wins!");
//			}
//		} else {
//			check();
//
//		}
	}
	
	private void check(){
		if (model.inCheck() == true) {
			if (model.getwCheck() == 1) {
				gameStatus.setText("White in Check!");
			}
			if (model.getbCheck() == 1) {
				gameStatus.setText("Black in Check!");
			}
		}
	}
	
	public void onClick(View view) {
		Button buttonPressed = ((Button) view);
		
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (buttonPressed == board[row][col]) {
					if (count == 0) {
						fromRow = row;
						fromCol = col;
						count++;
					} else {
						move = new Move(fromRow, fromCol, row, col);
						gameStatus();
						if (model.isValidMove(move) == true
								&& model.blockCheckMove(move) == true) {
							model.move(move);
							displayBoard();
							complete();
						} else if (model.blockCheckMove(move) == false) {
							gameStatus.setText("Cant move into check!");
						}
						count = 0;

					}
				}
			}
		}
	}
}
