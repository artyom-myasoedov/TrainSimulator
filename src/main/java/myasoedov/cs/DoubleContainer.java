package myasoedov.cs;

import java.io.Serializable;

public class DoubleContainer<A, B>  implements Serializable {
    private A first;
    private B second;

    public DoubleContainer() {
    }

    public DoubleContainer(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public void setFirst(A first) {
        this.first = first;
    }

    public B getSecond() {
        return second;
    }

    public void setSecond(B second) {
        this.second = second;
    }
}
