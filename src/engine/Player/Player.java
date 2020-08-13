package engine.Player;

public class Player {
    private final boolean isWhite;
    private final String name;
    private final String colourName;

    public Player(boolean isWhite, String name) {
        this.isWhite = isWhite;
        this.name = name;
        if(isWhite) { colourName = "White"; }
        else { colourName = "Black"; }
    }

    public boolean isWhite() {
        return isWhite;
    }
}
