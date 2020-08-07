package engine.gui;

import engine.Managment.Manager;
import engine.Player.Player;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Table extends Application {
    private static final int HEIGHT = 800;
    private static final int WIDTH = 1100;
    private static final int CELL = 100;

    @Override
    public void start(Stage stage) throws Exception {

        Group root = new Group();

        Manager manager = new Manager(root,CELL);
        BoardPanel boardPanel = new BoardPanel(root,WIDTH,HEIGHT,CELL);
        Player player1 = new Player(true, "engine.Player.engine.Player 1");
        Player player2 = new Player(false, "engine.Player.engine.Player 2");
        manager.run(player1,player2);
        stage.setTitle("CHESS GAME");
        stage.setScene(boardPanel.getScene());
        stage.show();


    }
}
