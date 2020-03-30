package components;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    List<Token> tokens;
    int n;
    int m;

    public Board(int n, int m) {
        tokens = new ArrayList<>(n);
        this.n = n;
        this.m = m;
        setTokens();
    }

    /**
     * Aici creez tokenii care vor fi pe tabla. Cum m > n imi permit sa selectez random niste numere
     * Acel rand.nextInt(100) reprezinta o probabilitate: in jocul meu am considerat ca 95% din carti vor avea numar,
     * iar 5% vor fi blank (cum scrie in enunt ca aceste carti pot lua orice valoare). Eu am pus 0 pe cartile blank
     */
    public void setTokens() {
        int fr[] = new int[m + 1];
        Random rand = new Random();
        int nr;
        for(int i = 1; i <= n; i++) {
            if(rand.nextInt(100) + 1 < 95) {
                while (fr[nr = (rand.nextInt(m) + 1)] != 0) ;
                fr[nr] = 1;
                tokens.add(new Token(nr));
            }
            else {
                tokens.add(new Token(0));
            }
        }
    }

    /**
     * Metoda prin care fiecare player va extrege un token. Extragerea se va face aleator
     * Odata ce se trage un tokenul, decrementam nr de tokeni de pe tabla
     * @return
     */
    public synchronized Token extract() {
        Random rand = new Random();
        return tokens.remove(rand.nextInt(n--));
    }

    /**
     * Inca sunt tokeni pe tabla?
     * Sa stim cand optim jocul
     * @return
     */
    public boolean stillPlay() {
        return n == 0;
    }
}
