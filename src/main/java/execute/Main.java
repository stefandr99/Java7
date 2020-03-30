package execute;

import components.Board;
import components.Game;
import components.Player;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(15, 20);
        Runnable p1 = new Player("Stefan");
        Runnable p2 = new Player("Maria");
        Runnable p3 = new Player("Teodora");

        board.setTokens();
        Game game = new Game(board, 3);
        game.addPlayer(p1);
        game.addPlayer(p2);
        game.addPlayer(p3);

        game.play();
    }
}
