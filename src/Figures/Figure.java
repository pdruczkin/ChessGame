package Figures;

import Board.Board;

public abstract class Figure {
    public Figure(boolean isWhite, int x, int y, byte type) {
        this.isWhite = isWhite;
        this.x = x;
        this.y = y;
        this.type = type;
        isMoved = false;
    }

    public abstract boolean isGoodToGo(Board board, int x, int y);

    public boolean isWhite() {
        return isWhite;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public byte getType() {
        return type;
    }

    public boolean isMoved() { return isMoved; }

    public void setMoved(boolean moved) { isMoved = moved; }

    protected boolean isWhite;
    protected int x,y;
    protected byte type;
    protected boolean isMoved;
}
