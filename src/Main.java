import engine.Managment.Manager;
import engine.Player.Player;
import gui.Table;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Table{

    public static void main(String[] args) {
        launch(args);
        Manager manager = new Manager();
        Player player1 = new Player(true, "engine.Player.engine.Player 1");
        Player player2 = new Player(false, "engine.Player.engine.Player 2");
        manager.run(player1, player2);
        System.out.println(":)");

    }


}