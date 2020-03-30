package components;

import java.util.ArrayList;
import java.util.List;

public class Game {
    Board board;
    List<Runnable> players = new ArrayList<>();
    int k;

    public Game(Board board, int k) {
        this.board = board;
        this.k = k;
    }

    public void addPlayer(Runnable player) {
        players.add(player);
    }

    public void addPlayers(List<Runnable> player) {
        players.addAll(player);
    }

    /**
     * Functie unde lansez in executie toti player-ii. O incercare, nu m-am prins inca exact cum functioneaza
     * toate lucrurile astea cu thread-uri. Nu functioneaza
     */
    public void play() {
        for (Runnable p: players) {
            new Thread(p).start();
        }
    }
}
