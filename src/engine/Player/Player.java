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

    public void setOldCords(){
        boolean correctCords;
        do {
            System.out.print("Old X: ");
            oldX = (byte) scanner.nextInt();
            System.out.print("Old Y: ");
            oldY = (byte) scanner.nextInt();
            correctCords = oldX < 0 || oldX >= 8 || oldY < 0 || oldY >=8;
            if(correctCords){
                System.out.println("Cords are incorrect, try again! ");
            }

        }while(correctCords);
    }

    public void setNewCords(){
        boolean correctCords;
        do {
            System.out.print("New X: ");
            newX = (byte) scanner.nextInt();
            System.out.print("New Y: ");
            newY = (byte) scanner.nextInt();
            correctCords = newX < 0 || newX >= 8 || newY < 0 || newY >=8;
            if(correctCords){
                System.out.println("Cords are incorrect, try again! ");
            }
        }while(correctCords);
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

    public Player() {

    }
}
