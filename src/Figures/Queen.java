package Figures;

import Board.Board;

public class Queen extends Figure {
    public Queen(boolean isWhite, int x, int y) {
        super(isWhite, x, y, 5);
    }

    @Override
    public boolean isGoodToGo(Board board, int x, int y) {




        return false;
    }
}
