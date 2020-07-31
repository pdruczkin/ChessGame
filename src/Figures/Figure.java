package Figures;

import Board.Board;

public abstract class Figure {
    public Figure(boolean isWhite, byte x, byte y, byte type) {
        this.isWhite = isWhite;
        this.type = type;
        isMoved = false;
    }

    public abstract boolean isGoodToGo(Board board, byte x, byte y);

    public boolean isWhite() {
        return isWhite;
    }

    public byte getType() {
        return type;
    }

    public boolean isMoved() { return isMoved; }

    public void setMoved(boolean moved) { isMoved = moved; }

    protected boolean isWhite;
    protected byte type;
    protected byte x,y;
    protected boolean isMoved;
}
