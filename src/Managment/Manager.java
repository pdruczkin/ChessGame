package Managment;

import Board.Board;
import Figures.*;
import Player.Player;

import java.util.Scanner;

public class Manager {
    private Board board = new Board();
    private MateFinder mateFinder = new MateFinder();
    private PossibleMovesFinder possibleMovesFinder = new PossibleMovesFinder();
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
        board.getSquareBoard()[7][4].setFigure(new King(true,(byte)4,(byte)7));
        board.getSquareBoard()[7][4].setOccupied(true);
    }
    public boolean extortMove(Board board, Player performer){

        System.out.println(performer.getColourName() + "'s move:");
        performer.setOldCords();

        if(board.getSquareBoard()[performer.getOldY()][performer.getOldX()].isOccupied()
                && board.getSquareBoard()[performer.getOldY()][performer.getOldX()].getFigure().isWhite() == performer.isWhite()){
            possibleMovesFinder.setPossibleMove(board, performer.getOldX(), performer.getOldY());
            if(!possibleMovesFinder.checkPossibleMoves()){
                System.out.println("This figure can't move anywhere");
                return false;
            }
            possibleMovesFinder.printPossibleMoves();
            performer.setNewCords();
            if(possibleMovesFinder.checkMove(performer.getNewX(), performer.getNewY())){
                move.moveFigure(board, performer.getNewX(), performer.getNewY(), performer.getOldX(), performer.getOldY());
                return true;
            }
        }
            System.out.println("You can't move the figure, try again");
            return false;

    }


    public void run(Player player1, Player player2){
        setFigures();

        Player performer = new Player();
        performer = player1;
        boolean check = true;

        while(!mateFinder.isCheckMate(board,true) || !mateFinder.isCheckMate(board,false)
                || !mateFinder.isStaleMate(board,true, check) || !mateFinder.isStaleMate(board,false,check)){
            possibleMovesFinder.FindAnyPossibleMoves(board);
            check = possibleMovesFinder.getAreAnyPossibleMoves();
            do {
                board.print(true);
            }while(!extortMove(board, performer));

            if(performer.equals(player1)){ performer = player2; }
            else{ performer = player1; }

        }
    }
}
