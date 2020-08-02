package Managment;

import Board.Board;
import Figures.Figure;

public class Move {

    private boolean [][] possibleMoves = new boolean[8][8];

    private Figure bufferFigure;
    private boolean isOldFigureMoved;


    public boolean[][] getPossibleMoves() {
        return possibleMoves;
    }

    public void moveFigure(Board board, int newX, int newY, int oldX, int oldY, boolean isActuallyMoving) {
            bufferFigure = board.getSquareBoard()[newY][newX].getFigure();
            isOldFigureMoved = board.getSquareBoard()[oldY][oldX].getFigure().isMoved();
            if(isActuallyMoving){
                extraRook(board, oldX, oldY, newX, newY);
                extraPawn(board, oldX, oldY, newX, newY);
            }
            board.getSquareBoard()[oldY][oldX].setOccupied(false);
            board.getSquareBoard()[newY][newX].setOccupied(true);
            board.getSquareBoard()[newY][newX].setFigure(board.getSquareBoard()[oldY][oldX].getFigure());
            board.getSquareBoard()[newY][newX].getFigure().setX((byte)newX);
            board.getSquareBoard()[newY][newX].getFigure().setY((byte)newY);
            board.getSquareBoard()[oldY][oldX].setFigure(null);
            board.getSquareBoard()[newY][newX].getFigure().setMoved(true);
            if(isActuallyMoving) hasPawnJustMovedTwo(board, newX, newY, oldY);
    }
    
    public void removeFigure(Board board, int newX, int newY, int oldX, int oldY){

        board.getSquareBoard()[oldY][oldX].setFigure(board.getSquareBoard()[newY][newX].getFigure());
        board.getSquareBoard()[oldY][oldX].getFigure().setX((byte)oldX);
        board.getSquareBoard()[oldY][oldX].getFigure().setY((byte)oldY);
        board.getSquareBoard()[oldY][oldX].getFigure().setMoved(isOldFigureMoved);
        board.getSquareBoard()[oldY][oldX].setOccupied(true);        
        board.getSquareBoard()[newY][newX].setFigure(bufferFigure);

        if(bufferFigure != null) board.getSquareBoard()[newY][newX].setOccupied(true);
        else board.getSquareBoard()[newY][newX].setOccupied(false);        
    }

    private void hasPawnJustMovedTwo(Board board, int newX, int newY, int oldY){
        if(board.getSquareBoard()[newY][newX].getFigure().getType() == 1 && Math.abs(oldY - newY) == 2){ // Pawn
            board.getSquareBoard()[newY][newX].getFigure().setJustMovedTwo(true);
        }
    }

    private boolean isCastling(Board board, int oldX, int oldY, int newX, int newY){
        if(board.getSquareBoard()[oldY][oldX].getFigure().getType() == 6
                && !board.getSquareBoard()[oldY][oldX].getFigure().isMoved()){
            return ((board.getSquareBoard()[oldY][oldX].getFigure().isWhite() && (newY == 7 && (newX == 1 || newX == 6)))
                    ||(!board.getSquareBoard()[oldY][oldX].getFigure().isWhite() && (newY == 0 && (newX == 1 || newX == 6))));
        }
        else return false;
    }

    private boolean isEnPassantAttacking(Board board, int oldX, int oldY, int newX, int newY){
        if(board.getSquareBoard()[oldY][oldX].getFigure().getType() == 1){
            if(!board.getSquareBoard()[newY][newX].isOccupied()){
                if(Math.abs(newX - oldX) == 1){
                    return true;
                }
            }
        }
        return false;
    }

    private void extraRook(Board board, int oldX, int oldY, int newX, int newY){
        if(isCastling(board, oldX, oldY, newX, newY)){
            if(newY == 7){
                if(newX == 1) moveFigure(board, 2, 7, 0, 7, false);
                else if(newX == 6) moveFigure(board, 5, 7, 7, 7, false);
            }
            else{
                if(newX == 1) moveFigure(board, 2, 7, 0, 0, false);
                else if(newX == 6) moveFigure(board, 5, 7, 7, 0, false);
            }
        }
    }

    private void extraPawn(Board board, int oldX, int oldY, int newX, int newY){
        if(isEnPassantAttacking(board, oldX, oldY, newX, newY)){
            if(newY == 2){
                board.getSquareBoard()[newY+1][newX].setOccupied(false);
                board.getSquareBoard()[newY+1][newX].setFigure(null);
            }
            else{
                board.getSquareBoard()[newY-1][newX].setOccupied(false);
                board.getSquareBoard()[newY-1][newX].setFigure(null);
            }
        }
    }
}