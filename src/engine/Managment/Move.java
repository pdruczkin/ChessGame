package engine.Managment;

import engine.Board.Board;
import engine.Figures.Figure;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class Move {


    private Figure bufferFigure;
    private boolean isOldFigureMoved;

    public void moveFigureAndView(Board board, int newX, int newY, int oldX, int oldY, int cell, Group root){

        extraRook(board, oldX, oldY, newX, newY, cell);
        extraPawn(board, oldX, oldY, newX, newY, root);

        if(board.getSquareBoard()[newY][newX].isOccupied()){
            root.getChildren().remove(board.getSquareBoard()[newY][newX].getFigure().getImageview());
        }
        board.getSquareBoard()[oldY][oldX].setOccupied(false);
        board.getSquareBoard()[newY][newX].setOccupied(true);
        board.getSquareBoard()[newY][newX].setFigure(board.getSquareBoard()[oldY][oldX].getFigure());
        board.getSquareBoard()[newY][newX].getFigure().setX((byte)newX);
        board.getSquareBoard()[newY][newX].getFigure().setY((byte)newY);
        board.getSquareBoard()[oldY][oldX].setFigure(null);
        board.getSquareBoard()[newY][newX].getFigure().setMoved(true);
        hasPawnJustMovedTwo(board, newX, newY, oldY);

        ImageView imageView = board.getSquareBoard()[newY][newX].getFigure().getImageview();
        imageView.setX(newX * cell);
        imageView.setY(newY * cell);
        board.getSquareBoard()[newY][newX].getFigure().setImageView(imageView);
    }

    private void extraRook(Board board, int oldX, int oldY, int newX, int newY, int cell){
        if(isCastling(board, oldX, oldY, newX, newY)){
            if(newY == 7){
                if(newX == 1) {
                    ImageView imageView = board.getSquareBoard()[7][0].getFigure().getImageview();
                    imageView.setX(2 * cell);
                    imageView.setY(7 * cell);
                    moveFigure(board, 2, 7, 0, 7);
                    board.getSquareBoard()[7][2].getFigure().setImageView(imageView);
                }
                else if(newX == 6){
                    ImageView imageView = board.getSquareBoard()[7][7].getFigure().getImageview();
                    imageView.setX(5 * cell);
                    imageView.setY(7 * cell);
                    moveFigure(board, 5, 7, 7, 7);
                    board.getSquareBoard()[7][5].getFigure().setImageView(imageView);
                }
            }
            else{
                if(newX == 1) {
                    ImageView imageView = board.getSquareBoard()[0][0].getFigure().getImageview();
                    imageView.setX(2 * cell);
                    imageView.setY(0);
                    moveFigure(board, 2, 0, 0, 0);
                    board.getSquareBoard()[0][2].getFigure().setImageView(imageView);
                }
                else if(newX == 6){
                    ImageView imageView = board.getSquareBoard()[0][7].getFigure().getImageview();
                    imageView.setX(5 * cell);
                    imageView.setY(0);
                    moveFigure(board, 5, 0, 7, 0);
                    board.getSquareBoard()[0][5].getFigure().setImageView(imageView);
                }
            }
        }
    }

    private void extraPawn(Board board, int oldX, int oldY, int newX, int newY, Group root){
        if(isEnPassantAttacking(board, oldX, oldY, newX, newY)){
            if(newY == 2){
                board.getSquareBoard()[newY+1][newX].setOccupied(false);
                root.getChildren().remove(board.getSquareBoard()[newY+1][newX].getFigure().getImageview());
                board.getSquareBoard()[newY+1][newX].setFigure(null);
            }
            else{
                board.getSquareBoard()[newY-1][newX].setOccupied(false);
                root.getChildren().remove(board.getSquareBoard()[newY-1][newX].getFigure().getImageview());
                board.getSquareBoard()[newY-1][newX].setFigure(null);
            }
        }
    }

    public void moveFigure(Board board, int newX, int newY, int oldX, int oldY) {
            bufferFigure = board.getSquareBoard()[newY][newX].getFigure();
            isOldFigureMoved = board.getSquareBoard()[oldY][oldX].getFigure().isMoved();
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
        board.getSquareBoard()[oldY][oldX].getFigure().setMoved(isOldFigureMoved);
        board.getSquareBoard()[oldY][oldX].setOccupied(true);        
        board.getSquareBoard()[newY][newX].setFigure(bufferFigure);

        board.getSquareBoard()[newY][newX].setOccupied(bufferFigure != null);
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
                return Math.abs(newX - oldX) == 1;
            }
        }
        return false;
    }



}