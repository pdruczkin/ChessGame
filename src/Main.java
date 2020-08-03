import Managment.Manager;
import Player.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 300, 275));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
        Manager manager = new Manager();
        Player player1 = new Player(true, "Player.Player 1");
        Player player2 = new Player(false, "Player.Player 2");
        manager.run(player1, player2);
    }


}