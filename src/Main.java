public class Main {
    public static void main(String[] args) {
       Manager manager = new Manager();
       Player player1 = new Player(true, "Player 1");
       Player player2 = new Player(false, "Player 2");
       manager.run(player1, player2);
    }
}
