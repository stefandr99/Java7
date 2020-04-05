package components;

import static java.lang.Thread.sleep;

public class Timekeeper implements Runnable{

    /**
     * Timekeeper care tine evidenta timpului de desfasurare a jocului.
     * Cat timp se joaca jocul sau jocul nu a depasit limita de 5 secunde totul e in regula.
     * Daca se termina jocul, afiseaza cat timp a durat jocul. Am oferit doua optiuni de afisare a timpului:
     *      - daca e peste 1 secunda atunci are sens sa calculez in secunde;
     *      - daca nu, voi afisa timpul in milisecunde.
     * Daca jocul dureaza mai mult de 5 secunde, timekeeperul va opri jocul (seteaza n la 0) si afiseaza un mesaj
     * Acel sleep are ca scop intarzierea afisarii mesajului de catre timekeeper ca sa nu se intercaleze cu mesajul
     *  jucatorilor.
     */
    @Override
    public void run() {
        long initialTime = System.currentTimeMillis();
        while(Board.play && (System.currentTimeMillis() - initialTime) / 1000 < 5);
        if(!Board.play) {
            try {
                sleep(10);
                System.out.println("Jocul a durat " + (
                        ((System.currentTimeMillis() - initialTime) < 2000) ?
                                ((System.currentTimeMillis() - initialTime) + " milisecunde.") :
                                ((System.currentTimeMillis() - initialTime) / 1000 + " secunde."))
                );
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
        else {
            Board.setN(0);
            System.out.println("Time's UP!");
        }
    }
}
