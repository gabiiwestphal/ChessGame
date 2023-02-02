package Chess;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Chess.Chess.ChessException;
import Chess.Chess.ChessMatch;
import Chess.Chess.ChessPiece;
import Chess.Chess.ChessPosition;

public class Program {
    public static void main(String [] args){
		
        Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();
		
		while (!chessMatch.getCheckMate()) {
			try {
				PrintTabuleiro.clearScreen();
				PrintTabuleiro.printMatch(chessMatch, captured);
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = PrintTabuleiro.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				PrintTabuleiro.clearScreen();
				PrintTabuleiro.printBoard(chessMatch.getPieces(), possibleMoves);
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = PrintTabuleiro.readChessPosition(sc);
				
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				
				if (capturedPiece != null) {
					captured.add(capturedPiece);
				}
				
				if (chessMatch.getPromoted() != null) {
					System.out.print("Enter piece for promotion (B/N/R/Q): ");
					String type = sc.nextLine().toUpperCase();
					while (!type.equals("B") && !type.equals("N") && !type.equals("R") & !type.equals("Q")) {
						System.out.print("Invalid value! Enter piece for promotion (B/N/R/Q): ");
						type = sc.nextLine().toUpperCase();
					}
					chessMatch.replacePromotedPiece(type);
				}
			}
			catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		PrintTabuleiro.clearScreen();
		PrintTabuleiro.printMatch(chessMatch, captured);
	}
}
