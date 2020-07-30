package Managment;

import Board.Board;
import Figures.*;
import Player.Player;

import java.util.Scanner;

public class Manager {
    private Board board = new Board();
    private boolean [][] possibleMoves = new boolean[8][8];
    private MateFinder mateFinder = new MateFinder();

    private void setFigures(){
        //setting Pawns(1)
        for (int i = 0; i < 8; i++) {
            board.getSquareBoard()[1][i].setFigure(new Pawn(false,i,1));
            board.getSquareBoard()[1][i].setOccupied(true);
        }
        for (int i = 0; i < 8; i++) {
            board.getSquareBoard()[6][i].setFigure(new Pawn(true,i,6));
            board.getSquareBoard()[6][i].setOccupied(true);
        }

        //setting Rooks(2)
        for(int i = 0; i < 8; i = i+7){
            board.getSquareBoard()[0][i].setFigure(new Rook(false,i,0));
            board.getSquareBoard()[0][i].setOccupied(true);
        }
        for(int i = 0; i < 8; i = i+7){
            board.getSquareBoard()[7][i].setFigure(new Rook(true,i,7));
            board.getSquareBoard()[7][i].setOccupied(true);
        }

        //setting Knights(3)
        for(int i = 1; i < 8; i = i+5){
            board.getSquareBoard()[0][i].setFigure(new Knight(false,i,0));
            board.getSquareBoard()[0][i].setOccupied(true);
        }
        for(int i = 1; i < 8; i = i+5){
            board.getSquareBoard()[7][i].setFigure(new Knight(true,i,7));
            board.getSquareBoard()[7][i].setOccupied(true);
        }

        //setting Bishops(4)
        for(int i = 2; i < 8; i = i+3){
            board.getSquareBoard()[0][i].setFigure(new Bishop(false,i,0));
            board.getSquareBoard()[0][i].setOccupied(true);
        }
        for(int i = 2; i < 8; i = i+3){
            board.getSquareBoard()[7][i].setFigure(new Bishop(true,i,7));
            board.getSquareBoard()[7][i].setOccupied(true);
        }

        //setting Queens(5)
        board.getSquareBoard()[0][3].setFigure(new Queen(false,3,0));
        board.getSquareBoard()[0][3].setOccupied(true);
        board.getSquareBoard()[7][3].setFigure(new Queen(true,3,7));
        board.getSquareBoard()[7][3].setOccupied(true);

        //setting Kings(6)
        board.getSquareBoard()[0][4].setFigure(new King(false,4,0));
        board.getSquareBoard()[0][4].setOccupied(true);
        board.getSquareBoard()[4][4].setFigure(new King(true,4,4));
        board.getSquareBoard()[4][4].setOccupied(true);
    }

    private void clearPossibleMoves(){
        for (int i = 0; i < 8 ; i++) {
            for (int j = 0; j < 8; j++) {
                possibleMoves[i][j] = false;
            }
        }
    }

    private void generetePossibleMoves(int x, int y){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                possibleMoves[i][j] = board.getSquareBoard()[y][x].getFigure().isGoodToGo(board,j,i);
            }
        }
    }

    private void setPossibleMoves(int x, int y){
        clearPossibleMoves();
        if(board.getSquareBoard()[y][x].isOccupied()){
            generetePossibleMoves(x,y);
        }
    }

    private void printPossibleMoves(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(possibleMoves[i][j]) System.out.print(1);
                else System.out.print("0");
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean moveFigure( int newX, int newY, int oldX, int oldY) {
        if (possibleMoves[newY][newX]){
            board.getSquareBoard()[oldY][oldX].getFigure().setX(newX);
            board.getSquareBoard()[oldY][oldX].getFigure().setY(newY);
            board.getSquareBoard()[oldY][oldX].setOccupied(false);
            board.getSquareBoard()[newY][newX].setOccupied(true);
            board.getSquareBoard()[newY][newX].setFigure(board.getSquareBoard()[oldY][oldX].getFigure());
            board.getSquareBoard()[oldY][oldX].setFigure(null);
            board.getSquareBoard()[newY][newX].getFigure().setMoved(true);
            return true;
        }
        else{
            System.out.println("You can't go there, try again");
            return false;
        }
    }

    private boolean extortMove(Scanner scanner, Player performer){
        int ansOldX, ansOldY, ansNewX, ansNewY;

        System.out.println(performer.getColourName() + "'s move:");
        System.out.print("Old X: ");
        ansOldX = scanner.nextInt();
        System.out.print("Old Y: ");
        ansOldY = scanner.nextInt();

        if(board.getSquareBoard()[ansOldY][ansOldX].isOccupied() && board.getSquareBoard()[ansOldY][ansOldX].getFigure().isWhite() == performer.isWhite()){
            setPossibleMoves(ansOldX, ansOldY);
            printPossibleMoves();

            System.out.print("New X: ");
            ansNewX = scanner.nextInt();
            System.out.print("New Y: ");
            ansNewY = scanner.nextInt();

            return moveFigure(ansNewX, ansNewY, ansOldX, ansOldY);
        }
        else{
            System.out.println("You can't move the figure, try again");
            return false;
        }
    }

    public void run(Player player1, Player player2){
        setFigures();

        Scanner scanner = new Scanner(System.in);
        Player performer = new Player();
        performer = player1;

        // zobacz alt + 9, fajna sprawa // +1

        while(true){
            do {
                board.print(true);
                System.out.println(mateFinder.isMate(board,true) + "  " + mateFinder.isMate(board,false));
            }while(!extortMove(scanner, performer));

            if(performer.equals(player1)){ performer = player2; }
            else{ performer = player1; }

        }
        //board.print(true);
        //System.out.println(matFinder.isMat(board,true) + "  " + matFinder.isMat(board,false));
    }
}
