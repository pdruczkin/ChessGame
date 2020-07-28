package Figures;

import Board.Board;

public class Pawn extends Figure{
    public Pawn(boolean isWhite, int x, int y) {
        super(isWhite, x, y,1);
    }

    @Override
    public boolean isGoodToGo(Board board, int x, int y) {
        if(x == this.x ){
            if(isWhite && y == this.y - 1 && y > 0){
                return true;
            }
            else if(!isWhite && y == this.y + 1 && y < 8){
                return true;
            }
        }
        return false;
    }



}
