package players;

import components.Board;
import components.Token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.Thread.sleep;

public abstract class Player implements Runnable {
    String name;
    List<Token> myTokens = new ArrayList<>();
    int points;
    final Board board;
    int index;
    int n;
    int k;

    public Player(String name, Board board, int k, int n, int index) {
        this.name = name;
        this.index = index;
        this.board = board;
        this.k = k;
        this.n = n;
        points = 0;
    }

    /**
     * Functie principala a jocului. Aici se stabileste daca se continua jocul sau nu (bucla while).
     * Am ales sa sincronizez obiectul board pentru ca este un obiect asupra caruia se poate aplica
     *  wait/notify pentru ca este un obiect comun ambilor jucatori.
     * Dupa terminarea jocului asignez false variabilei membru play ceea ce insemna ca jocul s-a terminat.
     * Daca "egal" din clasa Board inca este true, este egalitate si afisez mesajul corespunzator.
     */
    @Override
    public void run() {
        synchronized (board) {
            while (board.stillPlay()) {
                //System.out.println(name + " am intrat in joc ");
                playTheGame();
            }
            Board.play = false;
            if (board.getEgal()) {
                points = verifyProgression() + countZeros();
                System.out.println("Egalitate. " + name + " are " + points + " puncte.");
            }
        }
    }

    abstract void playTheGame();

    public void setK(int k) {
        this.k = k;
    }

    /**
     * Inchid jocul prin setarea numarului de tokeni la 0 (zero).
     */
    public void closeGame() {
        board.setN(0);
    }

    /**
     * Numar numarul de tokeni cu valoarea 0, adica tokeni blank.
     * Este o strategie buna si simpla: numararea acestor tokeni si adaugarea numarului
     *  la progresia aritmetica (adica adaug ori la finalul progresiei, ori la inceput)
     * @return numarul de tokeni blank
     */
    public int countZeros() {
        int zeros = 0;
        for(Token t: myTokens) {
            if(t.getValue() == 0) {
                zeros++;
            }
        }
        return zeros;
    }

    /**
     * Algoritm dinamic prin care calculez cea mai mare progresie aritmetica din numerele de pe tokeni
     * Consider de la inceput ca progresia aritmetica maxima este 2, ceea ce e adevarat mereu.
     * Sortez tokenii pentru ca voi avea nevoie la calcul. Creez un vector nou al carui elemente va indica pentru fiecare
     *  numar de pe tokeni cea mai mare progresie aritmetica care se poate forma de la acel numar de pe token.
     * Incepem de la penultima pozitie a sirului de tokeni si cautam in stanga si dreapta "seturi" de 3 numere care
     *  ar putea forma o progresie aritmetica (indeplinesc conditia a + c = 2 * b)
     * Daca se gasesc astfel de 3 numere, se face maximul dintre 2 valori:
     *      - cea mai mare progresie aritmetica care se poate forma de la tokenul curent (corespondentul lui b mai sus)
     *      - SAU cea mai mare progresie aritmetica care se poate forma pornind de la tokenul din dreapta (corespondentul lui c mai sus)
     * In conditiile "else if" si "else", realizez aceleasi operatii ca la cautarea binara.
     * @param
     * @return cea mai mare progresie aritmetica ce se poate forma din numerele de pe tokeni
     */
    public int verifyProgression() {
        int maxi = 2;
        Collections.sort(myTokens);
        int[] maxiArith = new int[myTokens.size()];
        Arrays.fill(maxiArith, 2);
        for (int j = maxiArith.length - 2; j >= 0; j--)
        {
            int i = j - 1;
            int l = j + 1;
            while (i >= 0 && l < maxiArith.length)
            {
                if (myTokens.get(i).getValue() + myTokens.get(l).getValue() == 2 * myTokens.get(j).getValue() && myTokens.get(i).getValue() != 0)
                {
                    maxiArith[j] = Math.max(maxiArith[l] + 1, maxiArith[j]);
                    maxi = Math.max(maxi, maxiArith[j]);
                    i -= 1;
                    l += 1;
                }
                else if (myTokens.get(i).getValue() + myTokens.get(l).getValue() < 2 * myTokens.get(j).getValue())
                    l += 1;
                else
                    i -= 1;
            }
        }
        return maxi;
    }
}
