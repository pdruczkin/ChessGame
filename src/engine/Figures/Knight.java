package engine.Figures;

import engine.Board.Board;

public class Knight extends Figure {
    public Knight(boolean isWhite, byte x, byte y) {
        super(isWhite, x, y, (byte) 3);
    }

    @Override
    public boolean isGoodToGo(Board board, byte x, byte y) {
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
