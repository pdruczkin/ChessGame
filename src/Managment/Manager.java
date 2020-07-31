package Managment;

import Board.Board;
import Figures.*;
import Player.Player;

import java.util.Scanner;

public class Manager {
    private Board board = new Board();
    private MateFinder mateFinder = new MateFinder();
    private Move move = new Move();

    private void setFigures(){
        //setting Pawns(1)
        for (byte i = 0; i < 8; i++) {
            board.getSquareBoard()[1][i].setFigure(new Pawn(false,i,(byte)1));
            board.getSquareBoard()[1][i].setOccupied(true);
        }
        for (byte i = 0; i < 8; i++) {
            board.getSquareBoard()[6][i].setFigure(new Pawn(true,i,(byte)6));
            board.getSquareBoard()[6][i].setOccupied(true);
        }

        //setting Rooks(2)
        for(byte i = 0; i < 8; i = (byte) (i+7)){
            board.getSquareBoard()[0][i].setFigure(new Rook(false,i,(byte)0));
            board.getSquareBoard()[0][i].setOccupied(true);
        }
        for(byte i = 0; i < 8; i = (byte) (i+7)){
            board.getSquareBoard()[7][i].setFigure(new Rook(true,i,(byte)7));
            board.getSquareBoard()[7][i].setOccupied(true);
        }

        //setting Knights(3)
        for(byte i = 1; i < 8; i = (byte) (i+5)){
            board.getSquareBoard()[0][i].setFigure(new Knight(false,i,(byte)0));
            board.getSquareBoard()[0][i].setOccupied(true);
        }
        for(byte i = 1; i < 8; i = (byte) (i+5)){
            board.getSquareBoard()[7][i].setFigure(new Knight(true,i,(byte)7));
            board.getSquareBoard()[7][i].setOccupied(true);
        }

        //setting Bishops(4)
        for(byte i = 2; i < 8; i = (byte) (i+3)){
            board.getSquareBoard()[0][i].setFigure(new Bishop(false,i,(byte)0));
            board.getSquareBoard()[0][i].setOccupied(true);
        }
        for(byte i = 2; i < 8; i = (byte) (i+3)){
            board.getSquareBoard()[7][i].setFigure(new Bishop(true,i,(byte)7));
            board.getSquareBoard()[7][i].setOccupied(true);
        }

        //setting Queens(5)
        board.getSquareBoard()[0][3].setFigure(new Queen(false,(byte)3,(byte)0));
        board.getSquareBoard()[0][3].setOccupied(true);
        board.getSquareBoard()[7][3].setFigure(new Queen(true,(byte)3,(byte)7));
        board.getSquareBoard()[7][3].setOccupied(true);

        //setting Kings(6)
        board.getSquareBoard()[0][4].setFigure(new King(false,(byte)4,(byte)0));
        board.getSquareBoard()[0][4].setOccupied(true);
        board.getSquareBoard()[4][4].setFigure(new King(true,(byte)4,(byte)4));
        board.getSquareBoard()[4][4].setOccupied(true);
    }



    public void run(Player player1, Player player2){
        setFigures();

        Player performer = new Player();
        performer = player1;

        while(true){
            do {
                board.print(true);
                System.out.println(mateFinder.isMate(board,true) + "  " + mateFinder.isMate(board,false));
            }while(!move.extortMove(board, performer));

            if(performer.equals(player1)){ performer = player2; }
            else{ performer = player1; }

        }
        //board.print(true);
        //System.out.println(matFinder.isMat(board,true) + "  " + matFinder.isMat(board,false));
    }
}
