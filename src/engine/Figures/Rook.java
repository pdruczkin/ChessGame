package engine.Figures;

import engine.Board.Board;
import javafx.scene.image.ImageView;

public class Rook extends Figure{
    public Rook(boolean isWhite, byte x, byte y, ImageView imageView) {
        super(isWhite, x, y, (byte) 2, imageView);
    }

    @Override
    public boolean isGoodToGo(Board board, byte x, byte y) {
        if(board.getSquareBoard()[y][x].isOccupied() && board.getSquareBoard()[y][x].getFigure().isWhite() == this.isWhite){
            return false;
        }
        else{
            int iterator = 1;
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

            //down
            else if(this.x == x && this.y < y) {
                while(this.y + iterator != y){
                    //check if there is a piece blocking way
                    if(board.getSquareBoard()[this.y + iterator][x].isOccupied()){
                        return false;
                    }
                    iterator++;
                }
                return true;
            }

            //right
            else if(this.y == y && this.x < x){
                while(this.x + iterator != x){
                    //check if there is a piece blocking way
                    if(board.getSquareBoard()[y][this.x + iterator].isOccupied()){
                        return false;
                    }
                    iterator++;
                }
                return true;
            }

            //left
            else if(this.y == y && this.x > x){
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
