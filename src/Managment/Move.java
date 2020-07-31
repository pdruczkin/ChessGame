package Managment;

import Board.Board;
import Figures.Figure;
import Player.Player;
import com.sun.jdi.connect.spi.TransportService;

public class Move {

    private boolean [][] possibleMoves = new boolean[8][8];

    private Figure bufferFigure;


    public boolean[][] getPossibleMoves() {
        return possibleMoves;
    }

    public void moveFigure(Board board, int newX, int newY, int oldX, int oldY) {
            bufferFigure = board.getSquareBoard()[newY][newX].getFigure();
            board.getSquareBoard()[oldY][oldX].setOccupied(false);
            board.getSquareBoard()[newY][newX].setOccupied(true);
            board.getSquareBoard()[newY][newX].setFigure(board.getSquareBoard()[oldY][oldX].getFigure());
            board.getSquareBoard()[newY][newX].getFigure().setX((byte)newX);
            board.getSquareBoard()[newY][newX].getFigure().setY((byte)newY);
            board.getSquareBoard()[oldY][oldX].setFigure(null);
            board.getSquareBoard()[newY][newX].getFigure().setMoved(true);
    }
    
    public void removeFigure(Board board, int newX, int newY, int oldX, int oldY){

        board.getSquareBoard()[oldY][oldX].setFigure(board.getSquareBoard()[newY][newX].getFigure());
        board.getSquareBoard()[oldY][oldX].getFigure().setX((byte)oldX);
        board.getSquareBoard()[oldY][oldX].getFigure().setY((byte)oldY);
        board.getSquareBoard()[oldY][oldX].setOccupied(true);        
        board.getSquareBoard()[newY][newX].setFigure(bufferFigure);
        if(bufferFigure != null) board.getSquareBoard()[newY][newX].setOccupied(true);
        else board.getSquareBoard()[newY][newX].setOccupied(false);        
    }

}
