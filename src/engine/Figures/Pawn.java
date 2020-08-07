package engine.Figures;

import engine.Board.Board;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Pawn extends Figure{

    public Pawn(boolean isWhite, byte x, byte y, ImageView imageView) {
        super(isWhite, x, y, (byte) 1,imageView);
    }


    @Override
    public boolean isGoodToGo(Board board, byte x, byte y) {

        //move forward
        if(x == this.x && !board.getSquareBoard()[y][x].isOccupied()){
            if(this.isWhite && y == this.y - 1) { return true; }
            else if(!this.isWhite && y == this.y + 1 && y < 8){ return true; }
            if(this.isWhite && y == this.y - 2 && !isMoved()) { return true; }
            else if(!this.isWhite && y == this.y + 2 && !isMoved()) { return true; }
        }

        //attack
        if(board.getSquareBoard()[y][x].isOccupied() && board.getSquareBoard()[y][x].getFigure().isWhite() != this.isWhite){
            if((this.x == x + 1 || this.x == x - 1 )){

                if(this.isWhite && y == this.y - 1){
                    return true;
                }
                else if(!this.isWhite && y == this.y + 1){
                    return true;
                }
            }
        }

        // 'en passant' attack (baguette xD)
        if((this.y == 3 || this.y == 4) && (y == 2 || y == 5)){
            if(this.isWhite){
                if(board.getSquareBoard()[y+1][x].isOccupied() && board.getSquareBoard()[y+1][x].getFigure().hasJustMovedTwo()) return true;
            }
            else{
                if(board.getSquareBoard()[y-1][x].isOccupied() && board.getSquareBoard()[y-1][x].getFigure().hasJustMovedTwo()) return true;
            }
        }

        return false;
    }
}