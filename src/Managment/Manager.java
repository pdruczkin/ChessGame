package Managment;

import Board.Board;
import Figures.*;
import Player.Player;

import javax.management.MBeanAttributeInfo;
import java.awt.image.BandedSampleModel;
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

    private void presentPromotionOptions(){
        System.out.println("Promote the pawn to: ");
        System.out.println("1 -> ROOK");
        System.out.println("2 -> KNIGHT");
        System.out.println("3 -> BISHOP");
        System.out.println("4 -> QUEEN");
    }

    private void changingFigure(Board board, Player performer, int choice){
        switch (choice) {
            case 2 -> board.getSquareBoard()[performer.getNewY()][performer.getNewX()].setFigure(new Rook(performer.isWhite(), (byte) performer.getNewX(), (byte) performer.getNewY()));
            case 3 -> board.getSquareBoard()[performer.getNewY()][performer.getNewX()].setFigure(new Knight(performer.isWhite(), (byte) performer.getNewX(), (byte) performer.getNewY()));
            case 4 -> board.getSquareBoard()[performer.getNewY()][performer.getNewX()].setFigure(new Bishop(performer.isWhite(), (byte) performer.getNewX(), (byte) performer.getNewY()));
            case 5 -> board.getSquareBoard()[performer.getNewY()][performer.getNewX()].setFigure(new Queen(performer.isWhite(), (byte) performer.getNewX(), (byte) performer.getNewY()));
        }
    }

    private void promotePawn(Board board, Player performer){
        presentPromotionOptions();
        Scanner scanner = new Scanner(System.in);
        int answer;
        do{
            answer = scanner.nextInt();
            changingFigure(board, performer, answer + 1);
            if(answer < 1 || answer > 4) System.out.println("Enter a valid value");
        }while(answer < 1 || answer > 4);
    }

    public boolean extortMove(Board board, Player performer){

        System.out.println(performer.getColourName() + "'s move:");
        performer.setOldCords();

        if(board.getSquareBoard()[performer.getOldY()][performer.getOldX()].isOccupied()
                && board.getSquareBoard()[performer.getOldY()][performer.getOldX()].getFigure().isWhite() == performer.isWhite()){
            possibleMovesFinder.setPossibleMove(board, performer.getOldX(), performer.getOldY(),performer.isWhite());
            if(!possibleMovesFinder.checkPossibleMoves()){
                System.out.println("This figure can't move anywhere");
                return false;
            }
            possibleMovesFinder.printPossibleMoves();
            performer.setNewCords();
            if(possibleMovesFinder.checkMove(performer.getNewX(), performer.getNewY())){
                possibleMovesFinder.clearJustMovedTwo(board);
                move.moveFigure(board, performer.getNewX(), performer.getNewY(), performer.getOldX(), performer.getOldY(), true);
                if(board.getSquareBoard()[performer.getNewY()][performer.getNewX()].getFigure().getType() == 1){
                    if((performer.isWhite() && performer.getNewY() == 0) || (!performer.isWhite() && performer.getNewY() == 7)) {
                        promotePawn(board, performer);
                    }
                }
                return true;
            }
        }
        System.out.println("You can't move the figure, try again");
        return false;
    }

    private void endingMessage(Player performer){
        System.out.println(performer.getColourName() + " wins!");
    }

    public void run(Player player1, Player player2){
        setFigures();

        Player performer = new Player();
        performer = player2;
        boolean check = true;

        while(!(mateFinder.isCheckMate(board, !performer.isWhite())
                && !mateFinder.isStaleMate(board, !performer.isWhite(),check))){
            // System.out.println(mateFinder.isMate(board,true) + "  " + mateFinder.isMate(board,false) );

            if(performer.equals(player1)){ performer = player2; }
            else{ performer = player1; }

            possibleMovesFinder.FindAnyPossibleMoves(board, !performer.isWhite());
            check = possibleMovesFinder.getAreAnyPossibleMoves();
            do {
                board.print(true);
                // System.out.println(mateFinder.isMate(board,true) + "  " + mateFinder.isMate(board,false) );
            }while(!extortMove(board, performer));
        }
        endingMessage(performer);
    }
}