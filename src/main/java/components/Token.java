package components;

import java.util.Comparator;

public class Token implements Comparable<Token> {
    int value;

    public Token() {
        value = 0;
    }

    public Token(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Am implementat un comparable pentru ca voi sorta lista de tokeni pentru a calcula prograsia aritmetica maxima
     * @param t1 Celalalt Token pe care il compar
     * @return valoare ce arata care din Token-uri are valoare mai mare.
     */
    public int compareTo(Token t1) {
        return this.value - t1.getValue();
    }
}
