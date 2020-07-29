package Figures;

import Board.Board;

public class Knight extends Figure {
    public Knight(boolean isWhite, int x, int y) {
        super(isWhite, x, y, 3);
    }

    @Override
    public boolean isGoodToGo(Board board, int x, int y) {
        if(!board.getSquareBoard()[y][x].isOccupied() || board.getSquareBoard()[y][x].getFigure().isWhite() == !isWhite()){
            if(Math.abs(y - this.y) == 2 && Math.abs(x - this.x) == 1){
                return true;
            }
            if(Math.abs(x - this.x) == 2 && Math.abs(y - this.y) == 1){
                return true;
            }
        }
        return false;
    }
}
