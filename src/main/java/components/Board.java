package components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Board {
    List<Token> tokens;
    volatile static int n;
    int m;
    volatile boolean egal = true;
    int turn = 1;
    public static volatile boolean play = true;

    public Board(int n, int m) {
        tokens = new ArrayList<>(n);
        this.n = n;
        this.m = m;
        setTokens();
    }

    /**
     * Aici creez tokenii care vor fi pe tabla. Cum m > n imi permit sa selectez random niste numere.
     * Acel rand.nextInt(100) reprezinta o probabilitate: in jocul meu am considerat ca 95% din carti vor avea numar,
     * iar 5% vor fi blank (cum scrie in enunt ca aceste carti pot lua orice valoare). Eu am pus 0 pe cartile blank.
     */
    public void setTokens() {
        int[] fr = new int[m + 1];
        Arrays.fill(fr, 0);
        Random rand = new Random();
        int nr;
        for(int i = 1; i <= n; i++) {
            if(rand.nextInt(100) + 1 < 95) {
                while (fr[nr = (rand.nextInt(m) + 1)] != 0) ;
                fr[nr] = 1;
                tokens.add(new Token(nr));
            }
            else {
                tokens.add(new Token());
            }
        }
    }

    public List<Token> getTokens() {
        return tokens;
    }

    /**
     * Metoda prin care fiecare player va extrege un token. Extragerea se va face aleator.
     * Odata ce se trage un token, decrementam numarul de tokeni de pe tabla.
     * @return Token-ul pe care l-a extras playerul
     */
    synchronized public Token extract() {
        Random rand = new Random();
        return tokens.remove(rand.nextInt(n--));
    }

    /**
     * Functie folosita pentru jocul manual. In aceasta situatie, player-ul va trebui sa introduca de la tastatura
     *  numarul pe care doreste sa il aleaga. Se cauta numarul in lista de Tokeni, daca nu se gaseste, inseamna ca
     *  jucatorul a facut o gluma, a spus ca vrea un Token care nu exista, asa ca ii vom da un Token aleatoriu din cele
     *  care exista.
     * @param nr Numarul de pe Token-ul care a fost ales.
     * @return Token-ul cu acel numar,
     *  un Token aleatoriu altfel
     */
    public Token extractOne(int nr) {
        for(int i = 0; i < tokens.size(); i++) {
            if(tokens.get(i).getValue() == nr) {
                n--;
                return tokens.remove(i);
            }
        }
        Random rand = new Random();
        return tokens.remove(rand.nextInt(n--));
    }

    /**
     * Metoda ce verifica daca mai sunt tokeni pe tabla.
     * @return true - mai sunt tokeni pe table,
     *         false - altfel
     */
    public boolean stillPlay() {
        return n > 0;
    }

    public int getN() {
        return n;
    }

    public static void setN(int n) {
        Board.n = n;
    }

    /**
     * Functie ce va fi apelata in cazul in care castiga un jucator. Egalul este true by default.
     * Deci daca nu va fi apelata inseamna ca nu a castigat nimeni, deci meci egal.
     */
    public void resetEgal() {
        egal = false;
    }

    public boolean getEgal() {
        return egal;
    }

    synchronized public int getTurn() {
        return turn;
    }

    /**
     * Fiecare jucator va avea asignat un numar de ordine: 1, respectiv -1. Aici schimb obrdinea jucatorilor
     *  prin trimiterea numarului -1 de fiecare cand se schimba tura.
     * @param turn -1
     */
    synchronized public void setTurn(int turn) {
        this.turn *= turn;
    }
}
