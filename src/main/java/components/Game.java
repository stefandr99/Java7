package components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    Board board;
    Runnable player1, player2, timeKeeper;
    int k;

    public Game(Board board, int k) {
        this.board = board;
        this.k = k;
    }

    public void setPlayer1(Runnable player) {
        player1 = player;
    }

    public void setPlayer2(Runnable player) {
        player2 = player;
    }

    public void setPlayers(Runnable[] player) {
        player1 = player[0];
        player2 = player[1];
    }

    public int getK() {
        return k;
    }

    public void setTimeKeeper(Runnable timeKeeper) {
        this.timeKeeper = timeKeeper;
    }

    /**
     * Functie in care lansez in executie toti player-ii.
     * Aici este comentat timekeeperul deoarece am considerat ca intr-un joc cu player manual nu are sens
     * Pentru un joc doar cu coputerul, se poate decomenta si functioneaza foarte bine.
     */
    public void play() {
        new Thread(player1).start();
        new Thread(player2).start();
        //new Thread(timeKeeper).start();
    }
}
