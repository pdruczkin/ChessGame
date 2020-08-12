package engine.Player;

import java.util.Scanner;

public class Player {
    private boolean isWhite;
    private String name;
    private String colourName;
    private byte oldX, oldY, newX, newY;

    public byte getOldX() {
        return oldX;
    }

    public byte getOldY() {
        return oldY;
    }

    public byte getNewX() {
        return newX;
    }

    public byte getNewY() {
        return newY;
    }

    private Scanner scanner = new Scanner(System.in);

    public Player(boolean isWhite, String name) {
        this.isWhite = isWhite;
        this.name = name;
        if(isWhite) { colourName = "White"; }
        else { colourName = "Black"; }
    }


    public String getColourName() {
        return colourName;
    }

    public void setColourName(String colourName) {
        this.colourName = colourName;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
