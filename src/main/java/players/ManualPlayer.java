package players;

import components.Board;
import components.Token;

import java.util.Scanner;
import java.util.function.ToDoubleBiFunction;

public class ManualPlayer extends Player{

    public ManualPlayer(String name, Board board, int k, int n, int index) {
        super(name, board, k, n, index);
    }

    /**
     * Player manual care va oferi raspunsuri de la tastatura.
     * Va raspunde cu "da" daca a creat o progresie de lungime k.
     */
    synchronized void playTheGame() {
        while (board.getTurn() != index) {
            try {
                board.wait();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
        if(board.stillPlay()) {
            System.out.print(name+ ", alege un Token: ");
            for(Token t: board.getTokens()) {
                System.out.print(t.getValue() + " ");
            }
            System.out.println();
            System.out.print("Raspuns: ");
            Scanner in = new Scanner(System.in);
            int nr = in.nextInt();
            myTokens.add(board.extractOne(nr));
            System.out.print(name+ ", pana acum ai: ");
            for(Token t: myTokens) {
                System.out.print(t.getValue() + " ");
            }
            System.out.println();
            System.out.println(name+ ", ai format o progresie aritmetica de lungime " + k + "?");
            System.out.print("Raspuns: ");
            Scanner in2 = new Scanner(System.in);
            String raspuns = in2.nextLine();
            if(raspuns.compareTo("da") == 0) {
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
