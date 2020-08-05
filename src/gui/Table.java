package gui;

import engine.Board.Board;
import engine.Player.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import engine.Managment.Manager;

public class Table extends Application {
    private static final int HEIGHT = 800;
    private static final int WIDTH = 1100;
    private static final int CELL = 100;

    @Override
    public void start(Stage stage) throws Exception {

        Group root = new Group();
        BoardPanel boardPanel = new BoardPanel(root,WIDTH,HEIGHT,CELL);
        Manager manager = new Manager();
        Player player1 = new Player(true, "engine.Player.engine.Player 1");
        Player player2 = new Player(false, "engine.Player.engine.Player 2");
        stage.setTitle("CHESS GAME");
        stage.setScene(boardPanel.getScene());
        stage.show();
        //manager.run(player1, player2);
    }
}
