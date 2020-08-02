package Figures;

import Board.Board;

public abstract class Figure {
    public Figure(boolean isWhite, byte x, byte y, byte type) {
        this.isWhite = isWhite;
        this.x = x;
        this.y = y;
        this.type = type;
        isMoved = false;
    }

    public abstract boolean isGoodToGo(Board board, byte x, byte y, boolean isKingInCheck);

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

    public void setX(byte x) {
        this.x = x;
    }

    public void setY(byte y) {
        this.y = y;
    }

    public byte getX() {
        return x;
    }

    public byte getY() {
        return y;
    }

    public boolean hasJustMovedTwo() {
        return justMovedTwo;
    }

    public void setJustMovedTwo(boolean justMovedTwo) {
        this.justMovedTwo = justMovedTwo;
    }

    protected byte x,y;
    protected boolean isMoved;
    protected boolean justMovedTwo; // for Pawns
}
