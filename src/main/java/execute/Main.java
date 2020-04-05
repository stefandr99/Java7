package execute;

import components.Board;
import components.Game;
import players.ManualPlayer;
import components.Timekeeper;
import players.RandomPlayer;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(15, 20);
        Game game = new Game(board, 4);
        Runnable p1 = new ManualPlayer("Stefan", board, game.getK(), board.getN(), 1);
        Runnable p2 = new RandomPlayer("Nicoleta", board, game.getK(), board.getN(), -1);
        Runnable timeKeeper = new Timekeeper();

        game.setPlayers(new Runnable[]{p1, p2});
        game.setTimeKeeper(timeKeeper);

        game.play();
    }
}
