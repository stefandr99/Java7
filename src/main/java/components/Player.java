package components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Player implements Runnable {
    String name;
    List<Token> myTokens = new ArrayList<>();
    int points;
    Board board;
    int k;

    public Player(String name) {
        this.name = name;
        points = 0;
    }

    /**
     * Functia prin care fiecare jucator va extrage token
     */
    @Override
    public void run() {
        myTokens.add(board.extract());
        if(verifyProgression(k)) {
            System.out.println(name + ": am castigat!");
        }
    }

    public void setK(int k) {
        this.k = k;
    }

    /**
     * Algoritm dinamic prin care calculez cea mai mare progresie aritmetica din numerele de pe tokei
     * Nu am timp sa il explic acum. Il voi explica cand voi preda intreaga tema :)
     * @param k
     * @return
     */
    public boolean verifyProgression(int k) {
        int maxi = 2;
        Collections.sort(myTokens);
        int maxiArith[] = new int[myTokens.size()];
        Arrays.fill(maxiArith, 2);
        for (int j = maxiArith.length - 2; j >= 0; j--)
        {
            int i = j - 1;
            int l = j + 1;
            while (i >= 0 && k < maxiArith.length)
            {
                if (myTokens.get(i).getValue() + myTokens.get(l).getValue() == 2 * myTokens.get(j).getValue())
                {
                    maxiArith[j] = Math.max(maxiArith[k] + 1, maxiArith[j]);
                    maxi = Math.max(maxi, maxiArith[j]);
                    i -= 1;
                    k += 1;
                }
                else if (myTokens.get(i).getValue() + myTokens.get(l).getValue() < 2 * myTokens.get(j).getValue())
                    k += 1;
                else
                    i -= 1;
            }
        }
        return maxi >= k;
    }
}
