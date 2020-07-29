package Figures;

import Board.Board;

public class Knight extends Figure {
    public Knight(boolean isWhite, int x, int y) {
        super(isWhite, x, y, 3);
    }

    @Override
    public boolean isGoodToGo(Board board, int x, int y) {
        return false;
    }

}
