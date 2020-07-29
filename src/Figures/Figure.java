package Figures;

import Board.Board;

public abstract class Figure {
    public Figure(boolean isWhite, int x, int y, int type) {
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

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isMoved() { return isMoved; }

    public void setMoved(boolean moved) { isMoved = moved; }

    protected boolean isWhite;
    protected int x,y;
    protected int type;
    protected boolean isMoved;
}
