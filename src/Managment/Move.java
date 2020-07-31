package Managment;

import Board.Board;
import Figures.Figure;
import Player.Player;
import com.sun.jdi.connect.spi.TransportService;

public class Move {

    private boolean [][] possibleMoves = new boolean[8][8];

    private Figure bufferFigure;
    
    private MateFinder mateFinder = new MateFinder();

    public boolean[][] getPossibleMoves() {
        return possibleMoves;
    }

    public boolean moveFigure(Board board, int newX, int newY, int oldX, int oldY) {
        if (possibleMoves[newY][newX]){
            bufferFigure = board.getSquareBoard()[newY][newX].getFigure();
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
    
    public void removeFigure(Board board, int newX, int newY, int oldX, int oldY){
        board.getSquareBoard()[oldY][oldX].setFigure(board.getSquareBoard()[newY][newX].getFigure());
        board.getSquareBoard()[oldY][oldX].setOccupied(true);        
        board.getSquareBoard()[newY][newX].setFigure(bufferFigure);
        if(bufferFigure != null) board.getSquareBoard()[newY][newX].setOccupied(true);
        else board.getSquareBoard()[newY][newX].setOccupied(false);        
    }

    private void clearPossibleMoves(){
        for (int i = 0; i < 8 ; i++) {
            for (int j = 0; j < 8; j++) {
                possibleMoves[i][j] = false;
            }
        }
    }
    
    public boolean fakeMove(Board board, byte j, byte i, byte x, byte y, boolean isKingWhite){
        moveFigure(board, j, i, x, y);
        if(mateFinder.isMate(board, isKingWhite)){
            removeFigure(board, j, i, x, y);
        }
        else{
            removeFigure(board, j, i, x, y);
            return true;
        }
        return false;
    }

    private void generetePossibleMoves(Board board, byte x, byte y){
        for (byte i = 0; i < 8; i++) {
            for (byte j = 0; j < 8; j++) {
                possibleMoves[i][j] = board.getSquareBoard()[y][x].getFigure().isGoodToGo(board,j,i)
                        && mateFinder.isMate(board, board.getSquareBoard()[y][x].getFigure().isWhite());
            }
        }
    }

    public void setPossibleMove(Board board, byte x, byte y){
        clearPossibleMoves();
        if(board.getSquareBoard()[y][x].isOccupied()){
            generetePossibleMoves(board, x,y);
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

    public boolean extortMove(Board board, Player performer){

        System.out.println(performer.getColourName() + "'s move:");
        performer.setOldCords();

        if(board.getSquareBoard()[performer.getOldY()][performer.getOldX()].isOccupied()
                && board.getSquareBoard()[performer.getOldY()][performer.getOldX()].getFigure().isWhite() == performer.isWhite()){
            setPossibleMove(board, performer.getOldX(), performer.getOldY());
            printPossibleMoves();
            performer.setNewCords();
            return moveFigure(board, performer.getNewX(), performer.getNewY(), performer.getOldX(), performer.getOldY());
        }
        else{
            System.out.println("You can't move the figure, try again");
            return false;
        }
    }



}
