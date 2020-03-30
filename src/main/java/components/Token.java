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
     * Am implementat un comparable pentru ca am neovie sa sortez lista de tokeni mai tarziu
     * @param t1
     * @return
     */
    public int compareTo(Token t1) {
        return this.value - t1.getValue();
    }
}
