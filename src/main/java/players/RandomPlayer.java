package players;

import components.Board;

public class RandomPlayer extends Player {

    public RandomPlayer(String name, Board board, int k, int n, int index) {
        super(name, board, k, n, index);
    }

    /**
     * Strategie random a computerului. Nu face niciun calcul, pur si simplu alege tokeni din lista si verifica mereu daca a obtinut progresia aritmetica
     *  de lungime k.
     */
    synchronized void playTheGame() {
        while (board.getTurn() != index) {
            try {
                board.wait();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
        if(board.stillPlay()) {
            myTokens.add(board.extract());
            if (verifyProgression() + countZeros() >= k) {
                points = n;

                System.out.println(name + ": Am castigat! Am obtinut " + n + " puncte!");
                board.resetEgal();
                closeGame();
            }
            board.setTurn(-1);
            board.notify();
        }
    }
}
