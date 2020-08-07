package engine.Figures;

import engine.Board.Board;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Queen extends Figure {
    public Queen(boolean isWhite, byte x, byte y, ImageView imageView) {
        super(isWhite, x, y, (byte) 5,imageView);
    }


    @Override
    public boolean isGoodToGo(Board board, byte x, byte y) {
        if(board.getSquareBoard()[y][x].isOccupied() && board.getSquareBoard()[y][x].getFigure().isWhite() == this.isWhite){
            return false;
        }
        else{
            int iterator = 1;
            if(Math.abs(this.x - x) == Math.abs(this.y - y)) {

                //right up
                if (this.x - x < 0 && this.y - y > 0) {
                    while (this.x + iterator != x) {
                        //check if there is a piece blocking way
                        if (board.getSquareBoard()[this.y - iterator][this.x + iterator].isOccupied()) {
                            return false;
                        }
                        iterator++;
                    }
                    return true;
                }
                //right down
                if (this.x - x < 0 && this.y - y < 0) {
                    while (this.x + iterator != x) {
                        //check if there is a piece blocking way
                        if (board.getSquareBoard()[this.y + iterator][this.x + iterator].isOccupied()) {
                            return false;
                        }
                        iterator++;
                    }
                    return true;
                }
                //left down
                if (this.x - x > 0 && this.y - y < 0) {
                    while (this.x - iterator != x) {
                        //check if there is a piece blocking way
                        if (board.getSquareBoard()[this.y + iterator][this.x - iterator].isOccupied()) {
                            return false;
                        }
                        iterator++;
                    }
                    return true;
                }
                //left up
                if (this.x - x > 0 && this.y - y > 0) {
                    while (this.x - iterator != x) {
                        //check if there is a piece blocking way
                        if (board.getSquareBoard()[this.y - iterator][this.x - iterator].isOccupied()) {
                            return false;
                        }
                        iterator++;
                    }
                    return true;
                }
            }

            iterator = 1;
            //up
            if(this.x == x && this.y > y){
                while(this.y - iterator != y){
                    //check if there is a piece blocking way
                    if(board.getSquareBoard()[this.y - iterator][x].isOccupied()){
                        return false;
                    }
                    iterator++;
                }
                return true;
            }
            iterator = 1;
            //down
            if(this.x == x && this.y < y) {
                while(this.y + iterator != y){
                    //check if there is a piece blocking way
                    if(board.getSquareBoard()[this.y + iterator][x].isOccupied()){
                        return false;
                    }
                    iterator++;
                }
                return true;
            }
            iterator = 1;
            //right
            if(this.y == y && this.x < x){
                while(this.x + iterator != x){
                    //check if there is a piece blocking way
                    if(board.getSquareBoard()[y][this.x + iterator].isOccupied()){
                        return false;
                    }
                    iterator++;
                }
                return true;
            }
            iterator = 1;
            //left
            if(this.y == y && this.x > x){
                while(this.x - iterator != x){
                    //check if there is a piece blocking way
                    if(board.getSquareBoard()[y][this.x - iterator].isOccupied()){
                        return false;
                    }
                    iterator++;
                }
                return true;
            }
        }

        return false;
    }
}

